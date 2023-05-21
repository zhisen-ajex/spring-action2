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
package com.zs.license.clients;

import com.zs.license.model.Organization;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * The client for invoking the organization service by Netflix Feign via Ribbon
 * 
 * @author  Wuyi Chen
 * @date    03/07/2019
 * @version 1.0
 * @since   1.0
 */
@FeignClient("organization-service")
public interface OrganizationFeignClient {
	/**
     * Get the matched organization record by the organization ID by calling 
     * the organization service.
     * 
     * @param  organizationId
     *         The organization ID which is being looking for.
     *         
     * @return The matched organization record.
     */
    @RequestMapping(method= RequestMethod.GET, value="/v1/organizations/{organizationId}", consumes="application/json")
    Organization getOrganization(@PathVariable("organizationId") String organizationId);
}
