package com.zappos.sgambhir.exceptions;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ServerExceptionHandler implements ExceptionMapper<ServerException> 
{
    @Override
    @Produces(MediaType.APPLICATION_JSON)
    public Response toResponse(ServerException exception) 
    {
    	Map<String, Object> error = new HashMap<String, Object>();
    	error.put("code", Status.INTERNAL_SERVER_ERROR.getStatusCode());
    	error.put("message", exception.getMessage());

        return Response.status((int) error.get("code")).entity(error).build();  
    }
}
