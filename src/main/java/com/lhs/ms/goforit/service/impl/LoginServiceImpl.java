package com.lhs.ms.goforit.service.impl;

import cn.dev33.satoken.stp.StpLogic;
import cn.dev33.satoken.stp.StpUtil;
import com.lhs.ms.goforit.common.config.cache.Cache;
import com.lhs.ms.goforit.constant.CheckImageConstant;
import com.lhs.ms.goforit.entity.User;
import com.lhs.ms.goforit.exception.ValidateCodeException;
import com.lhs.ms.goforit.model.request.LoginReq;
import com.lhs.ms.goforit.model.response.LoginRes;
import com.lhs.ms.goforit.repository.UserRepository;
import com.lhs.ms.goforit.service.LoginService;
import com.lhs.ms.goforit.utils.LoginUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.util.Optional;
/**
 * @Author Liang Hui Sen
 * @Date 2022/4/4 17:18
 * @Description :
 */

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserRepository userRepository;

    private Cache cache;


    @Override
    public LoginRes handle(LoginReq req, HttpServletRequest request) {

//        ServletContext context = request.getServletContext();
//        ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(context);
//        cache = ctx.getBean(Cache.class);
//
//        String imageCode = request.getHeader(CheckImageConstant.KEY_IMAGE_CODE);
//        String codeInCache = cache.getKey(imageCode);
//        String codeInRequest = null;
//        try {
//            codeInRequest = ServletRequestUtils.getStringParameter(request, "imageCode");
//        } catch (ServletRequestBindingException e) {
//        }
//        if (StringUtils.isBlank(codeInRequest)) {
//            throw new ValidateCodeException("验证码不能为空！");
//        }
//        if (codeInCache == null) {
//            throw new ValidateCodeException("验证码不存在，请重新发送！");
//        }
//        if (!StringUtils.equalsIgnoreCase(codeInCache, codeInRequest)) {
//            throw new ValidateCodeException("验证码不正确！");
//        }

        Optional<User> user = Optional.ofNullable(userRepository.findByUsername(
                req.getUsername()));
        if(user.isPresent()&&LoginUtil.matchPass(user.get().getPassword(),req.getPassword())){
            StpLogic stpLogic = new StpLogic(user.map(User::getToken).orElse("aaa"));
            stpLogic.setTokenValueToCookie(user.get().getToken(),3000);
            stpLogic.login(user.map(User::getUsername).get());
            return LoginRes.builder()
                    .user(user.get())
                    .build();
        }
        return null;
    }
}
