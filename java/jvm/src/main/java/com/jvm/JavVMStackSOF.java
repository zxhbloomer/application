package com.jvm;

/**
 * 虚拟机栈和本地方法栈OOM测试
 */
public class JavVMStackSOF {

    private int stackLength = 1;

    public void stackLeak(){
        stackLength++;
        stackLeak();
    }

    /**
     *  -Xss128K  = 虚拟机栈大小,注意此大小在Java8的情况下不能小于180K(StackSize)
     */
    public static void main(String[] args){
        JavVMStackSOF oom = new JavVMStackSOF();
        try{
            oom.stackLeak();
        }catch(Exception e){
            System.out.println("stack length : " + oom.stackLength);
            throw e;
        }
    }

}
