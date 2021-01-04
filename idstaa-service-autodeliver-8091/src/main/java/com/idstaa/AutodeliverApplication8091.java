package com.idstaa;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @author chenjie
 * @date 2020/12/30 15:23
 */
@EnableDiscoveryClient
@SpringBootApplication
// @EnableHystrix 开启Hystrix功能
@EnableCircuitBreaker // 开启熔断器功能
// @SpringCloudApplication 综合性注解（
// @SpringCloudApplication = @EnableDiscoveryClient + @SpringBootApplication + @EnableCircuitBreaker ）
public class AutodeliverApplication8091 {
    public static void main(String[] args) {
        SpringApplication.run(AutodeliverApplication8091.class, args);
    }

    /**
     * 注⼊RestTemplate
     *
     * @return
     */
    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }


    /**
     * 在被监控的微服务中注册一个servlet，后期我们就是通过访问这个servlet来获取该服务的Hystrix监控数据的
     * 前提： 被监控的微服务需要引入Springboot 的actuator功能
     * @return
     */
    @Bean
    public ServletRegistrationBean getServlet(){
        HystrixMetricsStreamServlet streamServlet = new
                HystrixMetricsStreamServlet();
        ServletRegistrationBean registrationBean = new
                ServletRegistrationBean(streamServlet);
        registrationBean.setLoadOnStartup(1);
        registrationBean.addUrlMappings("/actuator/hystrix.stream");
        registrationBean.setName("HystrixMetricsStreamServlet");
        return registrationBean;
    }
}