package com.idstaa.controller.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author chenjie
 * @date 2021/1/2 17:59
 */
// @FeignClient表明当前类是一个Feign客户端，value指定该客户端要请求的服务的名称（登记到注册中心上的服务提供者的服务名称）
@FeignClient(value="idstaa-service-resume",path = "/resume")
// @RequestMapping("/resume")
public interface ResumeServiceFeignClient {
    // Feign要做的事情，拼装url发起请求
    @GetMapping(value = "/openstate/{userId}",produces = { "application/json;charset=UTF-8"})
    public  Integer findDefaultResumeState(@PathVariable("userId") Long userId);
}
