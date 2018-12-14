package com.jack.springcloud.common.intercepter;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;


/**
 * JWT-Feign拦截器
 */
@Component
@Slf4j
public class FeignUserContextInterceptor implements RequestInterceptor {

    @Value("${eureka.instance.instance-id}")
    private String instanceId;

    @Override
    public void apply(RequestTemplate requestTemplate) {

        Object userId = request().getHeader("x-user-id");
        Object userName = request().getHeader("x-user-name");
        Object scope = request().getHeader("x-token-scope");
        Object active = request().getHeader("x-token-active");
        Object exp = request().getHeader("x-token-exp");
        Object authorities = request().getHeader("x-token-authorities");
        Object jti = request().getHeader("x-token-jti");
        Object clientId = request().getHeader("x-client-id");
        Object accessToken = request().getHeader("x-access-token");

        requestTemplate.header("x-user-id",userId != null ? userId.toString() : null);
        requestTemplate.header("x-user-name",userName != null ? userName.toString() : null);
        requestTemplate.header("x-token-scope",scope != null ? scope.toString() : null);
        requestTemplate.header("x-token-active",active != null ? active.toString() : null);
        requestTemplate.header("x-token-exp",exp != null ? exp.toString() : null);
        requestTemplate.header("x-token-authorities",authorities != null ? authorities.toString() : null);
        requestTemplate.header("x-token-jti",jti != null ? jti.toString() : null);
        requestTemplate.header("x-client-id",clientId != null ? clientId.toString() : null);
        requestTemplate.header("x-access-token",accessToken != null ? accessToken.toString() : null);

        requestTemplate.header("x-user-serviceName",instanceId);

        log.info("JWT-Feign拦截器 : {} instanceId = {}",instanceId);
    }

    /**
     * 通过上下文获取Request
     */
    protected HttpServletRequest request() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();

        return request;
    }


}
