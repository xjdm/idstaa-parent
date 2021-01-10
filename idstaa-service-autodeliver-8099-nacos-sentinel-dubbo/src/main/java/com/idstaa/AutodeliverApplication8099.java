package com.idstaa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author chenjie
 * @date 2020/12/30 15:23
 */
@EnableDiscoveryClient
@SpringBootApplication
public class AutodeliverApplication8099 {
    public static void main(String[] args) {
        SpringApplication.run(AutodeliverApplication8099.class, args);
    }
}