package com.dil.dubbo.service;

import com.alibaba.fastjson.JSON;
import com.dil.dubbo.api.IUserService;
import com.dil.dubbo.dto.UserReqDTO;
import com.dil.dubbo.dto.UserResDTO;
import com.dil.dubbo.res.Constants;
import com.dil.dubbo.res.Response;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;

@Slf4j
@DubboService(version = "1.0.0", interfaceClass = IUserService.class)
public class UserService implements IUserService {

    @Override
    public Response<UserResDTO> queryUserInfo(UserReqDTO reqDTO) {
//        log.info("查询用户信息 userId: {} reqStr: {}", reqDTO.getUserId(), JSON.toJSONString(reqDTO));
        try {
            UserResDTO resDTO = UserResDTO.builder()
                    .userId(reqDTO.getUserId())
                    .userName("Jack")
                    .userAge(20)
                    .build();

            return Response.<UserResDTO>builder()
                    .code(Constants.ResponseCode.SUCCESS.getCode())
                    .info(Constants.ResponseCode.SUCCESS.getInfo())
                    .data(resDTO).build();
        } catch (Exception e) {
//            log.error("查询用户信息失败 userId: {} reqStr: {}", reqDTO.getUserId(), JSON.toJSONString(reqDTO), e);
            return Response.<UserResDTO>builder()
                    .code(Constants.ResponseCode.UN_ERROR.getCode())
                    .info(Constants.ResponseCode.UN_ERROR.getInfo())
                    .build();
        }
    }
}
