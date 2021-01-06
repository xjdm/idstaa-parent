package com.idstaa.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.jwt.crypto.sign.MacSigner;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 * @author chenjie
 * @date 2021/1/6 10:45
 * 当前类为Oauth2 server 的配置类（需要继承特定的父类）
 */
@Configuration
@EnableAuthorizationServer // 开启认证服务器功能
public class OauthServerConfigure extends AuthorizationServerConfigurerAdapter {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        super.configure(security);
        // 相当于打开endpoints 访问接口的开关，这样的话后期我们能够访问该接口
        security
                // 允许客户端表单认证
                .allowFormAuthenticationForClients()
                // 开启端口/oauth/token_key的访问权限（允许）
                .tokenKeyAccess("permitAll()")
                // 开启端口/oauth/check_token的权限
                .checkTokenAccess("permitAll()");
    }

    /**
     * 客户端详情配置，
     * 比如client_id，secret
     * 当前这个服务就如同QQ平台，拉勾网作为客户端需要qq平台进行登录授权认证等，提取需要到QQ平台注册
     * QQ平台会给拉勾网颁发client_id等必要参数，表明客户端是谁
     *
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        super.configure(clients);
        clients.inMemory() // 客户端信息存储在什么地方，可以在内存中，可以再数据库里
                .withClient("client_idstaa")// 添加一个client配置，指定其client_id
                .secret("abcxyz") // 指定客户端的密码/安全码
                .resourceIds("autoDeliver")// 指定客户端所能访问资源id清单，此处的资源id是需要在具体的资源服务器上也配置一样
                // 认证类型/令牌颁发模式，可以配置多个在这里，但是不一定都用，具体使用哪种方式颁发token，需要客户端调用的时候传递的参数指定
                .authorizedGrantTypes("password", "refresh_token")
                // 客户端的权限范围，此处配置为all全部即可
                .scopes("all");
    }

    /**
     * 认证服务器是玩转token的，那么这里配置token令牌管理相关（token此时就是一个字符串，当下的token需要在服务器
     * 端存储）
     *
     * @param endpoints
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        super.configure(endpoints);
        endpoints.tokenStore(tokenStore())  // 指定token的存储方式
                .tokenServices(authorizationServerTokenServices())    // token的服务的一个描述，可以认为是token生成细节的描述，比如有效时间多少等
                .authenticationManager(authenticationManager) //指定认证管理器，随后注入一到当前类即可
                .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST,HttpMethod.TRACE);

    }

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
     * 该方法用于获取一个token服务对象（改对象描述了token有效期等信息）
     */
    public AuthorizationServerTokenServices authorizationServerTokenServices() {
        // 使用默认实现
        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setSupportRefreshToken(true); // 是否开启令牌刷新
        defaultTokenServices.setTokenStore(tokenStore());
        defaultTokenServices.setTokenEnhancer(jwtAccessTokenConverter());
        // 设置令牌有效时间
        defaultTokenServices.setAccessTokenValiditySeconds(2000);//access_token就是我们请求资源需要携带的令牌
        // 设置刷新令牌的有效时间
        defaultTokenServices.setRefreshTokenValiditySeconds(3 * 24 * 3600);//3天
        return defaultTokenServices;
    }
}
