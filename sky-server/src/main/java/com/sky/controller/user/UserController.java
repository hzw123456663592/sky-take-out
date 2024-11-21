package com.sky.controller.user;


import com.sky.constant.JwtClaimsConstant;
import com.sky.dto.UserLoginDTO;
import com.sky.entity.User;
import com.sky.properties.JwtProperties;
import com.sky.result.Result;
import com.sky.service.UserService;
import com.sky.utils.JwtUtil;
import com.sky.vo.UserLoginVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * c端用户登录-微信登录
 */
@RestController
@RequestMapping("/user/user")
@Api(tags = "c端用户接口")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

//    JWT配置属性，用于JWT令牌的生成和验证
    @Autowired
    private JwtProperties jwtProperties;

    @PostMapping("/login")
    @ApiOperation("微信登录")
    public Result<UserLoginVO> login(@RequestBody UserLoginDTO userLoginDTO){
        log.info("微信用户登录：{}",userLoginDTO.getCode());

        //微信登录
        User user = userService.wxLogin(userLoginDTO);

        //为微信用户生成JWt令牌
//        创建一个HashMap，用于存放JWT令牌的声明
        Map claims = new HashMap<>();
        claims.put(JwtClaimsConstant.USER_ID, user.getId());
//        将用户ID添加到声明中
        String token =  JwtUtil.createJWT(jwtProperties.getUserSecretKey(),jwtProperties.getUserTtl(),claims);
//        构建UserLoginVO对象，包含用户ID、openid和令牌
        UserLoginVO userLoginVO =  UserLoginVO.builder()
                .id(user.getId())
                .openid(user.getOpenid())
                .token(token)
                .build();

        return Result.success(userLoginVO);
    }


}
