/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cst8218.fokou.slidergame.resources;

import cst8218.fokou.slidergame.entity.Slider;
import jakarta.annotation.security.DeclareRoles;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.servlet.annotation.HttpMethodConstraint;
import jakarta.servlet.annotation.ServletSecurity;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

/**
 * RESTful resource class for managing Slider entities. Provides CRUD
 * operations, extended POST/PUT handling, and standards-compliant HTTP
 * responses.
 *
 * @author wilfr
 */
//restricted by roles

@Stateless
@Path("cst8218.fokou.slidergame.slider")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@DeclareRoles({"Admin", "RESTFullGroup"})
@ServletSecurity(
    httpMethodConstraints = {
        @HttpMethodConstraint(value = "GET", rolesAllowed = {"Admin", "RESTFullGroup"}),
        @HttpMethodConstraint(value = "POST", rolesAllowed = {"Admin", "RESTFullGroup"}),
        @HttpMethodConstraint(value = "PUT", rolesAllowed = {"Admin", "RESTFullGroup"}),
        @HttpMethodConstraint(value = "DELETE", rolesAllowed = {"Admin", "RESTFullGroup"})
    }
)
public class SliderFacadeREST extends AbstractFacade<Slider> {

    @PersistenceContext(unitName = "my_persistence_unit")
    private EntityManager em;

    public SliderFacadeREST() {
        super(Slider.class);
    }

    @POST
    public Response createOrUpdate(Slider slider) {
        if (slider.getId() == null) {
            super.create(slider);
            return Response.status(Response.Status.CREATED).entity(slider).build();
        } else {
            Slider existing = super.find(slider.getId());
            if (existing != null) {
                existing.updateFrom(slider);
                super.edit(existing);
                return Response.ok(existing).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
        }
    }

    @POST
    @Path("{id}")
    public Response updateById(@PathParam("id") Long id, Slider slider) {
        if (slider.getId() != null && !slider.getId().equals(id)) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        Slider existing = super.find(id);
        if (existing == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        existing.updateFrom(slider);
        super.edit(existing);
        return Response.ok(existing).build();
    }

    @PUT
    @Path("{id}")
    public Response replace(@PathParam("id") Long id, Slider slider) {
        if (slider.getId() != null && !slider.getId().equals(id)) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        Slider existing = super.find(id);
        if (existing == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        slider.setId(id);
        super.edit(slider);
        return Response.ok(slider).build();
    }

    @PUT
    public Response putNotAllowed() {
        return Response.status(Response.Status.METHOD_NOT_ALLOWED).build();
    }

    @DELETE
    @Path("{id}")
    public Response remove(@PathParam("id") Long id) {
        Slider existing = super.find(id);
        if (existing == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        super.remove(existing);
        return Response.noContent().build();
    }

    @GET
    @Path("{id}")
    public Response find(@PathParam("id") Long id) {
        Slider slider = super.find(id);
        if (slider == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(slider).build();
    }

    @GET
    public Response findAllSliders() {
        List<Slider> sliders = super.findAll();
        return Response.ok(sliders).build();
    }

    @GET
    @Path("{from}/{to}")
    public Response findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        List<Slider> sliders = super.findRange(new int[]{from, to});
        return Response.ok(sliders).build();
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public Response countREST() {
        return Response.ok(String.valueOf(super.count())).build();
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}