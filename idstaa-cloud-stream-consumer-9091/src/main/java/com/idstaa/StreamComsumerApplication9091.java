package com.idstaa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author chenjie
 * @date 2021/1/3 17:15
 */
@SpringBootApplication
@EnableDiscoveryClient
public class StreamComsumerApplication9091 {
    public static void main(String[] args) {
        SpringApplication.run(StreamComsumerApplication9091.class,args);
    }
}
