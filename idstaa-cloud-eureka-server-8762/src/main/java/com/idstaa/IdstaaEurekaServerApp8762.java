package com.idstaa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author chenjie
 * @date 2020/12/30 21:50
 */
@SpringBootApplication
@EnableEurekaServer
public class IdstaaEurekaServerApp8762 {
    public static void main(String[] args) {
        SpringApplication.run(IdstaaEurekaServerApp8762.class,args);
    }
}
