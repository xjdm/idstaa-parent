package com.idstaa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author chenjie
 * @date 2020/12/30 14:46
 */
@SpringBootApplication
@EntityScan("com.idstaa.pojo")
@EnableDiscoveryClient
public class IdstaaResumeApplication8082 {
    public static void main(String[] args) {
        SpringApplication.run(IdstaaResumeApplication8082.class,args);
    }
}
