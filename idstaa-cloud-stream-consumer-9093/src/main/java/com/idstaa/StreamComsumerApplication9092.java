package com.idstaa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author chenjie
 * @date 2021/1/3 17:44
 */
@SpringBootApplication
@EnableDiscoveryClient
public class StreamComsumerApplication9092 {
    public static void main(String[] args) {
        SpringApplication.run(StreamComsumerApplication9092.class,args);
    }
}