package com.lank;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.jwt.crypto.sign.MacSigner;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;


/**
 * @author lank
 * @since 2021/5/16 19:15
 */
@Configuration
@EnableResourceServer  // 开启资源服务器功能
@EnableWebSecurity  // 开启web访问安全
public class ResourcesServerConfigure extends ResourceServerConfigurerAdapter {

    // jwt 签名key
    private String sign_key = "jwt_key";

    /**
     * 用于资源服务器(该服务)向远程认证服务器发起请求，进行token校验
     *
     * @param resources resources
     * @throws Exception exception
     */
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        //资源id
//        resources.resourceId("edu-ad-boot");
//        // 定义token服务对象
//        RemoteTokenServices remoteTokenServices = new RemoteTokenServices();
//        // 校验端点/接口设置
//        remoteTokenServices.setCheckTokenEndpointUrl("http://localhost:9999/oauth/check_token");
//        remoteTokenServices.setClientId("client_ad");
//        remoteTokenServices.setClientSecret("lank");
//        resources.tokenServices(remoteTokenServices);

        // jwt改造
        resources.resourceId("oauth2-demo")
                .tokenStore(jwtTokenStore())
                .stateless(true);
    }

    private TokenStore jwtTokenStore(){
        return new JwtTokenStore(jwtAccessTokenConverter());
    }


    private JwtAccessTokenConverter jwtAccessTokenConverter(){
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        jwtAccessTokenConverter.setSigningKey(sign_key);
        jwtAccessTokenConverter.setVerifier(new MacSigner(sign_key));
        return jwtAccessTokenConverter;
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
