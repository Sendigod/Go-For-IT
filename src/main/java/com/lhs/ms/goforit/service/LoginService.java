package com.lhs.ms.goforit.service;

import com.lhs.ms.goforit.model.request.LoginReq;
import com.lhs.ms.goforit.model.response.LoginRes;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author Liang Hui Sen
 * @Date 2022/4/4 17:17
 * @Description :
 */
public interface LoginService {


    LoginRes handle(LoginReq req, HttpServletRequest request);

}
