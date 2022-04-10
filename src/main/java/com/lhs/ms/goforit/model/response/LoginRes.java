package com.lhs.ms.goforit.model.response;

import com.lhs.ms.goforit.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author Liang Hui Sen
 * @Date 2022/4/4 17:41
 * @Description :
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginRes {

    private User user;
}
