/**
 * 
 */
package com.praveen.jax.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import org.glassfish.jersey.spi.ExceptionMappers;
import com.praveen.jax.model.ErrorMessage;

/**
 * @author praveen Ambi
 *
 */
@Provider
public class GenericExceptionMapper  implements ExceptionMapper<Throwable>{
	
	@Override
	public Response toResponse(Throwable exception) {
		ErrorMessage  error = new ErrorMessage(exception.getMessage(), 500, "https://docs.oracle.com/javaee/6/tutorial/doc/giepu.html");
		return Response.status(Status.INTERNAL_SERVER_ERROR)
				.entity(error)
				.build();
		
	}

}
