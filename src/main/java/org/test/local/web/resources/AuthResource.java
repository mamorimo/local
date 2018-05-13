package org.test.local.web.resources;

import java.security.Key;

import javax.crypto.spec.SecretKeySpec;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.xml.bind.DatatypeConverter;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.glassfish.jersey.server.mvc.Viewable;
import org.test.local.dto.AuthDto;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Path("auth")
public class AuthResource {

	@GET
	public Response doAuth() {

		return Response.ok(new Viewable("/login")).build();
	}

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response authLogin(AuthDto requestDto) {

//        Factory<org.apache.shiro.mgt.SecurityManager> factory = new IniSecurityManagerFactory("shiro.ini");
//        org.apache.shiro.mgt.SecurityManager securityManager = factory.getInstance();
//        SecurityUtils.setSecurityManager(securityManager);

        Subject user = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken(requestDto.account, requestDto.password);

        try {
            user.login(token);
        } catch (AuthenticationException e) {
            return Response.status(Status.UNAUTHORIZED).build();
        }

        SignatureAlgorithm sigAlg = SignatureAlgorithm.HS256;

        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary("secret");

        Key signingKey = new SecretKeySpec(apiKeySecretBytes, sigAlg.getJcaName());

        JwtBuilder builder = Jwts.builder()
                .setSubject(requestDto.account)
                .signWith(sigAlg, signingKey);

        requestDto.atoken = builder.compact();

        user.getSession().stop();

        return Response.ok(requestDto).build();
    }
}
