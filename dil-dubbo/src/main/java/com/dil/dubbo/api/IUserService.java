package com.dil.dubbo.api;

import com.dil.dubbo.dto.UserReqDTO;
import com.dil.dubbo.dto.UserResDTO;
import com.dil.dubbo.res.Response;

public interface IUserService {

    Response<UserResDTO> queryUserInfo(UserReqDTO reqDTO);

}