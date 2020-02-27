package com.wizz.utils;

<<<<<<< HEAD
import org.springframework.stereotype.Component;

/**
 * @descrip：
 * @author: 李佳
 * @create： 2020-02-26-01:51
 **/
@Component
public class ForumUtils {
    public static String toJsonString (Integer code) {
        return "";
=======
import org.json.JSONException;
import org.json.JSONObject;

//工具类
public class ForumUtils {
    public static String toJsonString(Integer code) throws JSONException {
        JSONObject ajson =new JSONObject();
        ajson.put("code",code);
        return ajson.toString();
>>>>>>> 6a53a11f8ba22719f710872a07efe52ae70a959f
    }
}
