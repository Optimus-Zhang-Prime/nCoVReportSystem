package com.wizz.entity.jsonReturn;

import java.util.Map;

/**
 * @descrip：ids返回值封装
 * @author: 李佳
 * @create： 2020-02-27-19:11
 * {
 *     "code": 10000,
 *     "message": "ok",
 *     "description": "api请求成功",
 *     "uuid": "7136773201484e569253219ebb66247d",
 *     "result": {
 *         "access_token": "f398d0a5-ac53-45eb-a9d2-55000782317b",
 *         "token_type": "bearer",
 *         "expires_in": 7199,
 *         "scope": "server",
 *         "license": "made by pangu"
 *     }
 * }
 **/
public class IdsReturn {
    private String code;
    private String message;
    private String description;
    private String uuid;
    private Map<String,Object> result;

    public IdsReturn() {
    }

    public IdsReturn(String code, String message, String description, String uuid, Map<String, Object> result) {
        this.code = code;
        this.message = message;
        this.description = description;
        this.uuid = uuid;
        this.result = result;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Map<String, Object> getResult() {
        return result;
    }

    public void setResult(Map<String, Object> result) {
        this.result = result;
    }
}
