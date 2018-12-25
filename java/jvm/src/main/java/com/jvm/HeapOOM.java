package com.jvm;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Java堆溢出
 */
public class HeapOOM {

    public static void main(String[] args){
        // -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
        /**
         *      -Xms20m = 虚拟街堆容量尺寸（Size）
         *      -Xmx20m = 虚拟机堆最大容量（Max）
         *      -XX:+HeapDumpOnOutOfMemoryError（Dump出OOM的异常信息）
         */
        List<Date> list = new ArrayList<>();
        while(true){
            list.add(new Date());
        }

    }

}
