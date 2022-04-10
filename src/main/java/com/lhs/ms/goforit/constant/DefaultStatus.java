package com.lhs.ms.goforit.constant;

/**
 * @Author Liang Hui Sen
 * @Date 2022/4/4 17:36
 * @Description :
 */
public enum DefaultStatus implements StatusCode {

    SUCCESS(200, "Success"),
    PARAM_ERROR(400, "Invalid parameters"),
    NOT_FOUND(404, "Resource not found"),
    FAILURE(500, "Application internal error");

    private final int code;
    private final String message;

    DefaultStatus(int code, String message) {
        this.code = code;
        this.message = message;
    }


    @Override
    public int code() {
        return 0;
    }

    @Override
    public String message() {
        return null;
    }
}
