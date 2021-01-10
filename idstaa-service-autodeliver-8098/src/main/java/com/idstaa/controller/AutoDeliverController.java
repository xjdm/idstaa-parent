package com.idstaa.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.idstaa.controller.service.ResumeServiceFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author chenjie
 * @date 2020/12/30 15:20
 */
@RestController
@RequestMapping("/autoDeliver")
public class AutoDeliverController {
    @Autowired
    private ResumeServiceFeignClient resumeServiceFeignClient;

    @SentinelResource(value = "findResumeOpenState",blockHandler ="handlerException" )
    @GetMapping(value = "/checkState/{userId}",produces = { "application/json;charset=UTF-8"})
    public Integer findResumeOpenState(@PathVariable Long userId) {
            /*String url = "http://idstaa-service-resume/resume/openstate/" + userId;
            Integer forObject = restTemplate.getForObject(url, Integer.class);
            System.out.println("========>调用建立微服务，获取到用户" + userId + "的默认建立当前状态为" + forObject);*/
            Integer defaultResumeState = resumeServiceFeignClient.findDefaultResumeState(userId);
        return defaultResumeState;
    }

    @GetMapping(value = "/checkState/{userId}/testThread",produces = { "application/json;charset=UTF-8"})
    public Integer test(@PathVariable Long userId) {
            /*String url = "http://idstaa-service-resume/resume/openstate/" + userId;
            Integer forObject = restTemplate.getForObject(url, Integer.class);
            System.out.println("========>调用建立微服务，获取到用户" + userId + "的默认建立当前状态为" + forObject);*/
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Integer defaultResumeState = resumeServiceFeignClient.findDefaultResumeState(userId);
        return defaultResumeState;
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

    public Integer handlerException(Long userId, BlockException blockException){
        return  -100;
    }
}