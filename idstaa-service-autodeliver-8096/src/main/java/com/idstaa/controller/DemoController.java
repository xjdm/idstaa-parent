package com.idstaa.controller;

import com.idstaa.controller.service.ResumeServiceFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chenjie
 * @date 2020/12/30 15:20
 */
@RestController
@RequestMapping("/demo")
public class DemoController {
    @Autowired
    private ResumeServiceFeignClient resumeServiceFeignClient;

    @GetMapping(value = "/test/{userId}",produces = { "application/json;charset=UTF-8"})
    public String demoTest(@PathVariable Long userId) {
        Object details = SecurityContextHolder.getContext().getAuthentication().getDetails();
        return "demoTest";
    }

}
