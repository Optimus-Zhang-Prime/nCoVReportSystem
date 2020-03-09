package com.wizz.component;

import com.wizz.exception.DbErrorException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartException;

import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

/**
 * @descrip：异常处理器
 * @author: 李佳
 * @create： 2020-02-26-00:24
 **/
@ControllerAdvice
public class MyExceptionHandler {
    /** @Description: 处理上传文件太大的异常
    * @Param: [e]
    * @return: java.util.Map<java.lang.String,java.lang.Object>
    * @Author: 李佳
    * @Date: 2020/2/26
    */
    @ExceptionHandler(MultipartException.class)
    @ResponseBody
    public Map<String,Object> overFileMaxSize (Exception e) {
        Map<String,Object> message = new HashMap<>();
        message.put("errCode",500);
        message.put("message","文件太大了");
        return message;
    }
    @ExceptionHandler(DbErrorException.class)
    @ResponseBody
    public Map<String,Object> dbError (Exception e) {
        Map<String,Object> message = new HashMap<>();
        message.put("errCode",500);
        message.put("message",e.getMessage());
        return message;
    }
    /** @Description: 参数校验异常
    * @Param: [e]
    * @return: org.springframework.http.ResponseEntity<java.lang.String>
    * @Author: 李佳
    * @Date: 2020/3/9
    */
    
    @ExceptionHandler(ConstraintViolationException.class)
    ResponseEntity<String> handleConstraintViolationException(ConstraintViolationException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
}
