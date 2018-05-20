package org.test.local.auth;

import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExpiredCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAccount;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

public class JWTAuthorizingRealm extends AuthorizingRealm {

    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        JWTAuthenticationToken upToken = (JWTAuthenticationToken) token;
        SimpleAccount account = new SimpleAccount(upToken.getPrincipal(), upToken.getCredentials(), getName(),
                upToken.getRoles(), null);

        if (account != null) {

            if (account.isLocked()) {
                throw new LockedAccountException("Account [" + account + "] is locked.");
            }
            if (account.isCredentialsExpired()) {
                String msg = "The credentials for account [" + account + "] are expired";
                throw new ExpiredCredentialsException(msg);
            }
        }

        return account;
    }

    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
//        Object primary = null;
//        if (principals != null && !principals.isEmpty()) {
//            Collection thisPrincipals = principals.fromRealm(getName());
//            if (thisPrincipals != null && !thisPrincipals.isEmpty()) {
//                primary = thisPrincipals.iterator().next();
//            } else {
//                //no principals attributed to this particular realm.  Fall back to the 'master' primary:
//                primary = principals.getPrimaryPrincipal();
//            }
//        }

        Set<String> roles = new HashSet();
        roles.add("guest");

        return new SimpleAuthorizationInfo(roles);
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTAuthenticationToken;
    }
}
