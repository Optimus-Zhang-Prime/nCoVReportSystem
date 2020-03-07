package com.wizz.utils;
import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;

import java.util.Random;

public class SendMess {

    public static void sendCode(String tel,String code) {
        // 短信应用 SDK AppID
        int appid = 1400325529; // SDK AppID 以1400开头
        // 短信应用 SDK AppKey
        String appkey = "defc2e336cc6cb1162f9dda29829f45d";
        // 需要发送短信的手机号码
        String[] phoneNumbers = {tel};
        // 短信模板 ID，需要在短信应用中申请
        int templateId = 545285; // NOTE: 这里的模板 ID`7839`只是示例，真实的模板 ID 需要在短信控制台中申请
        // 签名
        String smsSign = "吴家骥我的数据可视化分析"; // NOTE: 签名参数使用的是`签名内容`，而不是`签名ID`。这里的签名"腾讯云"只是示例，真实的签名需要在短信控制台申请
        try {
            String[] params = {code};
            SmsSingleSender ssender = new SmsSingleSender(appid, appkey);
            SmsSingleSenderResult result = ssender.sendWithParam("86", phoneNumbers[0],
                    templateId, params, smsSign, "", "");
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void sendNotice(String tel) {//提醒用户打卡
        // 短信应用 SDK AppID
        int appid = 1400325529; // SDK AppID 以1400开头
        // 短信应用 SDK AppKey
        String appkey = "defc2e336cc6cb1162f9dda29829f45d";
        // 需要发送短信的手机号码
        String[] phoneNumbers = {tel};
        // 短信模板 ID，需要在短信应用中申请
        int templateId = 548865; // NOTE: 这里的模板 ID`7839`只是示例，真实的模板 ID 需要在短信控制台中申请
        // 签名
        String smsSign = "吴家骥我的数据可视化分析"; // NOTE: 签名参数使用的是`签名内容`，而不是`签名ID`。这里的签名"腾讯云"只是示例，真实的签名需要在短信控制台申请
        try {
            String[] params = {};
            SmsSingleSender ssender = new SmsSingleSender(appid, appkey);
            SmsSingleSenderResult result = ssender.sendWithParam("86", phoneNumbers[0],
                    templateId, params, smsSign, "", "");
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void sendNoticeToAdmin(String tel,Integer num) {//提醒管理员
        // 短信应用 SDK AppID
        int appid = 1400325529; // SDK AppID 以1400开头
        // 短信应用 SDK AppKey
        String appkey = "defc2e336cc6cb1162f9dda29829f45d";
        // 需要发送短信的手机号码
        String[] phoneNumbers = {tel};
        // 短信模板 ID，需要在短信应用中申请
        int templateId = 548867; // NOTE: 这里的模板 ID`7839`只是示例，真实的模板 ID 需要在短信控制台中申请
        // 签名
        String smsSign = "吴家骥我的数据可视化分析"; // NOTE: 签名参数使用的是`签名内容`，而不是`签名ID`。这里的签名"腾讯云"只是示例，真实的签名需要在短信控制台申请
        try {
            String[] params = {num.toString()};
            SmsSingleSender ssender = new SmsSingleSender(appid, appkey);
            SmsSingleSenderResult result = ssender.sendWithParam("86", phoneNumbers[0],
                    templateId, params, smsSign, "", "");
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
