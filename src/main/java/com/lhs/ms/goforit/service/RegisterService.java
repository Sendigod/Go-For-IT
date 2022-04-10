package com.lhs.ms.goforit.service;

import com.lhs.ms.goforit.model.request.RegisterReq;
import com.lhs.ms.goforit.model.response.RegisterRes;

/**
 * @Author Liang Hui Sen
 * @Date 2022/4/5 22:52
 * @Description :
 */
public interface RegisterService {


    RegisterRes registerHandle(RegisterReq req);

    Boolean checkHandle(RegisterReq req);


}
