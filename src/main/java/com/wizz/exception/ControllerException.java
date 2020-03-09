package com.wizz.exception;

/**
 * @descrip：controller中的exception,并未使用仅仅是测试
 * @author: 李佳
 * @create： 2020-03-09-16:46
 **/
public class ControllerException extends RuntimeException{
    public ControllerException (String message) {
        super("controlled错误：  "+message);
    }
}
