package org.test.local.auth;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;

public class JWTVerifyingFilter extends AccessControlFilter {

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)
            throws Exception {

        HttpServletRequest httpRequest = (HttpServletRequest) request;

        String aToken = httpRequest.getParameter("atoken");

        String headerAToken = httpRequest.getHeader("Authorization");
        if (headerAToken != null && !headerAToken.startsWith("Bearer ")) {
            aToken = headerAToken.substring(headerAToken.indexOf(" "));
        }

        if (aToken == null) {
            return false;
        }

        Subject user = SecurityUtils.getSubject();

        JWTAuthenticationToken token = new JWTAuthenticationToken(aToken);
        user.login(token);

        return true;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        saveRequestAndRedirectToLogin(request, response);
        return false;
    }

}
