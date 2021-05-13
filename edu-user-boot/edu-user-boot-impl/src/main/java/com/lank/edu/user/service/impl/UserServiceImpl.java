package com.lank.edu.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lank.edu.common.util.ConverUtil;
import com.lank.edu.user.dto.UserDTO;
import com.lank.edu.user.entity.User;
import com.lank.edu.user.mapper.UserMapper;
import com.lank.edu.user.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lank.edu.user.vo.UserQueryParam;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author LanceLan
 * @since 2021-05-13
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public Page<UserDTO> getUserPages(UserQueryParam userQueryParam) {
        Page<User> page = new Page<>();
        page.setCurrent(userQueryParam.getCurrentPage());
        page.setSize(userQueryParam.getPageSize());
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(userQueryParam.getPhone())) {
            queryWrapper.eq("phone", userQueryParam.getPhone());
        }
        if (userQueryParam.getUserId() != null) {
            queryWrapper.eq("id", userQueryParam.getUserId());
        }
        Date startCreateTime = userQueryParam.getStartCreateTime();
        Date endCreateTime = userQueryParam.getEndCreateTime();
        if (null != startCreateTime && null != endCreateTime) {
            queryWrapper.ge("create_time", startCreateTime);
            queryWrapper.le("create_time", endCreateTime);
        }
        Page<User> userPage = userMapper.selectPage(page, queryWrapper);
        Page<UserDTO> userDTOPage = new Page<>();
        userDTOPage = ConverUtil.convert(userPage, userDTOPage);
        userDTOPage.setOrders(userPage.getOrders());
        userDTOPage.setRecords(ConverUtil.convertList(page.getRecords(), UserDTO.class));
        return userDTOPage;
    }
}
