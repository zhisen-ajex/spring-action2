package com.zs.user.management.service;

import com.zs.user.management.dto.RealmDto;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.representations.idm.RealmRepresentation;
import org.springframework.http.ResponseEntity;

import javax.ws.rs.*;
import java.util.List;

public interface IKeycloakRealm {

    public RealmResource realm(@PathParam("realm") String realm) ;


    public ResponseEntity<List<RealmRepresentation>> findAll();
    public ResponseEntity<?> postRealm(RealmDto dto);

}
