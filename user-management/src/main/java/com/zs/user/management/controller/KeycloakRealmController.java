package com.zs.user.management.controller;

import com.zs.user.management.dto.RealmDto;
import com.zs.user.management.service.IKeycloakRealm;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.representations.idm.RealmRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.PathParam;
import java.util.List;

@RestController
@RequestMapping(path = "/api")
@CrossOrigin(allowedHeaders = "*", origins = "*")
public class KeycloakRealmController {

    @Autowired
    private IKeycloakRealm keycloakRealm;
    @Autowired
    private Keycloak keycloak;
    @GetMapping("/keycloaks/realms/{realm}")
    public RealmRepresentation realm(@PathVariable String realm) {
        return keycloakRealm.realm(realm);
    }


    @GetMapping("/keycloaks/realms")
    public ResponseEntity<List<RealmRepresentation>> findAll() {
        return keycloakRealm.findAll();
    }
    @PostMapping("/keycloaks/realms")
    public ResponseEntity<?> postRealm(@RequestBody RealmDto dto) {
//        final RealmRepresentation newRealm = new RealmRepresentation();
//        newRealm.setRealm(dto.getName());
//        newRealm.setEnabled(true);
//        newRealm.setPasswordPolicy("hashAlgorithm(scramsha1)");
//        newRealm.setAttributes(new HashMap<>());
//        newRealm.getAttributes().put("namespace", namespace);
//        newRealm.getAttributes().put("enmasse-realm", "true");
//        final RealmRepresentation realmRepresentation = new RealmRepresentation();
//        realmRepresentation.setRealm(dto.getName());
//        realmRepresentation.setId(dto.getName());
//        realmRepresentation.setEnabled(Boolean.TRUE);
//        keycloak.realms().create(realmRepresentation);
        return keycloakRealm.postRealm(dto);
    }

}
