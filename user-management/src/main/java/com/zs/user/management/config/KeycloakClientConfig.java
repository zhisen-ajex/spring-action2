package com.zs.user.management.config;

import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KeycloakClientConfig {
    @Value("${keycloak.credentials.secret}")
    private String secretKey;

    @Value("${keycloak.auth-server-url}")
    private String authUr;
    @Value("${keycloak.realm}")
    private String realm;
    @Value("${keycloak.resource}")
    private String clientId;


    @Value("${keycloak_admin_user}")
    private String user;

    @Value("${keycloak_admin_password}")
    private String password;


    @Bean
    public Keycloak keycloak() {
//        KeyStore trustStore = createKeyStore(b64dec.decode(certificate.getData().get("tls.crt")), serviceCa);
//        ResteasyJackson2Provider provider = new ResteasyJackson2Provider() {
//            @Override
//            public void writeTo(Object value, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, Object> httpHeaders, OutputStream entityStream) throws IOException {
//                ObjectMapper mapper = locateMapper(type, mediaType);
//                mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
//                super.writeTo(value, type, genericType, annotations, mediaType, httpHeaders, entityStream);
//            }
//        };
//        ResteasyClient resteasyClient = new ResteasyClientBuilder()
//                .connectionPoolSize(1)
//                .asyncExecutor(executorService) // executorService is the replacement but returns the wrong type
//                .hostnameVerification(ResteasyClientBuilder.HostnameVerificationPolicy.ANY)
//                .register(provider)
//                .build();
        return KeycloakBuilder.builder()
                //.grantType(OAuth2Constants.CLIENT_CREDENTIALS)
                .grantType(OAuth2Constants.PASSWORD)
                .serverUrl(authUr)
                .realm(realm)
                .clientId(clientId)
                .clientSecret(secretKey)
                .username(user)
                .password(password)
               // .resteasyClient(new ResteasyClientBuilder().connectionPoolSize(10).register(new CustomJacksonProvider()).build())
                .build();
    }
}
