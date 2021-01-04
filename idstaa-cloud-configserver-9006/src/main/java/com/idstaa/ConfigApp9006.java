package com.idstaa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @author chenjie
 * @date 2021/1/3 11:37
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableConfigServer // 开启配置服务器功能
public class ConfigApp9006 {
    public static void main(String[] args) {
        SpringApplication.run(ConfigApp9006.class,args);
    }
}