package com.main;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

public class SystemUtil {

    private SystemUtil() {

    }

    /**
     * 获取系统的编码
     */
    public static Map getSystemCoding(){
        Map map = new HashMap();
        //系统默认编码
        map.put("encoding",System.getProperty("file.encoding"));
        //系统默认的字符编码
        map.put("defaultCharset", Charset.defaultCharset());
        //系统默认语言
        map.put("language",System.getProperty("user.language"));
        //获取系统属性列表
//		System.getProperties().list(System.out);
        //设置编码
//		System.getProperties().put("file.encoding","GB2312");
        return map;
    }



}
