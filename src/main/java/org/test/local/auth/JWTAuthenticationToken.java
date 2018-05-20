package org.test.local.auth;

import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.DatatypeConverter;

import org.apache.shiro.authc.AuthenticationToken;

import io.jsonwebtoken.Jwts;

public class JWTAuthenticationToken implements AuthenticationToken {

    private String token;

    public JWTAuthenticationToken(String jwt) {
        this.token = jwt;
    }

    public String getUserName() {
        return Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary("secret"))
                .parseClaimsJws(token).getBody().getSubject();
    }

    @Override
    public Object getPrincipal() {
        return this.getUserName();
    }

    @Override
    public Object getCredentials() {
        return this.token;
    }

    public Set<String> getRoles() {
        String role = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary("secret"))
                .parseClaimsJws(token).getBody().get("role", String.class);

        Set<String> roles = new HashSet();
        roles.add(role);

        return roles;
    }
}
