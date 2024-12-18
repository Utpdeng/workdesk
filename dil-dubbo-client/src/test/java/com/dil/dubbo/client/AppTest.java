package com.dil.dubbo.client;

import com.alibaba.fastjson.JSON;
import com.dil.dubbo.api.IUserService;
import com.dil.dubbo.dto.UserReqDTO;
import com.dil.dubbo.dto.UserResDTO;
import com.dil.dubbo.res.Response;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class AppTest {

    // 测试使用直连模式；
    @DubboReference(interfaceClass = IUserService.class, url = "dubbo://127.0.0.1:20881", version = "1.0.0")
    // 测试注册中心模式；需要配置 Zookeeper
//    @DubboReference(interfaceClass = IUserService.class, version = "1.0.0")
    private IUserService userService;

    @Test
    public void test_userService() {
        UserReqDTO reqDTO = UserReqDTO.builder().userId("10001").build();
        Response<UserResDTO> resDTO = userService.queryUserInfo(reqDTO);
//        log.info("测试结果 req: {} res: {}", JSON.toJSONString(reqDTO), JSON.toJSONString(resDTO));
        log.info("测试结果 req: {} res: {}", reqDTO, resDTO);
    }

}
