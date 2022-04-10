package com.lhs.ms.goforit.utils;


import com.lhs.ms.goforit.constant.CommonConstants;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.sms.v20210111.SmsClient;
import com.tencentcloudapi.sms.v20210111.models.SendSmsRequest;
import com.tencentcloudapi.sms.v20210111.models.SendSmsResponse;
import com.tencentcloudapi.sms.v20210111.models.SendStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.UUID;

/**
 * @Author Liang Hui Sen
 * @Date 2022/4/10 15:28
 * @Description :
 */
@Slf4j
@Component
public class SendSmsUtil {

    @Value("${com.lhs.ms.secretId}")
    private String secretId;

    @Value("${com.lhs.ms.secretKey}")
    private String secretKey;

    @Value("${com.lhs.ms.appId}")
    private String appId;

    @Value("${com.lhs.ms.signName}")
    private String signName;

    @Value("${com.lhs.ms.templateId}")
    private String templateId;


    public SendSmsResponse sendSms(String phoneNbr,String[] templateParamSet){
        SendSmsResponse response =null;
        try {
            ClientProfile clientProfile = new ClientProfile();
            Credential cred = new Credential(secretId, secretKey);
            SmsClient client = new SmsClient(cred, "ap-guangzhou",clientProfile);
            SendSmsRequest request = new SendSmsRequest();
            request.setSmsSdkAppId(appId);
            request.setSignName(signName);
////            String[] templateParamSet = {String.valueOf((int) ((random.nextDouble() * (99999 - 10000 + 1)) + 10000))};
            request.setTemplateParamSet(templateParamSet);
            request.setTemplateId(templateId);
            request.setPhoneNumberSet(new String[]{"+86"+phoneNbr});
            response = client.SendSms(request);
        } catch (TencentCloudSDKException e) {
            e.printStackTrace();
        }
        return response;
    }

}
