/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cst8218.fokou.slidergame.resources;

import jakarta.annotation.security.DeclareRoles;
import jakarta.servlet.annotation.HttpMethodConstraint;
import jakarta.servlet.annotation.ServletSecurity;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/**
 *
 * @author wilfr
 */
@DeclareRoles({"Admin", "RESTFullGroup"})
@ServletSecurity(
    httpMethodConstraints = {
        @HttpMethodConstraint(value = "GET", rolesAllowed = {"Admin", "RESTFullGroup"}),
        @HttpMethodConstraint(value = "POST", rolesAllowed = {"Admin"}),
        @HttpMethodConstraint(value = "PUT", rolesAllowed = {"Admin"})
    }
)
@Path("/resources")
public class SliderResource {

    @GET
    @Path("/all")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getAllSliders() {
        return Response.ok("GET: Access granted").build();
    }

    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createSlider() {
        return Response.ok("POST: Access granted").build();
    }

    @PUT
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateSlider() {
        return Response.ok("PUT: Access granted").build();
    }
}