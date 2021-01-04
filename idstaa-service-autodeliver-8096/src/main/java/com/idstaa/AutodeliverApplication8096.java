package com.idstaa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author chenjie
 * @date 2020/12/30 15:23
 */
@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients  // 开启Feign客户端功能
public class AutodeliverApplication8096 {
    public static void main(String[] args) {
        SpringApplication.run(AutodeliverApplication8096.class, args);
    }
}