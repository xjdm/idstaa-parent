package com.idstaa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;

/**
 * @author chenjie
 * @date 2021/1/3 13:57
 */
@SpringBootApplication
@EntityScan("com.idstaa.pojo")
@EnableDiscoveryClient
public class IdstaaResumeApplication8080 {
    public static void main(String[] args) {
        SpringApplication.run(IdstaaResumeApplication8080.class,args);
    }
}
