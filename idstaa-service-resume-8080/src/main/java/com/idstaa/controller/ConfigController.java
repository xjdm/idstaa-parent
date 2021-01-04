package com.idstaa.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chenjie
 * @date 2021/1/3 11:59
 */
@RestController
@RequestMapping("/config")
@RefreshScope
public class ConfigController {
    @Value("${idstaa.message}")
    private String idstaaMessage;
    @Value("${mysql.url}")
    private String mysqlUrl;

    // 内存级别的配置信息
    // 数据库，redis配置信息
    @GetMapping("/viewconfig")
    public String viewConfig(){
        return "idstaaMessage==>"+idstaaMessage +"mysqlUrl-->"+mysqlUrl;
    }


}
