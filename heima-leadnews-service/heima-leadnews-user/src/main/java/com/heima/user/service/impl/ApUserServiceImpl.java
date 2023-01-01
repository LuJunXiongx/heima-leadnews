package com.heima.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.common.enums.AppHttpCodeEnum;
import com.heima.model.user.dto.LoginDTO;
import com.heima.model.user.pojo.ApUser;
import com.heima.user.mapper.ApUserMapper;
import com.heima.user.service.ApUserService;
import com.heima.utils.common.AppJwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lujunxiong
 * @date 2022/9/5 21:33
 * @description:
 */
@Service
@Transactional
@Slf4j
public class ApUserServiceImpl extends ServiceImpl<ApUserMapper, ApUser> implements ApUserService {

    @Override
    public ResponseResult login(LoginDTO dto) {
        //1.正常登录 输入用户名和密码
        if (StringUtils.isNotBlank(dto.getPhone()) && StringUtils.isNotBlank(dto.getPassword())) {
            //根据手机号查询用户
            ApUser user = this.lambdaQuery().eq(ApUser::getPhone, dto.getPhone()).one();
            if (user == null) {
                return ResponseResult.errorResult(AppHttpCodeEnum.DATA_NOT_EXIST, "用户信息不存在");
            }
            //数据库盐
            String salt = user.getSalt();
            String password = dto.getPassword();
            String pwd = DigestUtils.md5DigestAsHex((password + salt).getBytes());
            //加盐后的密码和数据库密码比较
            if (!StringUtils.equals(pwd, user.getPassword())) {
                return ResponseResult.errorResult(AppHttpCodeEnum.LOGIN_PASSWORD_ERROR);
            }
            //返回数据
            String token = AppJwtUtil.getToken(user.getId().longValue());
            Map<String, Object> map = new HashMap<>(3);
            map.put("token", token);
            user.setSalt("");
            user.setPassword("");
            map.put("user", user);
            return ResponseResult.okResult(map);
        } else {
            //2.游客登录
            Map<String, Object> map = new HashMap<>(3);
            map.put("token", AppJwtUtil.getToken(0L));
            return ResponseResult.okResult(map);
        }
    }
}
