
package com.zs.orgnization.service;

import com.zs.orgnization.model.Organization;
import com.zs.orgnization.repository.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

/**
 * The organization service for processing operations on organization.
 *
 * @author  Wuyi Chen
 * @date    02/14/2019
 * @version 1.0
 * @since   1.0
 */
@Service
public class OrganizationService {
    @Autowired
    private OrganizationRepository orgRepository;                    // for database operations

/*
    @Autowired
    private Tracer                 tracer;                           // for sending custom span to zipkin server

    @Autowired
    private SimpleSourceBean       simpleSourceBean;                 // for publishing organization change event to the message queue
*/

    /**
     * Query an organization by the organization ID.
     * 
     * @param  orgId
     *         The organization ID for looking up.
     *
     * @return  The matched organization record.
     */
    public Organization getOrg(String orgId) {
        //Span newSpan = tracer.createSpan("getOrgDBCall");            // create a new span for send custom span to zipkin server
    	
    	try {
            if (orgRepository.findById(orgId).isPresent()) {
                return orgRepository.findById(orgId).get();
            }else {
                return null;
            }
    	} finally {
/*    		newSpan.tag("peer.service", "mysql");
    		newSpan.logEvent(Span.CLIENT_RECV);
    		tracer.close(newSpan);*/
    	}
    }

}
