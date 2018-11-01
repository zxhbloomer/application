package com.main.common.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationListener;
import org.springframework.security.core.session.SessionDestroyedEvent;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.CopyOnWriteArraySet;

@Slf4j
@Component
public class RedisSessionRegistryImpl implements SessionRegistry,ApplicationListener<SessionDestroyedEvent> {

	@Autowired
	RedisService service;

	@Override
	public List<Object> getAllPrincipals() {
		return new ArrayList<>(service.getPrincipalsKeySet());
	}

	@Override
	public List<SessionInformation> getAllSessions(Object principal, boolean includeExpiredSessions) {
		String key = ((UserDetails) principal).getUsername();

		Set<String> sessionsUsedByPrincipal = service.getPrincipals(key);

		if(sessionsUsedByPrincipal == null){ return Collections.emptyList(); }
		List<SessionInformation> list = new ArrayList<>(sessionsUsedByPrincipal.size());
		for(String sessionId : sessionsUsedByPrincipal){
			SessionInformation sessionInformation = service.getSessionInformation(sessionId);
			if(sessionInformation == null) continue;
			if(includeExpiredSessions || !sessionInformation.isExpired()) list.add(sessionInformation);
		}
		return list;
	}

	@Override
	public SessionInformation getSessionInformation(String sessionId) {
		return service.getSessionInformation(sessionId);
	}

	@Override
	public void onApplicationEvent(SessionDestroyedEvent event) {
		String sessionId = event.getId();
		service.removeSessionInfo(sessionId);
	}

	@Override
	public void refreshLastRequest(String sessionId) {
		SessionInformation info = service.getSessionInformation(sessionId);
		if(info != null)info.refreshLastRequest();
	}

	@Override
	public void registerNewSession(String sessionId, Object principal) {
		String key = ((UserDetails)principal).getUsername();
		//创建新的Session之前把已经登录的Session失效
		service.sessionLogout(key);
		if(service.getSessionInformation(sessionId) != null)service.removeSessionInfo(sessionId);
		service.addSessionInfo(sessionId, new SessionInformation(principal, sessionId, new Date()));
		Set<String> sessionsUsedByPrincipal = service.getPrincipals(key);
		if(sessionsUsedByPrincipal == null){
			sessionsUsedByPrincipal = new CopyOnWriteArraySet<>();
			Set<String> prevSessionsUsedByPrincipal = service.putIfAbsentPrincipals(key,sessionsUsedByPrincipal);
			if(prevSessionsUsedByPrincipal != null) sessionsUsedByPrincipal = prevSessionsUsedByPrincipal;
		}
		sessionsUsedByPrincipal.add(sessionId);
		service.putPrincipals(key,sessionsUsedByPrincipal);//Mark
	}

	@Override
	public void removeSessionInformation(String sessionId) {
		SessionInformation info = service.getSessionInformation(sessionId);
		String key = ((UserDetails)info.getPrincipal()).getUsername();
		if (info == null) return;
		service.removeSessionInfo(sessionId);
		Set<String> sessionsUsedByPrincipal = service.getPrincipals(key);
		if (sessionsUsedByPrincipal == null) return;
		sessionsUsedByPrincipal.remove(sessionId);
		if(sessionsUsedByPrincipal.isEmpty()) service.removePrincipal(key);
	}


}
