package com.lhs.ms.goforit.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.lhs.ms.goforit.model.request.LoginReq;
import com.lhs.ms.goforit.model.request.RegisterReq;
import com.lhs.ms.goforit.model.response.BaseResponse;
import com.lhs.ms.goforit.model.response.LoginRes;
import com.lhs.ms.goforit.service.LoginService;
import com.lhs.ms.goforit.service.RegisterService;
import com.lhs.ms.goforit.service.impl.LoginServiceImpl;
import com.lhs.ms.goforit.service.impl.RegisterServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author Liang Hui Sen
 * @Date 2022/4/4 18:24
 * @Description :
 */
@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private RegisterService registerService;

    @PostMapping("/login")
    public BaseResponse loginHandle(@RequestBody LoginReq req, HttpServletRequest request){
        return BaseResponse.builder().data(loginService.handle(req,request)).build();
    }

    @PostMapping("/register")
    public BaseResponse registerHandle(@RequestBody RegisterReq req){
        return BaseResponse.builder().data(registerService.registerHandle(req)).build();
    }

    @PostMapping("/register/checkNbr")
    private Boolean checkNbr(@RequestBody RegisterReq req){
        return registerService.checkHandle(req);
    }

}
