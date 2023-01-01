package com.heima.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.user.dto.LoginDTO;
import com.heima.model.user.pojo.ApUser;

/**
 * @author lujunxiong
 * @date 2022/9/5 21:32
 * @description:
 */
public interface ApUserService extends IService<ApUser> {

    /**
     * app端登录功能
     *
     * @param dto
     * @return
     */
    ResponseResult login(LoginDTO dto);
}
