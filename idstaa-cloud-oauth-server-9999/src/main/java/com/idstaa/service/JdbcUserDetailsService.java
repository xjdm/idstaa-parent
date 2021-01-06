package com.idstaa.service;

import com.idstaa.dao.UsersRepository;
import com.idstaa.pojo.Users;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * @author chenjie
 * @date 2021/1/6 22:41
 */
@Service
public class JdbcUserDetailsService implements UserDetailsService {
    @Autowired
    private UsersRepository usersRepository;
    /**
     * 根据username查询出改用户的所有信息，封装成UserDetails类型的对象返回，至于密码，框架会自动匹配
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users users = usersRepository.findByUsername(username);
        return new User(username, users.getPassword(),new ArrayList<>());
    }
}
