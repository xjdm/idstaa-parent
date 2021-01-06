package com.idstaa.config;

import com.idstaa.service.JdbcUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.cglib.proxy.NoOp;
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
 * 改配置类，主要处理用户名和密码的校验等事宜
 * @author chenjie
 * @date 2021/1/6 11:21
 */
@Configuration
public class SecurityConfigure extends WebSecurityConfigurerAdapter {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JdbcUserDetailsService jdbcUserDetailsService;

    /**
     * 注册一个认证管理器对象到容器
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * 处理用户名和密码验证事宜
     * 1）客户端传递username 和 password参数到认证服务器
     * 2）一般来说，usernam 和password 会存储在数据库中的用户表中
     * 3）根据用户表中的数据，验证当前传递过来的用户信息的合法性
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 在这个方法中就可以去关联数据了，当前我们先把用户信息配置在内存中
        // 实例化一个用户对象(相当于数据表中的一条数据)
/*        UserDetails user = new User("admin","123456",new ArrayList<>());
        auth.inMemoryAuthentication()
                .withUser(user).passwordEncoder(passwordEncoder);*/
        auth.userDetailsService(jdbcUserDetailsService).passwordEncoder(passwordEncoder);
    }

    /**
     * 密码编码对象
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }
}
