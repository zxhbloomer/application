package com.main.config;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum GatewayRouteEnum {

    USER("用户服务","/service/user/**","serviceRoute01"),
    INDENT("订单服务","/service/indent/**","serviceRoute02"),
    MANAGE("消费服务","/service/manage/**","serviceRoute03");

    private String desc;
    private String path;
    private String id;

    public static GatewayRouteEnum getRouteByName(String id){
        for (GatewayRouteEnum value : GatewayRouteEnum.values()) {
            if(value.getId().equals(id)){
                return value;
            }
        }
        return null;
    }


    
}
