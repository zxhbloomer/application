package com.main;

public interface SimpleInterface {

    //8:默认方法=只有子类实现此接口才可调用方法
    default String defaultMethod() {
        return "Jack";
    }

    //8:静态方法=可通过类名称直接调用
    static String staticMethod() {
        return "Jack";
    }

    //9:私有方法=外部不能调用
    private String privateMethod(){
        return "Jack";
    }

}
