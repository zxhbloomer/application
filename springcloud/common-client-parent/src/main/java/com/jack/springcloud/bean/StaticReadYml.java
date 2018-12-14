//package com.jack.springcloud.bean;
//
//import com.jack.springcloud.common.util.UserPermissionUtil;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.PostConstruct;
//
//@Component
//public class StaticReadYml {
//
//    private static String instanceId;
//
//    @Value("${eureka.instance.instance-id}")
//    private String instanceIdItem;
//
//    @PostConstruct
//    public void getYmlConfig(){
//            instanceId = instanceIdItem;
//    }
//
//    public static String getInstanceId(){
//        return instanceId;
//    }
//}
