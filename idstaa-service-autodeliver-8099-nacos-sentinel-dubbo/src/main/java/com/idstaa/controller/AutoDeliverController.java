package com.idstaa.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.idstaa.config.SentinelHandlersClass;
import com.idstaa.service.impl.ResumeService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chenjie
 * @date 2020/12/30 15:20
 */
@RestController
@RequestMapping("/autoDeliver")
public class AutoDeliverController {
    @Reference
    private ResumeService resumeService;

    @SentinelResource(value = "findResumeOpenState"
            ,blockHandlerClass = SentinelHandlersClass.class,blockHandler ="handlerException",
            fallback ="handleError",fallbackClass = SentinelHandlersClass.class)
    @GetMapping(value = "/checkState/{userId}",produces = { "application/json;charset=UTF-8"})
    public Integer findResumeOpenState(@PathVariable Long userId) {
        return resumeService.findDefaultResumeByUserId(userId);
    }
}
