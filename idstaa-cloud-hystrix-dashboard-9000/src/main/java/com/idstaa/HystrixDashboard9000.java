package com.idstaa;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Bean;

/**
 * @author chenjie
 * @date 2021/1/2 11:16
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableHystrixDashboard // 开启hystrix dashboard
public class HystrixDashboard9000 {
    public static void main(String[] args) {
        SpringApplication.run(HystrixDashboard9000.class,args);
    }
}
