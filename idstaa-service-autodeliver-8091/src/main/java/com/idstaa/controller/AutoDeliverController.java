package com.idstaa.controller;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author chenjie
 * @date 2020/12/30 15:20
 */
@RestController
@RequestMapping("/autoDeliver")
public class AutoDeliverController {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;
    // /autoDeliver/checkState/1545346
   /* @GetMapping("/checkState/{userId}")
    public Integer findResumeOpenState(@PathVariable Long userId) {
       *//* Integer forObject =
                restTemplate.getForObject("http://localhost:8080/resume/openstate/"
                        + userId, Integer.class);
        System.out.println("======>>>调⽤简历微服务，获取到⽤户" +
                userId + "的默认简历当前状态为： " + forObject);
        return forObject;*//*
       // 1、获取Eureka中注册user-service实例列表
        List<ServiceInstance> instances = discoveryClient.getInstances("idstaa-service-resume");
        // 2、 获取实例
        ServiceInstance serviceInstance = instances.get(0);
        //3、根据实例的信息拼接请求地址
        String host = serviceInstance.getHost();
        int port = serviceInstance.getPort();
        String url = "http://" + host + ":" + port +"/resume/openstate/" + userId;
        //4、消费之直接调用提供者
        Integer forObject = restTemplate.getForObject(url, Integer.class);
        System.out.println("========>调用建立微服务，获取到用户" + userId + "的默认建立当前状态为" + forObject);
        return forObject;
    }*/

    /**
     * 使用Ribbon负载均衡
     *
     * @param userId
     * @return
     */
    @GetMapping("/checkState/{userId}")
    public Integer findResumeOpenState(@PathVariable Long userId) {
        String url = "http://idstaa-service-resume/resume/openstate/" + userId;
        Integer forObject = restTemplate.getForObject(url, Integer.class);
        System.out.println("========>调用建立微服务，获取到用户" + userId + "的默认建立当前状态为" + forObject);
        return forObject;
    }

    /**
     * 提供者模拟处理超时，调用方法添加Hystrix控制
     *
     * @param userId
     * @return
     */
    // 使用@HystrixCommand 注解进行熔断控制
    @HystrixCommand(
            // 线程池标识，要保持唯一，不唯一的话就共用了
            threadPoolKey = "findResumeOpenStateTimeout",
            threadPoolProperties = {
                    @HystrixProperty(name = "coreSize", value = "1"),// 线程数
                    @HystrixProperty(name = "maxQueueSize", value = "20")
            },
            // 使⽤@HystrixCommand注解进⾏熔断控制
            commandProperties = {
                    // 每一个属性都是一个HystrixProperty
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000")
            }
    )
    @GetMapping("/checkStateTimeout/{userId}")
    public Integer findResumeOpenStateTimeout(@PathVariable Long userId) {
        String url = "http://idstaa-service-resume/resume/openstate/" + userId;
        Integer forObject = restTemplate.getForObject(url, Integer.class);
        System.out.println("========>调用建立微服务，获取到用户" + userId + "的默认建立当前状态为" + forObject);
        return forObject;
    }

    /**
     * 1）服务提供者处理超时，熔断，返回错误信息
     * 2）有可能服务提供者出现异常直接抛出异常信息
     * <p>
     * 以上信息，都会返回到消费者这里，很多时候消费者服务不希望收到异常/错误信息再抛到它的上游去
     * 用户微服务 - 注册微服务 - 优惠券服务
     * 1 登记注册
     * 2 分发优惠券 （并不是核心步骤） 这里如果调用优惠券微服务返回了异常信息或着是熔断后的错误信息，
     * 这些信息如果抛给用户很不好，此时，我们可以返回一个兜底的数据，预设默认值
     */
    @HystrixCommand(
            // 线程池标识，要保持唯一，不唯一的话就共用了
            threadPoolKey = "findResumeOpenStateTimeoutFallBack",
            threadPoolProperties = {
                    @HystrixProperty(name = "coreSize", value = "2"),// 线程数
                    @HystrixProperty(name = "maxQueueSize", value = "20")
            },
            // 使⽤@HystrixCommand注解进⾏熔断控制
            commandProperties = {
                    // 每一个属性都是一个HystrixProperty
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000"),
                    // hystrix高级配置，定制工作过程细节
                    // 统计时间窗口定义
                    @HystrixProperty(name =
                            "metrics.rollingStats.timeInMilliseconds", value = "8000"),
                    // 统计时间窗口内的最小请求书
                    @HystrixProperty(name =
                            "circuitBreaker.requestVolumeThreshold", value = "2"),
                    // 统计时间窗口内的错误数量百分百阈值
                    @HystrixProperty(name =
                            "circuitBreaker.errorThresholdPercentage", value = "50"),
                    // 自我修复时的活动窗口长度
                    @HystrixProperty(name =
                            "circuitBreaker.sleepWindowInMilliseconds", value = "3000")
            }, fallbackMethod = "MyFallBack"
    )
    @GetMapping("/checkStateTimeoutFallBack/{userId}")
    public Integer findResumeOpenStateTimeoutFallBack(@PathVariable Long userId) {
        String url = "http://idstaa-service-resume/resume/openstate/" + userId;
        Integer forObject = restTemplate.getForObject(url, Integer.class);
        System.out.println("========>调用建立微服务，获取到用户" + userId + "的默认建立当前状态为" + forObject);
        return forObject;
    }

    /*
    定义回退方法，返回默认值
    注意：该方法形参和返回值与原始方法保持一致
     */
    public Integer MyFallBack(Long userId) {
        return -1;
    }
}
