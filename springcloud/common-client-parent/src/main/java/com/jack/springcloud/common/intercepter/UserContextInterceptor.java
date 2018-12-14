//package com.jack.springcloud.common.intercepter;
//
//import com.alibaba.fastjson.JSON;
//import com.jack.springcloud.common.util.UserPermissionUtil;
//import com.jack.springcloud.common.vo.User;
//import lombok.extern.slf4j.Slf4j;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.web.servlet.ModelAndView;
//import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//@Slf4j
//public class UserContextInterceptor extends HandlerInterceptorAdapter {
//
//	@Override
//	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
//		User user = getUser(request);
//
//		log.info("进入 {}",user);
//
//		if(!UserPermissionUtil.verify(user,request)) {
//			log.info("错误");
//			response.setHeader("Content-Type", "application/json");
//			String jsonStr = JSON.toJSONString("no permission access service, please check");
//			response.getWriter().write(jsonStr);
//			response.getWriter().flush();
//			response.getWriter().close();
//			throw new PermissionException("no permission access service, please check");
//		}
//		UserContextHolder.set(user);
//		log.info("成功");
//		return true;
//	}
//
//	@Override
//	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object arg2, ModelAndView arg3) throws Exception {
//		// DOING NOTHING
//	}
//
//	@Override
//	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object arg2, Exception arg3) throws Exception {
//		UserContextHolder.shutdown();
//	}
//
//	private User getUser(HttpServletRequest request){
//		User user = new User();
//
//		Object userName = request.getHeader("x-user-name");
//		Object scope = request.getHeader("x-token-scope");
//		Object active = request.getHeader("x-token-active");
//		Object exp = request.getHeader("x-token-exp");
//		Object authorities = request.getHeader("x-token-authorities");
//		Object jti = request.getHeader("x-token-jti");
//		Object clientId = request.getHeader("x-client-id");
//
//		user.setUserName(userName == null ? null : userName.toString());
//		user.setScope(scope == null ? null : scope.toString());
//		user.setActive(active == null ? null : active.toString());
//		user.setExp(exp == null ? null : exp.toString());
//		user.setAuthorities(authorities == null ? null : authorities.toString());
//		user.setJti(jti == null ? null : jti.toString());
//		user.setClientId(clientId == null ? null : clientId.toString());
//
//		return user;
//	}
//
//	static class PermissionException extends RuntimeException {
//		private static final long serialVersionUID = 1L;
//		public PermissionException(String msg) {
//	        super(msg);
//	    }
//	}
//}
