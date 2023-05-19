package com.zs.configserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/*我们的Config服务是一个Spring Boot应用程序，因此必须用@SpringBootApplication注解进行标注*/
@SpringBootApplication
/*使这个服务成为Spring Cloud Config服务*/
@EnableConfigServer

public class ConfigurationServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConfigurationServerApplication.class, args);
    }
}