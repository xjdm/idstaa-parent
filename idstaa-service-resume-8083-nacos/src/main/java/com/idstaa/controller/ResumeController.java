package com.idstaa.controller;

import com.idstaa.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

/**
 * @author chenjie
 * @date 2020/12/30 14:43
 */
@RestController
@RequestMapping("/resume")
public class ResumeController {
    @Autowired
    private ResumeService resumeService;

    @Value("${server.port}")
    private Integer port;

    @GetMapping(value = "/openstate/{userId}",produces = { "application/json;charset=UTF-8"})
    public  Integer findDefaultResumeState(@PathVariable Long userId){
        //return resumeService.findDefaultResumeByUserId(userId).getIsOpenResume();
        return port;
    }
}
