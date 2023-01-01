package com.heima.user.controller;

import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.user.dto.LoginDTO;
import com.heima.user.service.ApUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lujunxiong
 * @date 2022/9/5 21:26
 * @description:
 */
@RestController
@RequestMapping("/api/v1/login")
@Api(value = "app端用户登录", tags = "app端用户登录")
public class ApUserLoginController {

    @Autowired
    private ApUserService apUserService;

    @ApiOperation("用户登录功能")
    @PostMapping("/login_auth")
    public ResponseResult login(@RequestBody LoginDTO dto) {
        return this.apUserService.login(dto);
    }
}
