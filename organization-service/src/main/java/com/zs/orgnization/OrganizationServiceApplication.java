package com.zs.orgnization;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
@RefreshScope
@SpringBootApplication
/**
 * Spring Discovery Client提供了对Load Balancer及其注册服务的最低层次访问.
 * 使用Discovery Client，可以查询通过Spring Cloud LoadBalancer注册的所有服务以及这些服务对应的URL。
 */
//@EnableDiscoveryClient
//@EnableEurekaClient
public class OrganizationServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrganizationServiceApplication.class, args);
    }
}
