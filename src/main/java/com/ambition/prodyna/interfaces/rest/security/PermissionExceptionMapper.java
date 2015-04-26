package com.ambition.prodyna.interfaces.rest.security;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.ambition.prodyna.application.security.PermissionException;

@Provider
public class PermissionExceptionMapper implements ExceptionMapper<PermissionException> {

    @Context
    HttpServletRequest request;

    @Override
    public Response toResponse(final PermissionException exception) {
	return Response.status(Response.Status.FORBIDDEN).build();
    }
}
