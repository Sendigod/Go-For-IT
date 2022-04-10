package com.lhs.ms.goforit.model.request;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Id;
import java.math.BigInteger;
import java.util.Date;

/**
 * @Author Liang Hui Sen
 * @Date 2022/4/5 22:53
 * @Description :
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterReq {

    private String username;

    private String password;

    private Integer checkNbr;

    private String name;

    private BigInteger phoneNbr;

    private Date crtTime;

    private String userIcon;

    private String token;

    private Integer permission;
}
