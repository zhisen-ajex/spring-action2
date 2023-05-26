package com.zs.user.management.service.impl;

import com.zs.user.management.dto.RealmDto;
import com.zs.user.management.service.IKeycloakRealm;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.representations.idm.RealmRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.ws.rs.PathParam;
import java.util.List;

@Service
public class KeycloakRealmImpl implements IKeycloakRealm {
    @Autowired
    private Keycloak keycloak;
    public RealmRepresentation realm( String realm) {
        return keycloak.realms().realm(realm).toRepresentation();
    }


    public ResponseEntity<List<RealmRepresentation>> findAll() {
        return new ResponseEntity<>(keycloak.realms().findAll(), HttpStatus.OK);
    }
    public ResponseEntity<?> postRealm(RealmDto dto) {
//        final RealmRepresentation newRealm = new RealmRepresentation();
//        newRealm.setRealm(dto.getName());
//        newRealm.setEnabled(true);
//        newRealm.setPasswordPolicy("hashAlgorithm(scramsha1)");
//        newRealm.setAttributes(new HashMap<>());
//        newRealm.getAttributes().put("namespace", namespace);
//        newRealm.getAttributes().put("enmasse-realm", "true");
        final RealmRepresentation realmRepresentation = new RealmRepresentation();
        realmRepresentation.setRealm(dto.getName());
        realmRepresentation.setId(dto.getName());
        realmRepresentation.setEnabled(Boolean.TRUE);
        keycloak.realms().create(realmRepresentation);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
