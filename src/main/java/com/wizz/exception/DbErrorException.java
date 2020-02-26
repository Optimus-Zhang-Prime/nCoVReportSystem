package com.wizz.exception;

/**
 * @descrip：数据库返回错误信息的异常
 * @author: 李佳
 * @create： 2020-02-26-22:13
 **/
public class DbErrorException extends RuntimeException{
    public DbErrorException (String message) {
        super(message);
    }
}
