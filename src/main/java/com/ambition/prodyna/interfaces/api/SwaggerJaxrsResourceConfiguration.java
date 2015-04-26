package com.ambition.prodyna.interfaces.api;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import com.ambition.prodyna.interfaces.rest.person.PersonEndpoint;

@ApplicationPath("/resources")
public class SwaggerJaxrsResourceConfiguration extends Application {
    private void addRestResourceClasses(final Set<Class<?>> resources) {
	resources.add(PersonEndpoint.class);
    }

    @Override
    public Set<Class<?>> getClasses() {
	final Set<Class<?>> resources = new HashSet<>();
	resources.add(com.wordnik.swagger.jaxrs.listing.ApiListingResource.class);
	resources.add(com.wordnik.swagger.jaxrs.listing.ApiDeclarationProvider.class);
	resources.add(com.wordnik.swagger.jaxrs.listing.ApiListingResourceJSON.class);
	resources.add(com.wordnik.swagger.jaxrs.listing.ResourceListingProvider.class);
	this.addRestResourceClasses(resources);
	return resources;
    }
}
