package com.jack.springcloud.bean;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Data
public class RequestUser implements Serializable {

    /**用户ID**/
    private Long userId;
    /**用户名称**/
    private String userName;
    /**此用户是否可用(根据exp判断)**/
    private Boolean active;
    /**此Token的到期时间**/
    private Date exp;
    /**用户权限组**/
    private String[] authorities;
    /**此请求过来的ServiceName**/
    private String serviceName;
    /**Token**/
    private String accessToken;

    /**JTI(OAuth2的字段)**/
    private String jti;
    /**客户端ID(OAuth2的字段)**/
    private String clientId;
    /**允许访问的作用域(OAuth2的字段)**/
    private String[] scope;

}
