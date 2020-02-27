package com.wizz.utils;

import org.springframework.stereotype.Component;
import org.json.JSONException;
import org.json.JSONObject;
/**
 * @descrip：
 * @author: 李佳
 * @create： 2020-02-26-01:51
 **/
@Component
public class ForumUtils {
    public static String toJsonString(Integer code) throws JSONException {
        JSONObject ajson =new JSONObject();
        ajson.put("code",code);
        return ajson.toString();
    }
}
