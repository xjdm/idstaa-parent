package com.idstaa.dao;

import com.idstaa.pojo.Users;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author chenjie
 * @date 2021/1/6 22:46
 */
public interface UsersRepository extends JpaRepository<Users,Long> {
    Users findByUsername(String username);
}
