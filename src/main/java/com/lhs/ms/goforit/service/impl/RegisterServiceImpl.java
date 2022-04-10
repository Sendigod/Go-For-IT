package com.lhs.ms.goforit.service.impl;

import com.lhs.ms.goforit.constant.CommonConstants;
import com.lhs.ms.goforit.entity.User;
import com.lhs.ms.goforit.model.request.RegisterReq;
import com.lhs.ms.goforit.model.response.RegisterRes;
import com.lhs.ms.goforit.repository.UserRepository;
import com.lhs.ms.goforit.service.RegisterService;
import com.lhs.ms.goforit.utils.LoginUtil;
import com.lhs.ms.goforit.utils.SendSmsUtil;
import com.tencentcloudapi.sms.v20210111.models.SendSmsResponse;
import com.tencentcloudapi.sms.v20210111.models.SendStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.Optional;
import java.util.Random;

/**
 * @Author Liang Hui Sen
 * @Date 2022/4/5 22:56
 * @Description :
 */
@Service
public class RegisterServiceImpl implements RegisterService {


    @Autowired
    private RegisterServiceImpl registerService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SendSmsUtil sendSmsUtil;

    private Integer checkNbr;

    @Override
    public RegisterRes registerHandle(RegisterReq req) {
        Optional<RegisterReq> reqOptional = Optional.ofNullable(req);
        String encodePassword = LoginUtil.encodePwd(reqOptional.map(RegisterReq::getPassword).get());
        User user = User.builder()
                .username(reqOptional.map(RegisterReq::getUsername).get())
                .password(encodePassword)
                .name(reqOptional.map(RegisterReq::getName).orElse(""))
                .phoneNbr(reqOptional.map(RegisterReq::getPhoneNbr).orElse(null))
                .permission(reqOptional.map(RegisterReq::getPermission).orElse(null))
                .userIcon(reqOptional.map(RegisterReq::getUserIcon).orElse(null))
                .build();
        User saveResult= null;
        if (checkNbr.equals(reqOptional.map(RegisterReq::getCheckNbr).orElse(null))) {
             saveResult = userRepository.save(user);
        }
        return RegisterRes.builder().registerResult(
                Optional.ofNullable(saveResult).isPresent()
        ).build();
    }

    @Override
    public Boolean checkHandle(RegisterReq req) {
        Random random = new Random();
        checkNbr = (int) (random.nextDouble() * (99999 - 10000 + 1)) + 10000;
        String[] templateParamSet = {String.valueOf(checkNbr) };
        SendSmsResponse response = sendSmsUtil.sendSms(String.valueOf(req.getPhoneNbr()),templateParamSet);
        SendStatus[] statuses = response.getSendStatusSet();
        return CommonConstants.CODE_OK.equals(statuses[0].getCode());
    }
}
