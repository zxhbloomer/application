package com.jack.springcloud.exception;

import java.sql.SQLException;

public class NoRequestException extends RuntimeException {

    public NoRequestException(String msg) {
        super(msg);
    }

    public NoRequestException() {
        this("获取系统用户发送错误!");
    }

}
