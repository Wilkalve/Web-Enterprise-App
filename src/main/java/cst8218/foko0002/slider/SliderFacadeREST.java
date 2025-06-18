/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cst8218.foko0002.slider;

import cst8218.foko0002.slider.entity.Slider;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;

/**
 *
 * @author wilfr
 */
@Stateless
@Path("cst8218.foko0002.slider.entity.slider")
public class SliderFacadeREST extends AbstractFacade<Slider> {

    @PersistenceContext(unitName = "my_persistence_unit")
    private EntityManager em;

    public SliderFacadeREST() {
        super(Slider.class);
    }

   
@POST
@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
public Response create(Slider entity, @Context UriInfo uriInfo) {
    if (entity.getId() != null) {
        return Response.status(Response.Status.BAD_REQUEST)
                .entity("New entity should not have an ID assigned").build();
    }

    super.create(entity);
    em.flush(); // Ensure ID is persisted before returning response

    if (entity.getId() == null) {
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity("Entity ID was not generated").build();
    }

    URI location = uriInfo.getAbsolutePathBuilder().path(entity.getId().toString()).build();
    return Response.created(location).entity(entity).build();
}

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response replace(@PathParam("id") Long id, Slider entity) {
        if (!id.equals(entity.getId())) {
            return Response.status(Response.Status.BAD_REQUEST)
                .entity("ID in request body does not match URL ID").build();
        }
        Slider existingSlider = super.find(id);
        if (existingSlider == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        super.edit(entity);
        return Response.ok(entity).build();
    }

    
    @POST
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response update(@PathParam("id") Long id, Slider newSlider) {
        Slider existingSlider = super.find(id);
        if (existingSlider == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        existingSlider.updateFrom(newSlider);
        super.edit(existingSlider);
        return Response.ok(existingSlider).build();
    }

    @DELETE
    @Path("{id}")
    public Response remove(@PathParam("id") Long id) {
        Slider entity = super.find(id);
        if (entity == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        super.remove(entity);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response find(@PathParam("id") Long id) {
        Slider entity = super.find(id);
        if (entity == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(entity).build();
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Slider> findAll() {
        return super.findAll();
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
