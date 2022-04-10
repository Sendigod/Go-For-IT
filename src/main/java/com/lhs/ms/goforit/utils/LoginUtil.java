package com.lhs.ms.goforit.utils;

import cn.dev33.satoken.secure.SaBase64Util;
import cn.dev33.satoken.secure.SaSecureUtil;
import cn.hutool.core.util.RandomUtil;

/**
 * @Author Liang Hui Sen
 * @Date 2022/4/5 17:30
 * @Description :
 */
public class LoginUtil {

    /**
     * 用base64的方法加密， 盐值随机取5位。
     *
     * @param passwordStr
     * @return 加密后的密码
     */
    public static String encodePwd(String passwordStr) {
        String salt = RandomUtil.randomString(5);
        String base64Pass = encodePwd(passwordStr, salt);
        return base64Pass;
    }

    /**
     * 传入正常的密码加盐值，解密后再给正常的密码加密
     *
     * @param passwordStr
     * @param salt
     * @return 加密后的密码。
     */
    public static String encodePwd(String passwordStr, String salt) {
        String hasPass = SaSecureUtil.sha256(passwordStr + salt);
        String fullPass = salt + hasPass;
        String base64 = SaBase64Util.encode(fullPass);
        return base64;
    }

    /**
     * 将数据库里面的密码和用户输入的密码加密后比较
     *
     * @param realPwd
     * @param intPutPwd
     * @return 比较用户输入的密码和数据库的密码是否一致。一致返回True ，否则返回false
     */
    public static boolean matchPass(String realPwd, String intPutPwd) {
        String realPass = SaBase64Util.decode(realPwd);
        String salt = realPass.substring(0, 5);
        String inputPass = encodePwd(intPutPwd, salt);
        if (inputPass.equals(realPwd)) {
            return true;
        }
        return false;
    }
}
