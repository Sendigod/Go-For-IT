package com.lhs.ms.goforit.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Date;

/**
 * @Author Liang Hui Sen
 * @Date 2022/4/4 16:23
 * @Description :
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "name")
    private String name;

    @Column(name = "phone_nbr")
    private BigInteger phoneNbr;

    @Column(name = "crt_time")
    private Date crtTime;

    @Column(name = "user_icon")
    private String userIcon;

    @Column(name = "token")
    private String token;

    @Column(name = "permission")
    private Integer permission;

}
