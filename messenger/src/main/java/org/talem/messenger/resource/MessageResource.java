package org.talem.messenger.resource;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import org.talem.messenger.model.Message;
import org.talem.messenger.service.MessageService;

@Path("messages")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MessageResource {
	
	MessageService messageService = new MessageService();
	/*
	@GET
	public List<Message> getMessages(){
		return messageService.getAllMessages();
	}
	*/
	@GET
	public List<Message> getMessages(@QueryParam("year")int year,
									@QueryParam("start")int start,
									@QueryParam("size")int size){
		if(year > 0){
			return messageService.getMessageForYear(year);
		}
		if(start >= 0 && size > 0){
			return messageService.getAllMessagesPaginated(start, size);
		}
		return messageService.getAllMessages();
	}
	/*
	@POST
	public Message addMessage(Message message){
		messageService.addMessage(message);
		return message;
	}
	*/
	@POST
	public Response addMessage(Message message, @Context UriInfo uriInfo){
		Message newMessage = messageService.addMessage(message);
		/*return Response.status(Status.CREATED)
				.entity(newMessage)
				.build();*/
		/*return Response.created(new URI("/messenger/webapi/messages/" + newMessage.getId()))
				.entity(newMessage)
				.build();*/
		String newPath = String.valueOf(message.getId());
		URI uri = uriInfo.getAbsolutePathBuilder().path(newPath).build();
		return Response.created(uri)
				.entity(newMessage)
				.build();
	}
	
	@PUT
	@Path("/{messageId}")
	public Message updateMessage(@PathParam("messageId")long messageId, Message message){
		message.setId(messageId);
		messageService.updateMessage(message);
		return message;
	}
	
	@DELETE
	@Path("/{messageId}")
	public Message deleteMessage(@PathParam("messageId")long messageId){
		return messageService.removeMessage(messageId);
	}
	
	@GET
	@Path("/{messageId}")
	public Message test(@PathParam("messageId")long messageId){
		
		return messageService.getMessage(messageId);
	}
	//Subresource for comments
	@Path("/{messageId}/comments")
	public CommentResource test(){
		return new CommentResource();
		
	}
	
}
