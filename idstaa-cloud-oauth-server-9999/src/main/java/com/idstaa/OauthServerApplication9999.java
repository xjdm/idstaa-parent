package com.idstaa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author chenjie
 * @date 2021/1/6 10:41
 */
@SpringBootApplication
@EnableDiscoveryClient
@EntityScan("com.idstaa.pojo")
public class OauthServerApplication9999 {
    public static void main(String[] args) {
        SpringApplication.run(OauthServerApplication9999.class,args);
    }
}
