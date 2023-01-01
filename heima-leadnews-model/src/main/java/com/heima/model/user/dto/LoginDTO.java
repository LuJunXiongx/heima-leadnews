package com.heima.model.user.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author lujunxiong
 * @date 2022/9/5 21:28
 * @description:
 */
@Data
public class LoginDTO {

    @ApiModelProperty(value = "手机号", required = true)
    private String phone;

    @ApiModelProperty(value = "密码", required = true)
    private String password;
}
