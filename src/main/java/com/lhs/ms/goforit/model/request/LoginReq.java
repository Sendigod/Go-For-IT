package com.lhs.ms.goforit.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author Liang Hui Sen
 * @Date 2022/4/4 17:42
 * @Description :
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginReq {

    private String username;

    private String password;

    private Integer permission;
}
