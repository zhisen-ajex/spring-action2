package com.zs.license.controller;

import com.zs.license.model.License;
import com.zs.license.service.LicenseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

/**
 *
 */
//告诉Spring Boot这是一个基于REST的服务，它将自动以JSON方式序列化/反序列化服务请求/响应
@RestController
//在这个类中使用前缀/v1/organization/ {organizationId}/license公开所有HTTP端点
@RequestMapping(value="v1/organization/{organizationId}/license")
public class LicenseController {

    @Autowired
    private LicenseService licenseService;
    @Autowired
    MessageSource messages;
    private static final Logger logger = LoggerFactory.getLogger(LicenseController.class);

    /**
     *  clientType参数确定要使用的Spring REST客户端的类型
     * @param organizationId
     * @param licenseId
     * @param clientType
     * @return
     */
    @RequestMapping(value="/{licenseId}/{clientType}",
            method = RequestMethod.GET)
    public License getLicensesWithClient(
            @PathVariable("organizationId") String organizationId,
            @PathVariable("licenseId") String licenseId,
            @PathVariable("clientType") String clientType) {
        return licenseService.getLicense(licenseId,organizationId, clientType);
    }

    /**
     * Query a license by the organization ID and the license ID.
     *
     * @param  orgId
     *         The organization ID for looking up.
     *
     * @param  licenseId
     *         The license ID for looking up.
     *
     * @return  The matched license record.
     */
    @RequestMapping(value = "/{licenseId}", method = RequestMethod.GET)
    public License getLicenses(@PathVariable("organizationId") String orgId, @PathVariable("licenseId") String licenseId,
                               @RequestHeader(value = "Accept-Language",required = false)
                                       Locale locale) {
        logger.debug("Query a license by the organization ID and the license ID {} {}", orgId, licenseId);

        return licenseService.getLicense(orgId, licenseId,locale);
    }


    /**
     * Update a license by the license ID.
     *
     * @param  licenseId
     *         The license ID for looking up.
     *
     * @param  license
     *         The license information needs to be updated to.
     */
    @RequestMapping(value = "/{licenseId}", method = RequestMethod.PUT)
    public void updateLicenses(@PathVariable("licenseId") String licenseId, @RequestBody License license) {
        licenseService.updateLicense(license);
    }

    /**
     * Add a new license.
     *
     * @param  license
     *         The new license needs to be added.
     */
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public void saveLicenses(@RequestBody License license) {
        licenseService.createLicense(license);
    }

    /**
     * Delete a license by the license ID.
     *
     * @param  licenseId
     *         The license ID for identifying the record needs to be deleted.
     */
    @RequestMapping(value = "/{licenseId}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteLicenses(@PathVariable("licenseId") String licenseId) {
        licenseService.deleteLicense(licenseId);
    }
}
