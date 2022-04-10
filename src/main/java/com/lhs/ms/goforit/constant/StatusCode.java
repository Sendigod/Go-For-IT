package com.lhs.ms.goforit.constant;

import com.fasterxml.jackson.annotation.JsonValue;

import java.io.Serializable;

/**
 * @Author Liang Hui Sen
 * @Date 2022/4/4 17:33
 * @Description :
 */
public interface StatusCode extends Serializable {

    @JsonValue
    /**
     * 序列化时只显示code，message以BaseResponse中的为准
     * @return 返回请求状态码
     */
    int code();

    /**
     *
     * @return 返回请求体的信息
     */
    String message();
}
