//package com.jack.springcloud.common.util;
//
//
//import com.jack.springcloud.bean.StaticReadYml;
//import com.jack.springcloud.common.vo.User;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.util.StringUtils;
//
//import javax.servlet.http.HttpServletRequest;
//import java.util.ArrayList;
//import java.util.List;
//
//@Slf4j
//public class UserPermissionUtil {
//
//	/**
//	 * 模拟权限校验, 可以根据自己项目需要定制不同的策略,如查询数据库获取具体的菜单url或者角色等等.
//	 */
//	public static boolean verify(User user, HttpServletRequest request){
//		//1.先判断能调用本服务的黑白名单
//
//		//2.在判断此用户是否有权限调用我
//		String x = StaticReadYml.getInstanceId();
//		String localServiceName = x.substring(0,x.indexOf(":"));
//		List<String> list = getServiceListByUserName(user.getUserName());
//		for(String name : list){
//			if(localServiceName.equalsIgnoreCase(name)){
//				return true;
//			}
//		}
//		return false;
//	}
//
//	public static List<String> getServiceListByUserName(String username){
//		ArrayList<String> list = new ArrayList<>();
//		if("one".equals(username)){
//			list.add("service-client-personal");
//		}
//		if("two".equals(username)){
//			list.add("service-client-personal");
//			list.add("service-client-order");
//		}
//		if("three".equals(username)){
//			list.add("service-client-personal");
//			list.add("service-client-order");
//			list.add("service-client-consumer");
//		}
//		return list;
//	}
//
//
//}
