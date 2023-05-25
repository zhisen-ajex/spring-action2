package com.zs.user.management.controller;

import com.zs.user.management.dto.GroupDto;
import com.zs.user.management.service.KeycloakGroupService;
import org.keycloak.representations.idm.GroupRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api")
@CrossOrigin(allowedHeaders = "*", origins = "*")
public class KeycloakGroupController {

    @Autowired
    private KeycloakGroupService service;

    @PostMapping("/keycloaks/groups")
    public ResponseEntity<?> createGroup(@RequestBody GroupDto dto) throws Exception {
        service.getGroup().createGroup(dto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/keycloaks/groups")
    public ResponseEntity<List<GroupRepresentation>> findAllGroup() {
        return new ResponseEntity<>(service.getGroup().findAllGroup(), HttpStatus.OK);
    }

    @GetMapping("/keycloaks/groups/{id}")
    public ResponseEntity<GroupRepresentation> findGroupByName(@PathVariable String id) {
        return new ResponseEntity<>(service.getGroup().findGroupByName(id), HttpStatus.OK);
    }

    @DeleteMapping("/keycloaks/groups/{id}")
    public ResponseEntity<?> deleteGroup(@PathVariable String id) {
        service.getGroup().deleteGroup(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/keycloaks/groups/{groupId}/users")
    public ResponseEntity<List<UserRepresentation>> usersOfGroup(@PathVariable String groupId) {
        return new ResponseEntity<>(service.getGroup().usersOfGroup(groupId), HttpStatus.OK);
    }

    @PostMapping("/keycloaks/groups/roles")
    public ResponseEntity<?> rolAddGroup(@RequestParam String groupId, @RequestParam String rolAdi) {
        service.getGroup().rolAddGroup(groupId, rolAdi);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/keycloaks/groups/{groupId}/roles")
    public ResponseEntity<List<RoleRepresentation>> rolsOfGroup(@PathVariable String groupId) {
        return new ResponseEntity<>(service.getGroup().rolsOfGroup(groupId), HttpStatus.OK);
    }

}
