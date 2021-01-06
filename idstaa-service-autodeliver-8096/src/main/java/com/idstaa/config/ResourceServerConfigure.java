package com.idstaa.config;

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
 * @author chenjie
 * @date 2021/1/6 16:24
 */
@Configuration
@EnableResourceServer // 开启资源服务器功能
@EnableWebSecurity // 开启web访问安全
public class ResourceServerConfigure extends ResourceServerConfigurerAdapter {

    /**
     * 改方法用于创建tokenStore对象（令牌存储对象）
     * token以什么形式存储
     */
    public TokenStore tokenStore() {
        // return new InMemoryTokenStore();
        return new JwtTokenStore(jwtAccessTokenConverter());
    }

    private String sign_key = "idstaaidstaa";
    /**
     * 返回jwt令牌转换器（帮助我们⽣成jwt令牌的）
     * 在这⾥，我们可以把签名密钥传递进去给转换器对象
     * @return
     */
    public JwtAccessTokenConverter jwtAccessTokenConverter(){
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        jwtAccessTokenConverter.setSigningKey(sign_key); // 签名密钥
        jwtAccessTokenConverter.setVerifier(new MacSigner(sign_key)); // 签名密钥
        return jwtAccessTokenConverter;
    }

    /**
     * 该方法用于定义资源服务器向远程认证服务器发起请求，进行token校验等事宜
     *
     * @param resources
     * @throws Exception
     */
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        // 设置当前资源服务的资源id
       /* resources.resourceId("autoDeliver");

        // 定义token的服务对象（token校验就应该靠token服务对象）
        RemoteTokenServices remoteTokenServices = new RemoteTokenServices();
        // 校验端点/接口设置
        remoteTokenServices.setCheckTokenEndpointUrl("http://localhost:9999/oauth/check_token");
        // 携带客户端ip和客户端安全码
        remoteTokenServices.setClientId("client_idstaa");
        remoteTokenServices.setClientSecret("abcxyz");

        resources.tokenServices(remoteTokenServices);*/
        resources.resourceId("autoDeliver").tokenStore(tokenStore()).stateless(true);

    }

    /**
     * 场景：一个服务中可能有很多资源（API资源）
     * 某一些API接口，需要先认证，才能访问
     * 某一些API接口，压根就不需要认证，本来就是对外开放的接口
     * 我们就需要对不同特点的接口区分对待（在当前configure方法中完成），设置是否需要认证
     *
     * @param http
     * @throws Exception
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http    //设置Session 的创建策略
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .and()
                .authorizeRequests()
                .antMatchers("/autoDeliver/**").authenticated()  // autoDeliver为前缀的请求需要认证
                .antMatchers("/demo/**").authenticated() // demo为前缀的请求需要认证
                .anyRequest().permitAll();
    }
}
