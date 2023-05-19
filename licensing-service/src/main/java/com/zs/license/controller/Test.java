package com.zs.license.controller;

import com.zs.license.config.ServiceConfig;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j2
public class Test {
    @Autowired
    ServiceConfig serviceConfig;
    @Value("${spring.datasource.username}")
    private String d;
    @RequestMapping("/test")
    public void test(){
        log.info("pro:"+serviceConfig.getProperty());
        log.info("d:"+d);
    }
}
