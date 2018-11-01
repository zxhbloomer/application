package com.main.common.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Slf4j
public class RedisService {

	private static final String SESSIONIDS = "sessionIds";
	private static final String PRINCIPALS = "principals";

	@Qualifier("redisTemplate")
	@Autowired
	private RedisTemplate template;

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	/**
	 * 添加一个Session
	 */
	public void addSessionInfo(final String sessionId, final SessionInformation sessionInformation){
		BoundHashOperations<String, String, SessionInformation> hash = template.boundHashOps(SESSIONIDS);
		hash.put(sessionId,sessionInformation);
		log.info("[添加一个Session] Param:sessionId={},sessionInformation={} -- Data:hashKeys={},hashValues={}",sessionId,sessionInformation,hash.keys(),hash.values());
	}

	/**
	 * 根据SessionID获取Session信息
	 */
	public SessionInformation getSessionInformation(final String sessionId){
		BoundHashOperations<String, String, SessionInformation> hash = template.boundHashOps(SESSIONIDS);
		if(hash.get(sessionId) != null){ log.info("[根据SessionID获取Session信息] Param:sessionId={} -- Data:hashKeys={},hashValues={}",sessionId,hash.keys(),hash.values()); }
		return hash.get(sessionId);
	}

	/**
	 * 根据SessionID删除Session信息
	 */
	public void removeSessionInfo(final String sessionId) {
		BoundHashOperations<String, String, SessionInformation> hashOperations = template.boundHashOps(SESSIONIDS);
		Long num = hashOperations.delete(sessionId);
		log.info("[根据SessionID删除Session信息] Param:sessionId={} -- Data:num={}",sessionId,num);
	}

	/**
	 * 获取所有的PrincipalsKeySet
	 */
	public Set<String> getPrincipalsKeySet() {
		BoundHashOperations<String, String, Set<String>> hash = template.boundHashOps(PRINCIPALS);
		log.info("[获取所有的PrincipalsKeySet] Data:hashKeys={},hashValues={}",hash.keys(),hash.values());
		return hash.keys();
	}
	/**
	 * 如果没有的话,插入此Principals
	 */
	public Set<String> putIfAbsentPrincipals(final String key, final Set<String> set) {
		BoundHashOperations<String, String, Set<String>> hash = template.boundHashOps(PRINCIPALS);
		Boolean result = hash.putIfAbsent(key, set);
		log.info("[如果没有的话,插入此Principals] Param:key={},set = {} -- Data:hashKeys={},hashValues={} -- Result:result={}",key,set,hash.keys(),hash.values(),result);
		return hash.get(key);
	}
	/**
	 * 添加一个Principals
	 */
	public void putPrincipals(final String key, final Set<String> set) {
		BoundHashOperations<String, String, Set<String>> hash = template.boundHashOps(PRINCIPALS);



		hash.put(key,set);
		log.info("[添加一个Principals] Param key = {},set = {} -- Data:hashKeys={},hashValues={}",key,set,hash.keys(),hash.values());
	}

	/**
	 * 根据Key获取Principals
	 */
	public Set<String> getPrincipals(final String key) {
		BoundHashOperations<String, String, Set<String>> hash = template.boundHashOps(PRINCIPALS);

		log.info("[根据Key获取Principals ] Param key = {} -- Result:result={}",key,hash.get(key));
		return hash.get(key);
	}

	/**
	 * 使此账号之前的Session失效
	 */

	public void sessionLogout(final String key){
		BoundHashOperations<String, String, Set<String>> hash = template.boundHashOps(PRINCIPALS);
		//当用户登录重复的账号的时候提出之前的登录
		if(hash.size() != 0){
			hash.entries().entrySet().forEach(e -> {
				if (e.getKey().equals(key)){
					removePrincipal(key);
					for(String sessionId : e.getValue()){
						simpleRemoveSession(sessionId);
					}
				}
			});
		}
	}

	/**
	 * 自定义删除Session
	 * (重点) 如果你想使用redis里面的key直接删除的话就是用StringRedisTemplate进行删除
	 * RedisTemplate和String的模板操作的时候key的规则是不一样的!!!
	 */
	public void simpleRemoveSession(String sessionId){
		String key1 = "spring:session:sessions:"+sessionId;
		String key2 = "spring:session:sessions:expires:"+sessionId;
		String key3 = "spring:session:index:org.springframework.session.FindByIndexNameSessionRepository.PRINCIPAL_NAME_INDEX_NAME:15277578023";
//		BoundHashOperations<String, String, Set<String>> hs = template.boundHashOps(SESSIONIDS);
//		Long num2 = hs.delete(sessionId);
		Boolean b1 = stringRedisTemplate.delete(key1);
		Boolean b2 = stringRedisTemplate.delete(key2);
		Boolean b3 = stringRedisTemplate.delete(key3);

	}


	/**
	 * 根据删除一个Principal
	 */
	public void removePrincipal(final String key) {
		BoundHashOperations<String, String, Set<String>> hash = template.boundHashOps(PRINCIPALS);
		Long num = hash.delete(key);
		log.info("[根据删除一个Principal] Param:key={} -- Data:hashKeys={},hashValues={} -- Result:num={}",key,hash.keys(),hash.values(),num);
	}

}
