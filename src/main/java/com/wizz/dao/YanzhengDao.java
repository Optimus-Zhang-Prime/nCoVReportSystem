package com.wizz.dao;

/**
 * @descrip：验证码操作
 * @author: 李佳
 * @create： 2020-03-07-21:55
 **/
public interface YanzhengDao {
    void save (String tel,String code); // 新创建或者更新验证码
    String get (String tel); // 根据手机号获取存储的验证码
}
