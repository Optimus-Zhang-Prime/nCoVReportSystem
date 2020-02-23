package com.wizz.utils;

import org.json.JSONException;
import org.json.JSONObject;

//工具类
public class ForumUtils {
    public static String toJsonString(Integer code) throws JSONException {
        JSONObject ajson =new JSONObject();
        ajson.put("code",code);
        return ajson.toString();
    }
}
