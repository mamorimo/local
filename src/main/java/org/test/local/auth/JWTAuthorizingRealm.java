package org.test.local.auth;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExpiredCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAccount;
import org.apache.shiro.realm.SimpleAccountRealm;

public class JWTAuthorizingRealm extends SimpleAccountRealm {

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        if (!(token instanceof JWTAuthenticationToken)) {
            return null;
        }

        JWTAuthenticationToken upToken = (JWTAuthenticationToken) token;

        SimpleAccount account = getUser(upToken.getUserName());

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

}
