package com.zs.user.management.controller;

import com.zs.user.management.dto.RealmDto;
import com.zs.user.management.dto.RolDto;
import com.zs.user.management.service.KeycloakRolService;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.representations.idm.RealmRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(path = "/api")
@CrossOrigin(allowedHeaders = "*", origins = "*")
public class KeycloakRealmController {

    @Autowired
    private KeycloakRolService service;
    @Autowired
    private Keycloak keycloak;
    @GetMapping("/keycloaks/{realm}")
    public RealmResource realm(@PathParam("realm") String realm) {
        return keycloak.realms().realm(realm);
    }


    @GetMapping("/keycloaks/realms")
    public ResponseEntity<List<RealmRepresentation>> findAll() {
        return new ResponseEntity<>(keycloak.realms().findAll(), HttpStatus.OK);
    }
    @PostMapping("/keycloaks/realms")
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
