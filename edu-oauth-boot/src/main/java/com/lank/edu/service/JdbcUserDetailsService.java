package com.lank.edu.service;

import com.lank.edu.dao.UsersRepository;
import com.lank.edu.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;

/**
 * @author LanceLan
 * @since 2021/5/19 11:20
 */
@Service
public class JdbcUserDetailsService implements UserDetailsService {

    @Resource
    private UsersRepository usersRepository;

    /**
     * 根据username查询出该⽤户的所有信息，封装成UserDetails类型的对象返回，⾄于密码，框
     * 架会⾃动匹配
     */
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Users users = usersRepository.findByUsername(s);
        return new User(users.getUsername(),users.getPassword(),new ArrayList<>());
    }

}
