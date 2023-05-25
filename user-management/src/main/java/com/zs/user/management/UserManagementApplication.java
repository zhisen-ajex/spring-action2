package com.zs.user.management;

import lombok.extern.slf4j.Slf4j;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class UserManagementApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserManagementApplication.class, args);

  /*      KeycloakAdminClientDemo adminClient = new KeycloakAdminClientDemo();

        adminClient.usersResource.list()
                .stream()
                .filter(ur-> !ur.getUsername().equals("manager"))
               .forEach(ur -> log.info(" adminClient.usersResource forEach:{}",ur));
                       //adminClient.deleteUserByUUID(ur.getId()));*/
        // User "idm-admin" needs at least "manage-users, view-clients, view-realm, view-users" roles for "realm-management"

    }


    private static String toString(UserRepresentation userRepresentation) {
        StringBuilder builder = new StringBuilder();
        builder.append("{");
        builder.append("Username:");
        builder.append(userRepresentation.getUsername());
        builder.append(",Firstname:");
        builder.append(userRepresentation.getFirstName());
        builder.append(",Lastname:");
        builder.append(userRepresentation.getLastName());
        builder.append(",Email:");
        builder.append(userRepresentation.getEmail());
        if(userRepresentation.getRealmRoles() !=null ) {
            builder.append(",Realms Roles :");
            builder.append(userRepresentation.getRealmRoles().toString());
        }
        if(userRepresentation.getClientRoles() != null){
            builder.append(",Clients Roles:");
            builder.append(userRepresentation.getClientRoles().toString());
        }
        builder.append("}");

        return builder.toString();
    }
}
