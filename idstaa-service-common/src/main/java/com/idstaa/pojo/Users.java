package com.idstaa.pojo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author chenjie
 * @date 2021/1/6 22:43
 */
@Data
@Entity
@Table(name="users")
public class Users {
    @Id
    private Long id;
    private String username;
    private String password;
}
