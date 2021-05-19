package com.lank.edu.config;

import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author LanceLan
 * @since 2021/5/19 11:33
 */
@Component
public class AccessTokenConvertor extends DefaultAccessTokenConverter {


    @Override
    public Map<String, ?> convertAccessToken(OAuth2AccessToken token, OAuth2Authentication authentication) {

        // 获取到request对象
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        // 获取客户端ip
        String remoteAddr = request.getRemoteAddr();
        Map<String, String> stringMap = (Map<String, String>) super.convertAccessToken(token, authentication);
        stringMap.put("clientIp",remoteAddr);
        return stringMap;
    }
}
