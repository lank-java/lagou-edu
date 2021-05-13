package com.lank.edu.user.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lank.edu.user.api.UserRemoteService;
import com.lank.edu.user.dto.UserDTO;
import com.lank.edu.user.service.IUserService;
import com.lank.edu.user.vo.UserQueryParam;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author LanceLan
 * @since 2021-05-13
 */
@RestController
@RequestMapping("/user/user")
public class UserController implements UserRemoteService {

    @Resource
    private IUserService iUserService;

    @Override
    public Page<UserDTO> getUserPages(UserQueryParam userQueryParam) {
        return iUserService.getUserPages(userQueryParam);
    }
}
