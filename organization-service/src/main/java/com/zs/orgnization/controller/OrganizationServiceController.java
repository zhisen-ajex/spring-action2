/*
 * Copyright 2019 Wuyi Chen.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.zs.orgnization.controller;

import com.zs.orgnization.model.Organization;
import com.zs.orgnization.service.OrganizationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.annotation.security.RolesAllowed;

/**
 * The controller class for defining available calls to the API endpoint of 
 * organization service.
 * 
 * @author  Wuyi Chen
 * @date    05/08/2019
 * @version 1.0
 * @since   1.0
 */
@RestController
@RequestMapping(value="v1/organizations")
public class OrganizationServiceController {
    @Autowired
    private OrganizationService orgService;
    private static final Logger logger = LoggerFactory.getLogger(OrganizationServiceController.class);

    /**
     * Query an organization by the organization ID.
     * 
     * @param  orgId
     *         The organization ID for looking up.
     *         
     * @return  The matched organization record.
     */
    //指示只有拥有USER和ADMIN角色的用户才能执行此操作, "USER"
    @RolesAllowed({ "ROLE_ADMIN" })
    @RequestMapping(value="/{organizationId}", method = RequestMethod.GET)
    public Organization getOrganization(@PathVariable("organizationId") String orgId) {
        logger.debug("Query an organization by the organization ID {}", orgId);

        return orgService.getOrg(orgId);
    }
}
