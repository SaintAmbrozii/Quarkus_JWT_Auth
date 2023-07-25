package org.acme.tsg.controller;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.SecurityContext;
import org.acme.tsg.service.UserService;
import org.acme.tsg.dto.UserDTO;


@Path("/api/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {

    private final UserService userService;


    @Inject
    public UserResource(UserService userService) {
        this.userService = userService;
    }
    @Path("/info")
    @GET
    @RolesAllowed("USER")
    public Response profile(@Context SecurityContext context) {
        return Response.ok(context.getUserPrincipal()).build();
    }


    @POST
    public Response addUser(@Valid UserDTO userDTO) {

        return Response.ok(userService.addUser(userDTO)).build();
    }

    @GET
    public Response userList() {
        return Response.ok(userService.userList()).build();
    }



    @GET
    @Path("/search/{email}")
    public Response findByEmail(@PathParam("email") String email) {
        return Response.ok(userService.findByEmail(email)).build();
    }



    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id) {
        return Response.ok(userService.findById(id)).build();
    }
    @PUT
    @Path("/{id}")
    public Response updateUser(@PathParam("id")Long id,@Valid UserDTO userDTO) {
        return Response.ok(userService.updateUser(id,userDTO)).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteUser(@PathParam("id") Long id) {
        userService.deleteUser(id);
        return Response.status(200).build();
    }
}
