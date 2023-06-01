package com.zs.license.controller;

import com.zs.license.config.ServiceConfig;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Log4j2
public class Test {
    @Autowired
    private DiscoveryClient discoveryClient;
    @Autowired
    ServiceConfig serviceConfig;
    @Value("${spring.datasource.username}")
    private String d;
    @RequestMapping("/test")
    public void test(){
        log.info("pro:"+serviceConfig.getProperty());
        log.info("d:"+d);
    }
    @GetMapping("/serviceurl")
    public Map<String, List<ServiceInstance>> serviceUrl() {
        Map<String, List<ServiceInstance>> msl = new HashMap<>();
        List<String> services = discoveryClient.getServices();
        for (String service : services) {
            List<ServiceInstance> sis = discoveryClient.getInstances(service);
            msl.put(service, sis);
        }
        return msl;
    }
}
