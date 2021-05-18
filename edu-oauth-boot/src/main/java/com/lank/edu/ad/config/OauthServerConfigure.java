package com.lank.edu.ad.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.jwt.crypto.sign.MacSigner;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * @author LanceLan
 * @since 2021/5/14 17:48
 * Oauth2 server的配置类 需要继承AuthorizationServerConfigurerAdapter
 */
@Configuration
@EnableAuthorizationServer // 开启认证服务
public class OauthServerConfigure extends AuthorizationServerConfigurerAdapter {

    @Resource
    private AuthenticationManager authenticationManager;

    @Resource
    private DataSource dataSource;

    private String signKey = "";

    /**
     * 认证服务器最终式以api接口的方式对外提供服务
     * （校验合法性并生成token）
     * 以api接口方式对外的话，就涉及到接口的访问权限，需要在这里配置
     *
     * @param security security
     * @throws Exception exception
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        super.configure(security);
        // 打开endpoints 访问接口的开关
        // 允许表单提交
        security.allowFormAuthenticationForClients()
                // 
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("permitAll()");
    }

    /**
     * 客户端详情配置，比如client_id,secret
     *
     * @param clients 客户端
     * @throws Exception exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        super.configure(clients);
        // 客户端信息存在内存，也可以放在数据库
//        clients.inMemory()
//                // 添加一个客户端，指定client_id
//                .withClient("client_ad")
//                // 指定客户端密码
//                .secret("lank")
//                //指定客户端能访问资源id清单
//                .resourceIds("edu-ad-boot")
//                // 授权模式，令牌颁发模式，可以配置多个，具体使用哪个得看客户端调用时传递
//                .authorizedGrantTypes("password", "refresh_token")
//                // 客户端授权范围
//                .scopes("all");

        // jwt改造
        //把从内存中获取客户端详情改为从数据库中获取
        clients.withClientDetails(jdbcClientDetailsService());
    }

    public JdbcClientDetailsService jdbcClientDetailsService(){
        return new JdbcClientDetailsService(dataSource);
    }

    /**
     * 配置token令牌管理相关
     * （token此时就是一个字符串，当下token需要再服务器端存储）
     *
     * @param endpoints 令牌
     * @throws Exception exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        super.configure(endpoints);
        // 指定token存储方式
        endpoints
                //.tokenStore(tokenStore())
                .tokenStore(jwtTokenStore())
                // token服务得描述，token生成细节的描述，比如token有效期
                .tokenServices(authorizationServerTokenServices())
                // 指定认证管理器，随后注入一个到当前类使用即可
                .authenticationManager(authenticationManager)
                .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST);
    }

    /**
     * 创建tokenStore对象(令牌存储对象)
     * token以什么方式存储
     * @return tokenStore
     */
    private TokenStore tokenStore() {
        // 内存存储
        return new InMemoryTokenStore();
    }


    /**
     * 创建tokenStore对象(令牌存储对象)
     * token以什么方式存储
     * @return tokenStore
     */
    public TokenStore jwtTokenStore(){
        return new JwtTokenStore(jwtAccessTokenConverter());
    }

    /**
     * 返回jwt令牌转换器
     */
    private JwtAccessTokenConverter jwtAccessTokenConverter(){
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        // 签名密钥
        jwtAccessTokenConverter.setSigningKey(signKey);
        // 验证时使用的密钥和签名的时候保持一致
        jwtAccessTokenConverter.setVerifier(new MacSigner(signKey));
        return jwtAccessTokenConverter;
    }

    /**
     * 该⽅法⽤户获取⼀个token服务对象（该对象描述了token有效期等信息）
     * @return authorizationServerTokenServices
     */
    private AuthorizationServerTokenServices authorizationServerTokenServices() {
        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        // 开始令牌刷新
        defaultTokenServices.setSupportRefreshToken(true);
        defaultTokenServices.setTokenStore(jwtTokenStore());
        // 针对jwt令牌的添加
        defaultTokenServices.setTokenEnhancer(jwtAccessTokenConverter());
        // 设置令牌有效时间
        defaultTokenServices.setAccessTokenValiditySeconds(20);
        // 刷新令牌有效时间
        defaultTokenServices.setRefreshTokenValiditySeconds(60);
        return defaultTokenServices;
    }

}
