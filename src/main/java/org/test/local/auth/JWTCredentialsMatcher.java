/**
 *
 */
package org.test.local.auth;

import javax.xml.bind.DatatypeConverter;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;

/**
 * @author unico
 *
 */
public class JWTCredentialsMatcher implements CredentialsMatcher {

    /* (非 Javadoc)
     * @see org.apache.shiro.authc.credential.CredentialsMatcher#doCredentialsMatch(org.apache.shiro.authc.AuthenticationToken, org.apache.shiro.authc.AuthenticationInfo)
     */
    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {

        try {
            Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary("secret"))
                    .parseClaimsJws((String) info.getCredentials()).getBody().getSubject();
        } catch (SignatureException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

}
