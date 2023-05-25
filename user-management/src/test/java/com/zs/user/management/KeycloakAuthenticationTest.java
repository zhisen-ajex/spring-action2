package com.zs.user.management;

import org.keycloak.adapters.RefreshableKeycloakSecurityContext;
import org.keycloak.adapters.spi.KeycloakAccount;
import org.keycloak.adapters.springsecurity.account.SimpleKeycloakAccount;
import org.keycloak.adapters.springsecurity.authentication.KeycloakAuthenticationProvider;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.mockito.internal.util.collections.Sets;

import java.security.Principal;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.Arrays;

import static java.lang.System.currentTimeMillis;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.util.AssertionErrors.assertTrue;
import static org.mockito.Mockito.mock;

/**
 * Keycloak authentication provider tests.
 */
public class KeycloakAuthenticationTest {

    private KeycloakAuthenticationProvider provider = new KeycloakAuthenticationProvider();
    private KeycloakAuthenticationToken token;
    private KeycloakAuthenticationToken interactiveToken;
    private Set<String> roles = Sets.newSet("user", "admin");

    @BeforeEach
    public void setUp() throws Exception {
        Principal principal = mock(Principal.class);
        RefreshableKeycloakSecurityContext securityContext = mock(RefreshableKeycloakSecurityContext.class);
        KeycloakAccount account = new SimpleKeycloakAccount(principal, roles, securityContext);

        token = new KeycloakAuthenticationToken(account, false);
        interactiveToken = new KeycloakAuthenticationToken(account, true);
    }

/*    @Test
    public void testAuthenticate() throws Exception {
        assertAuthenticationResult(provider.authenticate(token));
    }

    @Test
    public void testAuthenticateInteractive() throws Exception {
        assertAuthenticationResult(provider.authenticate(interactiveToken));
    }

    @Test
    public void testSupports() throws Exception {
        assertTrue(provider.supports(KeycloakAuthenticationToken.class));
        assertFalse(provider.supports(PreAuthenticatedAuthenticationToken.class));
    }

    @Test
    public void testGrantedAuthoritiesMapper() throws Exception {
        SimpleAuthorityMapper grantedAuthorityMapper = new SimpleAuthorityMapper();
        grantedAuthorityMapper.setPrefix("ROLE_");
        grantedAuthorityMapper.setConvertToUpperCase(true);
        provider.setGrantedAuthoritiesMapper(grantedAuthorityMapper);

        Authentication result = provider.authenticate(token);
        assertEquals(Sets.newSet("ROLE_USER", "ROLE_ADMIN"),
                AuthorityUtils.authorityListToSet(result.getAuthorities()));
    }


    private void assertAuthenticationResult(Authentication result) {
        assertNotNull(result);
        assertEquals(roles, AuthorityUtils.authorityListToSet(result.getAuthorities()));
        assertTrue(result.isAuthenticated());
        assertNotNull(result.getPrincipal());
        assertNotNull(result.getCredentials());
        assertNotNull(result.getDetails());
    }*/
}