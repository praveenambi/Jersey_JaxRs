package com.praveen.jax.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.praveen.jax.model.ErrorMessage;


@Provider
public class DataNotFoundExceptionMapper  implements ExceptionMapper<DataNotFoundException>{

	@Override
	public Response toResponse(DataNotFoundException exception) {
		
		
		
		ErrorMessage  error = new ErrorMessage(exception.getMessage(), 404, "https://docs.oracle.com/javaee/6/tutorial/doc/giepu.html");
		return Response.status(Status.NOT_FOUND)
				.entity(error)
				.build();
		
	}
	
}
