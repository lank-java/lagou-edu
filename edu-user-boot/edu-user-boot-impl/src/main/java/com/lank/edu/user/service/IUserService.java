package com.lank.edu.user.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lank.edu.user.dto.UserDTO;
import com.lank.edu.user.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lank.edu.user.vo.UserQueryParam;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author LanceLan
 * @since 2021-05-13
 */
public interface IUserService extends IService<User> {

    /**
     * 分页查询用户列表
     * @param userQueryParam 查询参数
     * @return dto
     */
    Page<UserDTO> getUserPages(UserQueryParam userQueryParam);

}
