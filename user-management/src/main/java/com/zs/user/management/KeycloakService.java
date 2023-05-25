package com.zs.user.management;

import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;

public class KeycloakService {

    static Keycloak keycloak = null;
    final static String serverUrl = "http://192.168.134.191:8080/auth";
    final static String realm = "test";
    final static String clientId = "demo-client";
    final static String clientSecret = "416d8d92-fd62-4613-a6c5-b688e58dbbf9";

    public KeycloakService() {
    }

    public static Keycloak getInstance(){
        if(keycloak == null){
        keycloak = KeycloakBuilder.builder() //
                .serverUrl(serverUrl) //
                .realm(realm) //
                .grantType(OAuth2Constants.PASSWORD) //
                .clientId(clientId) //
                .clientSecret(clientSecret)
                .username("admin2")
                .password("123")
                .build();
        }
        return keycloak;
    }
}
