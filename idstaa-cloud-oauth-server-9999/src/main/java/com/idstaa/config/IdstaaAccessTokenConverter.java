package com.idstaa.config;

import io.undertow.servlet.attribute.ServletRequestAttribute;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author chenjie
 * @date 2021/1/6 23:10
 */
@Component
public class IdstaaAccessTokenConverter extends DefaultAccessTokenConverter {
    @Override
    public Map<String, ?> convertAccessToken(OAuth2AccessToken token, OAuth2Authentication authentication) {
        // 获取到对象
        HttpServletRequest request = ((ServletRequestAttributes)
                (RequestContextHolder.getRequestAttributes())).getRequest();
        // 获取客户端ip（注意：如果是经过代理之后到达当前服务的话，那么这种⽅式获取的并不是真实的浏览器客户端ip）
        String remoteAddr = request.getRemoteAddr();
        // return super.convertAccessToken(token, authentication);
        Map<String, String> stringMap = (Map<String, String>) super.convertAccessToken(token, authentication);
        stringMap.put("clientIp",remoteAddr);
        return stringMap;
    }
}
