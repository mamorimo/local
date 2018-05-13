package org.test.local.auth;

import org.apache.shiro.authc.AuthenticationToken;

public class JWTAuthenticationToken implements AuthenticationToken {

    private String token;

    public JWTAuthenticationToken(String userName) {
        this.token = userName;
    }

    public String getUserName() {
        return token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }

}
