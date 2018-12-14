//package com.jack.springcloud.common.intercepter;
//
//import com.jack.springcloud.common.vo.User;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.http.HttpRequest;
//import org.springframework.http.client.ClientHttpRequestExecution;
//import org.springframework.http.client.ClientHttpRequestInterceptor;
//import org.springframework.http.client.ClientHttpResponse;
//
//import java.io.IOException;
//
///**
// * JWT-RestTemplate拦截器
// */
//@Slf4j
//public class RestTemplateUserContextInterceptor implements ClientHttpRequestInterceptor {
//
//	@Override
//	public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
//		User user = UserContextHolder.currentUser();
//
//		request.getHeaders().add("x-user-name",user.getUserName());
//		request.getHeaders().add("x-token-scope",user.getScope());
//		request.getHeaders().add("x-token-active",user.getActive());
//		request.getHeaders().add("x-token-exp",user.getExp());
//		request.getHeaders().add("x-token-authorities",user.getAuthorities());
//		request.getHeaders().add("x-token-jti",user.getJti());
//		request.getHeaders().add("x-client-id",user.getClientId());
//
//		request.getHeaders().add("x-user-serviceName",request.getURI().getHost());
//		log.info("JWT-RestTemplate拦截器 : {},RequestUriHost = {}",user);
//		return execution.execute(request, body);
//	}
//}
