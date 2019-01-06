//package com.main;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.*;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletRequestWrapper;
//import javax.servlet.http.HttpServletResponse;
//import java.io.*;
//
//import static com.main.WebHooksUtil.validateSignature;
//
///**
// * Github WebHooks的请求拦截器,解决因为载荷(payload),SpringBoot无法正常反序列化这串载荷而报了400错误：
// */
//@Component
//@Slf4j
//public class ConfigServerValidateFilter implements Filter {
//
//    private static final String SIGNATURE_PREFIX = "sha1=";
//    private static final String HMAC_SHA1_ALGORITHM = "HmacSHA1";
//    private static final String SECRET = "123456";
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        HttpServletRequest request = (HttpServletRequest)servletRequest;
//        HttpServletResponse response = (HttpServletResponse)servletResponse;
//
//        String url = new String(request.getRequestURI());
//        //过滤/actuator/bus-refresh请求
//        if (!url.endsWith("/actuator/bus-refresh")) {
//            filterChain.doFilter(servletRequest, servletResponse);
//        }else{
//            //验证是否来自Github的请求
//            if(!validateSignature(request)){
//                response.sendError(403);
//                return ;
//            }
//            //使用HttpServletRequest包装原始请求达到修改post请求中body内容的目的
//            CustomerRequestWrapper requestWrapper = new CustomerRequestWrapper(request);
//            filterChain.doFilter(requestWrapper, servletResponse);
//        }
//
//    }
//
//    private class CustomerRequestWrapper extends HttpServletRequestWrapper {
//        public CustomerRequestWrapper(HttpServletRequest request) {
//            super(request);
//        }
//        @Override
//        public ServletInputStream getInputStream() throws IOException {
//            byte[] bytes = new byte[0];
//            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
//            return new ServletInputStream() {
//                @Override
//                public boolean isFinished() {
//                    return byteArrayInputStream.read() == -1 ? true:false;
//                }
//                @Override
//                public boolean isReady() {
//                    return false;
//                }
//                @Override
//                public void setReadListener(ReadListener readListener) {
//
//                }
//                @Override
//                public int read() throws IOException {
//                    return byteArrayInputStream.read();
//                }
//            };
//        }
//    }
//
//
//    @Override
//    public void destroy() {
//
//    }
//
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//
//    }
//
//}
