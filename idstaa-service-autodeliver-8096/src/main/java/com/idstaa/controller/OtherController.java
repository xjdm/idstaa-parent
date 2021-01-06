package com.idstaa.controller;

import com.idstaa.controller.service.ResumeServiceFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chenjie
 * @date 2020/12/30 15:20
 */
@RestController
@RequestMapping("/other")
public class OtherController {
    @Autowired
    private ResumeServiceFeignClient resumeServiceFeignClient;

    @GetMapping("/test/{userId}")
    public String otherTest(@PathVariable Long userId) {
        return "otherTest";
    }

}
