package com.lank.edu.ad.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

/**
 * @author lank
 * @since 2021/5/16 18:10
 *该配置类主要处理用户名和密码校验的操作
 */
@Configuration
public class SecurityConfigure extends WebSecurityConfigurerAdapter {

    @Autowired
    private  PasswordEncoder passwordEncoder;

    /**
     * 注册一个认证管理器到spring容器
     * @return AuthenticationManager
     * @throws Exception exception
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * 处理用户名和密码的方法
     * 通常是去数据库种检验合法性
     * @param auth auth
     * @throws Exception exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // todo 关联数据库
        // 参数：用户名，密码，权限信息
        UserDetails userDetails = new User("lank","lank",new ArrayList<>());
        auth.inMemoryAuthentication()
                .withUser(userDetails)
                // 密码编码器，如md5
                .passwordEncoder(passwordEncoder);

    }

    /**
     * 密码校验对象
     * @return passwordEncoder
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        //不加密
        return NoOpPasswordEncoder.getInstance();
    }
}
