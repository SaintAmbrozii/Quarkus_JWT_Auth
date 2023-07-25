package org.acme.tsg.controller;

import jakarta.annotation.security.PermitAll;
import jakarta.inject.Inject;
import jakarta.resource.spi.work.SecurityContext;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import org.acme.tsg.domain.User;
import org.acme.tsg.dto.AuthRequest;
import org.acme.tsg.dto.TokenResponse;
import org.acme.tsg.exception.AuthException;
import org.acme.tsg.service.UserService;
import org.acme.tsg.utils.PasswordEncoder;
import org.acme.tsg.utils.TokenUtils;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.util.Collections;

@PermitAll
@Path("/api/auth")
@Consumes(MediaType.APPLICATION_JSON)
public class AuthController {

    @Inject
    UserService userService;
   @Inject
   PasswordEncoder passwordEncoder;



    @ConfigProperty(name = "mp.jwt.duration")
    Long duration;
    @ConfigProperty(name = "mp.jwt.issuer")
    String issuer;

   @POST
   @Path("/login")
   @PermitAll
    public Response login(AuthRequest authRequest) throws Exception {

        User user = userService.findByEmail(authRequest.getEmail());
        if (!passwordEncoder.matches(authRequest.getPassword(), user.getPassword())) {

            throw new AuthException("не подходит пароль");

       }
        String  generatedToken = TokenUtils.generateToken(user.getEmail(), Collections.singleton(user.getRoles()), duration, issuer);

       TokenResponse jwt = TokenResponse.builder().token(generatedToken).build();
        return Response.ok(jwt).header("Auhthorization",jwt).build();


   }


    @GET
    @Path("/user")
    public String user(@Context SecurityContext ctx) {
        return ctx.getName();
    }

}
