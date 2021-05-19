package com.lank.edu.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;


/**
 * @author lank
 * @since 2021/5/16 19:15
 */
@Configuration
@EnableResourceServer  // 开启资源服务器功能
@EnableWebSecurity  // 开启web访问安全
public class ResourcesServerConfigure extends ResourceServerConfigurerAdapter {

    /**
     * 用于资源服务器(该服务)向远程认证服务器发起请求，进行token校验
     *
     * @param resources resources
     * @throws Exception exception
     */
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        //资源id
        resources.resourceId("edu-ad-boot");
        // 定义token服务对象
        RemoteTokenServices remoteTokenServices = new RemoteTokenServices();
        // 校验端点/接口设置
        remoteTokenServices.setCheckTokenEndpointUrl("http://localhost:9999/oauth/check_token");
        remoteTokenServices.setClientId("client_ad");
        remoteTokenServices.setClientSecret("lank");
        resources.tokenServices(remoteTokenServices);

    }

    /**
     * 标明哪些接口需要认证
     *
     * @param http http
     * @throws Exception exception
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http     // 设置session创建状态(根据需要)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .and()
                .authorizeRequests()
                // ad开头的请求需要认证
                .antMatchers("/ad/**").authenticated()
                .antMatchers("/demo/**").authenticated()
                // 其他请求不需要认证
                .anyRequest().permitAll();

    }
}
