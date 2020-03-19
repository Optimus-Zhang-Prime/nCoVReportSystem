package com.wizz.utils;
import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.github.qcloudsms.SmsMultiSender;
import com.github.qcloudsms.SmsMultiSenderResult;


import java.util.Random;

public class SendMess {

    public static void sendCode(String tel,String code) {
        // 短信应用 SDK AppID
        int appid = 1400332073; // SDK AppID 以1400开头
        // 短信应用 SDK AppKey
        String appkey = "384586a6b00fb7a5b1711f2a30bf624b";
        // 需要发送短信的手机号码
        String[] phoneNumbers = {tel};
        // 短信模板 ID，需要在短信应用中申请
        int templateId = 553085; // NOTE: 这里的模板 ID`7839`只是示例，真实的模板 ID 需要在短信控制台中申请
        // 签名
        String smsSign = "疫卡通"; // NOTE: 签名参数使用的是`签名内容`，而不是`签名ID`。这里的签名"腾讯云"只是示例，真实的签名需要在短信控制台申请
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
    public static void sendNotice(String[] phoneNumbers) {//提醒用户打卡
        // 短信应用 SDK AppID
        int appid = 1400332073; // SDK AppID 以1400开头
        // 短信应用 SDK AppKey
        String appkey = "384586a6b00fb7a5b1711f2a30bf624b";
        // 短信模板 ID，需要在短信应用中申请
        int templateId = 553077; // NOTE: 这里的模板 ID`7839`只是示例，真实的模板 ID 需要在短信控制台中申请
        // 签名
        String smsSign = "疫卡通"; // NOTE: 签名参数使用的是`签名内容`，而不是`签名ID`。这里的签名"腾讯云"只是示例，真实的签名需要在短信控制台申请
        try {
            String[] params = {};
            SmsMultiSender msender = new SmsMultiSender(appid, appkey);
        SmsMultiSenderResult result =  msender.sendWithParam("86", phoneNumbers,templateId, params, smsSign, "", "");
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void sendNoticeToAdmin(String tel,Integer num) {//提醒管理员
        // 短信应用 SDK AppID
        int appid = 1400332073; // SDK AppID 以1400开头
        // 短信应用 SDK AppKey
        String appkey = "384586a6b00fb7a5b1711f2a30bf624b";
        // 需要发送短信的手机号码
        String[] phoneNumbers = {tel};
        // 短信模板 ID，需要在短信应用中申请
        int templateId = 553090; // NOTE: 这里的模板 ID`7839`只是示例，真实的模板 ID 需要在短信控制台中申请
        // 签名
        String smsSign = "疫卡通"; // NOTE: 签名参数使用的是`签名内容`，而不是`签名ID`。这里的签名"腾讯云"只是示例，真实的签名需要在短信控制台申请
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
