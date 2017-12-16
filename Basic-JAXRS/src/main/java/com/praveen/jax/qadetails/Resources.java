package com.praveen.jax.qadetails;

import java.net.URI;
import java.util.List;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import com.praveen.jax.model.Message;
import com.praveen.jax.service.MessageFilterbean;
import com.praveen.jax.service.MessageService;

@Path("/details")
public class Resources {

	MessageService msg_service = new MessageService();

	@GET
	@Produces(value={MediaType.APPLICATION_JSON,MediaType.TEXT_XML})
	public List<Message> getdetails(@BeanParam MessageFilterbean msgfiletrbean) {

		if (msgfiletrbean.getYear() > 0 ) {
			return  msg_service.getAllMessagesForYear(msgfiletrbean.getYear());

		}

		if (msgfiletrbean.getStart()> 0 && msgfiletrbean.getSize()> 0 ) {
			return  msg_service.getAllMessagesPaginated(msgfiletrbean.getStart(), msgfiletrbean.getSize());

		}

		return msg_service.getallMessages() ;
	}

	@GET
	@Path("/{messageid}")
	@Produces(value={MediaType.APPLICATION_JSON,MediaType.TEXT_XML})
	public Message getsubdetails(@PathParam("messageid")  long id,@Context UriInfo URI_info) {

		 Message getmessage = msg_service.getmessage(id);
		 String uri = getURI_forself(URI_info, getmessage);
		 getmessage.addLinks(uri, "self");
		 
		 return getmessage;
		
		//return  "Path param is " + messageid;
	}

	

	@GET
	@Path("/context")
	@Produces(MediaType.TEXT_PLAIN)
	public String getContext(@Context UriInfo URIinfo,@Context HttpHeaders httpheaders ) {

		String absolute_URI = URIinfo.getAbsolutePath().toString();
		String cookies = httpheaders.getCookies().toString();
		return "Absolute_URI   " + absolute_URI + "                         Cookies   "  + cookies;
		
	}

	
	@POST
	@Path("/{messageid}")
	@Produces(value={MediaType.APPLICATION_JSON,MediaType.TEXT_XML})
	@Consumes(value={MediaType.APPLICATION_JSON,MediaType.TEXT_XML})
	public Response  addMessage(Message msg, @Context UriInfo URI_info) {

		Message addMessage = msg_service.addMessage(msg);
		String newEmpID = String.valueOf(addMessage.getEmpID());
		URI LocationHeader = URI_info.getAbsolutePathBuilder().path(newEmpID).build();
		return Response.created(LocationHeader)
				.entity(addMessage)
				.build();
		//return msg_service.addMessage(msg);
	}

	@PUT
	@Path("/{messageid}")
	@Produces(value={MediaType.APPLICATION_JSON,MediaType.TEXT_XML})
	@Consumes(value={MediaType.APPLICATION_JSON,MediaType.TEXT_XML})
	public Response  updateMessage(@PathParam("messageid")  long id ,Message msg,@Context UriInfo URI_info) {
		msg.setEmpID(id);
		//return msg_service.updateMessage(msg);
		
		Message addMessage = msg_service.updateMessage(msg);
		String newEmpID = String.valueOf(addMessage.getEmpID());
		URI LocationHeader = URI_info.getAbsolutePathBuilder().path(newEmpID).build();
		return Response.ok(LocationHeader)
				.entity(addMessage)
				.build();
	}

	@DELETE
	@Path("/{messageid}")
	@Produces(value={MediaType.APPLICATION_JSON,MediaType.TEXT_XML})
	public Response   deleteMessage(@PathParam("messageid")  long id ,Message msg,@Context UriInfo URI_info) {
	//	return msg_service.removeMessage(id);
		
		Message addMessage = msg_service.removeMessage(id);
		String newEmpID = String.valueOf(addMessage.getEmpID());
		URI LocationHeader = URI_info.getAbsolutePathBuilder().path(newEmpID).build();
		return Response.ok(LocationHeader)
				.entity(addMessage)
				.build();

	}
	
	private String getURI_forself(UriInfo URI_info, Message getmessage) {
		String uri = URI_info.getBaseUriBuilder()
		 .path(Resources.class)
		 .path(Long.toString(getmessage.getEmpID()))
		 .build()
		 .toString();
		return uri;
	}


}
