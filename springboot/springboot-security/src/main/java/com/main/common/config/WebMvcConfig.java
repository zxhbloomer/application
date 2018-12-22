package com.main.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 映射JS静态资源(新的方式)
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    /**
     * 静态资源映射
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }
//    /**
//     * 支持CORS跨域访问
//     */
//    @Override
//    public void addCorsMappings(CorsRegistry registry){
//        //允许所有的跨域访问
//        registry.addMapping("/**");
//    }
}