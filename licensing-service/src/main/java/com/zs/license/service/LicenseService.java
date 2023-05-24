package com.zs.license.service;


import com.zs.license.clients.OrganizationDiscoveryClient;
import com.zs.license.clients.OrganizationFeignClient;
import com.zs.license.clients.OrganizationRestTemplateClient;
import com.zs.license.config.ServiceConfig;
import com.zs.license.model.Organization;
import com.zs.license.repository.LicenseRepository;
import com.zs.license.utils.UserContextHolder;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.zs.license.model.License;

import java.util.*;
import java.util.concurrent.TimeoutException;

@Service
public class LicenseService {
    @Autowired
    MessageSource messages;
    @Autowired
    private LicenseRepository licenseRepository;
    @Autowired
    ServiceConfig config;
    @Autowired
    OrganizationFeignClient organizationFeignClient;

    @Autowired
    OrganizationRestTemplateClient organizationRestClient;

    @Autowired
    OrganizationDiscoveryClient organizationDiscoveryClient;
    private static final Logger logger = LoggerFactory.getLogger(LicenseService.class);
    /**
     * Get the organization record by an organization ID.
     *
     * <p>There are 3 types of clients:
     * <ul>
     *  <li>discovery: Spring DiscoveryClient with standard Spring RestTemplate.
     *  <li>rest: Ribbon-backed Spring RestTemplate.
     *  <li>feign: Netflix Feign via Ribbon.
     * </ul>
     *
     * @param  organizationId
     *
     * @param clientType
     * @return
     */
    private Organization retrieveOrganizationInfo(String organizationId, String clientType){
        Organization organization = null;

        switch (clientType) {
            case "feign":
                logger.info("I am using the feign client");
                organization = organizationFeignClient.getOrganization(organizationId);
                break;
            case "rest":
                logger.info("I am using the rest client");
                organization = organizationRestClient.getOrganization(organizationId);
                break;
            case "discovery":
                logger.info("I am using the discovery client");
                organization = organizationDiscoveryClient.getOrganization(organizationId);
                break;
            default:
                organization = organizationRestClient.getOrganization(organizationId);
        }

        return organization;
    }
    public License getLicense(String licenseId, String organizationId, String
            clientType){
        License license = licenseRepository.findByOrganizationIdAndLicenseId(organizationId, licenseId);
        if (null == license) {
            throw new IllegalArgumentException(String.format(
                    messages.getMessage("license.search.error.message", null, null),
                    licenseId, organizationId));
        }
        Organization organization = retrieveOrganizationInfo(organizationId,
                clientType);
        if (null != organization) {
            license.setOrganizationName(organization.getName());
            license.setContactName(organization.getContactName());
            license.setContactEmail(organization.getContactEmail());
            license.setContactPhone(organization.getContactPhone());
        }
        return license.withComment(config.getProperty());
    }
    public License getLicense(String licenseId, String organizationId, Locale locale){
        License license = licenseRepository
                .findByOrganizationIdAndLicenseId(organizationId, licenseId);
        if (null == license) {
            throw new IllegalArgumentException(
                    String.format(messages.getMessage(
                                    "license.search.error.message", null, locale),
                            licenseId, organizationId));
        }
        return license.withComment(config.getProperty());
    }

    public License createLicense(License license){
        license.setLicenseId(UUID.randomUUID().toString());
        licenseRepository.save(license);
        return license.withComment(config.getProperty());
    }

    public License updateLicense(License license){
        licenseRepository.save(license);
        return license.withComment(config.getProperty());
    }

    public String deleteLicense(String licenseId){
        String responseMessage = null;
        License license = new License();
        license.setLicenseId(licenseId);
        licenseRepository.delete(license);
        responseMessage = String.format(messages.getMessage(
                "license.delete.message", null, null),licenseId);
        return responseMessage;
    }
    //@CircuitBreaker使用Resilience4j断路器对 getLicensesByOrganization()进行了包装
    //定义了buildFallbackLicenseList方法，如果调用服务失败，那么就会调用该方法
    //@CircuitBreaker(name = "licenseService", fallbackMethod= "buildFallbackLicenseList")
    //设置重试模式的实例名和回退方法
    @Retry(name = "retryLicenseService",fallbackMethod="buildFallbackLicenseList")
    //为舱壁模式设置实例名称和后备方法 （@Bulkhead这个注解表示我们正在设置舱壁模式）
    // @Bulkhead(name= "bulkheadLicenseService",type = Bulkhead.Type.THREADPOOL, fallbackMethod= "buildFallbackLicenseList")
    public List<License> getLicensesByOrganization(String organizationId) throws TimeoutException {
        //randomlyRunLong();
        logger.info("getLicensesByOrganization Correlation id: {}",
                UserContextHolder.getContext().getCorrelationId());
        sleep();
        return licenseRepository.findByOrganizationId(organizationId);
    }

    private List<License> buildFallbackLicenseList(String organizationId,Throwable t){
        logger.info("getLicensesByOrganization buildFallbackLicenseList: {}",t.getMessage());
        List<License> fallbackList = new ArrayList<>();
        License license = new License();
        license.setLicenseId("0000000-00-00000");
        license.setOrganizationId(organizationId);
        license.setProductName(
                "Sorry no licensing information currently available");
        fallbackList.add(license);
        return fallbackList;
    }

    // 有三分之一的概率数据库调用会持续很长时间
    private void randomlyRunLong() throws TimeoutException {
        Random rand = new Random();
        int randomNum = rand.nextInt(3) + 1;
        if (randomNum==3) sleep();
    }

    private void sleep() throws TimeoutException {
        try {
            //睡眠5000毫秒（5秒），然后抛出一个TimeoutException
            Thread.sleep(5000);
            throw new java.util.concurrent.TimeoutException();
        } catch (InterruptedException e) {
            logger.error(e.getMessage());
        }
    }
}