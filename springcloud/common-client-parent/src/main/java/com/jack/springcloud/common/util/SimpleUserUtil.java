package com.jack.springcloud.common.util;

import com.jack.springcloud.bean.RequestUser;
import com.jack.springcloud.exception.NoRequestException;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class SimpleUserUtil {

    /**
     * 通过上下文获取Request,并且从Request里获取业务用户
     */
    public static RequestUser getRequestUser() {
        HttpServletRequest request = null;
        try{
            request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        }catch (RuntimeException e){
            e.printStackTrace();
        }
        RequestUser user = null;
        if(request != null){
            user = new RequestUser();
            Object userId = request.getHeader("x-user-id");
            Object userName = request.getHeader("x-user-name");
            Object scope = request.getHeader("x-token-scope");
            Object active = request.getHeader("x-token-active");
            Object exp = request.getHeader("x-token-exp");
            Object authorities = request.getHeader("x-token-authorities");
            Object jti = request.getHeader("x-token-jti");
            Object clientId = request.getHeader("x-client-id");
            Object accessToken = request.getHeader("x-access-token");
            Object serviceName = request.getHeader("x-user-serviceName");

            user.setUserId(userId != null ? Long.valueOf(userId.toString()) : null);
            user.setUserName(hasNull(userName));
            user.setScope(scope != null ? getStringArray(scope.toString()) : null);
            user.setActive(active != null ? Boolean.valueOf(active.toString()) : null);
            user.setExp(exp != null ? new Date(Long.valueOf(exp.toString()) * 1000) : null);
            user.setAuthorities(authorities != null ? getStringArray(authorities.toString()) : null);
            user.setJti(hasNull(jti));
            user.setClientId(hasNull(clientId));
            user.setAccessToken(hasNull(accessToken));
            user.setServiceName(hasNull(serviceName));
            return user;
        }else{
            throw new NoRequestException();
        }
    }

    public static String hasNull(Object obj){
        return obj != null ? obj.toString() : null;
    }



    private static String[] getStringArray(String str){
        if(str != null && str.length() != 0){
            String item = str.substring(1,str.length()-1);
            if(item.length() != 0){
                String[] result = item.split(",");
               return result;
            }
        }
        return null;
    }

}
