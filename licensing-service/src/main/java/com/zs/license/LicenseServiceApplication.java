package com.zs.license;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

@SpringBootApplication
@RefreshScope
/**
 * @EnableDiscoveryClient注解是Spring Cloud的触发器，
 * 其作用是使应用程序能够使用Discovery Client和Spring Cloud LoadBalancer库
 */
@EnableDiscoveryClient
/**
 * 需要使用这个注解，以在代码中使用Feign客户端
 */
@EnableFeignClients
public class LicenseServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(LicenseServiceApplication.class, args);
    }

    /**
     * 通过使用RestTemplate类，Spring Cloud LoadBalancer将在所有服务实例之间轮询负载均衡所有请求
     * @return
     */
    @LoadBalanced
    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver localeResolver = new SessionLocaleResolver();
        // 将US设置为默认区域
        localeResolver.setDefaultLocale(Locale.US);
        return localeResolver;
    }
    @Bean
    public ResourceBundleMessageSource messageSource() {
        ResourceBundleMessageSource messageSource =
                new ResourceBundleMessageSource();
        //如果找不到消息，则不会抛出错误，而是返回消息代码
        messageSource.setUseCodeAsDefaultMessage(true);
        //设置语言属性文件的基本名称
        messageSource.setBasenames("messages");
        return messageSource;
    }
}
