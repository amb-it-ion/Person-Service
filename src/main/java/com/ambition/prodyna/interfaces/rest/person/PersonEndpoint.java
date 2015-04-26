package com.ambition.prodyna.interfaces.rest.person;

import java.util.UUID;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.persistence.OptimisticLockException;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.EntityTag;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriBuilder;

import com.ambition.prodyna.domain.model.person.Person;
import com.ambition.prodyna.domain.model.person.PersonRepository;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;


/**
 * Here we set up our REST service
 */
@Stateless
@Path("/person")
@Api(value = "/person", description = "Person CRUD service")
@Produces("application/json;charset=utf-8")
@Consumes("application/json")
public class PersonEndpoint {

    @Inject
    public PersonRepository personRepository;

    public PersonEndpoint( ) {
    }

    /**
     * @description creates a new person
     * @status 201 Person created successfully
     * @status 401 only authorized users can access this resource
     * @status 403 only authenticated users can access this resource
     * @status 409 UUID was already used
     */
    @PUT
    @Path("/")
    @ApiOperation(value = "create", notes = "Creates a person")
    @Produces("application/json;charset=utf-8")
    @Consumes("application/json")
    public Response create(@NotNull @Valid final PersonRest entity) {

	if ( this.personRepository.create(entity.toPerson()) )
	{
	    return Response.created(UriBuilder.fromResource(PersonEndpoint.class).path(String.valueOf(entity.getUuid())).build()).build();
	}
	else
	{
	    return Response.status(Status.CONFLICT).build();
	}
    }


    /**
     * @description deletes a person based on its UUID
     * @param id person ID
     * @status 204 Person deleted successfully
     * @status 401 only authorized users can access this resource
     * @status 403 only authenticated users can access this resource
     * @status 404 person not found
     */
    @DELETE
    @Path("/{uuid}")
    @ApiOperation(value = "delete", notes = "Deletes a person")
    @Produces("application/json;charset=utf-8")
    @Consumes("application/json")
    public Response deleteById(@PathParam("uuid") final String uuid) {
	this.personRepository.delete(UUID.fromString(uuid));
	return Response.noContent().build();
    }

    /**
     * @description finds a person based on its ID
     * @param uuid person ID
     * @status 200 person found successfully
     * @status 403 only authenticated users can access this resource
     * @status 404 person not found
     */
    @GET
    @Path("/{uuid}")
    @ApiOperation(value = "read", notes = "Reads a person")
    @Produces("application/json;charset=utf-8")
    @Consumes("application/json")
    public Response read(@PathParam("uuid") final String uuid , @Context final Request request) {
	Person entity;
	try {
	    entity = this.personRepository.read(UUID.fromString(uuid));
	} catch (final NoResultException nre) {
	    entity = null;
	}
	if (entity == null) {
	    return Response.status(Status.NOT_FOUND).build();
	}
	final CacheControl cc = new CacheControl();
	cc.setMaxAge(100);
	final EntityTag tag = new EntityTag(Integer.toString(entity.hashCode()));
	Response.ResponseBuilder builder =  request.evaluatePreconditions(tag);
	if(builder != null){
	    builder.cacheControl(cc);
	    return builder.build();
	}
	builder = Response.ok(entity);
	builder.cacheControl(cc);
	builder.tag(tag);
	return Response.ok(entity).build();
    }

    /**
     * @status 204 Person updated successfully
     * @status 400 Person model cannot be empty
     * @status 403 only authenticated users can access this resource
     * @status 404 No Person found with the given ID
     * @status 409 UUID passed in parameter is different from the Person to update,
     * 		   or somebody else changed the entity meanwhile to the response
     */
    @PUT
    @Path("/{uuid}")
    @ApiOperation(value = "update", notes = "Updates a person")@Produces("application/json;charset=utf-8")
    @Consumes("application/json")
    public Response update(@PathParam("uuid") final String uuid, @NotNull @Valid final PersonRest entity) {
	if (entity == null) {
	    return Response.status(Status.BAD_REQUEST).build();
	}
	if (!uuid.equals(entity.getUuid())) {
	    return Response.status(Status.CONFLICT).entity(entity).build();
	}
	if (this.personRepository.read(entity.toPerson().getUuid()) == null) {
	    return Response.status(Status.NOT_FOUND).build();
	}
	try {
	    this.personRepository.update(entity.toPerson());
	} catch (final OptimisticLockException e) {
	    return Response.status(Status.CONFLICT).entity(e.getEntity()).build();
	}
	return Response.noContent().build();
    }
}