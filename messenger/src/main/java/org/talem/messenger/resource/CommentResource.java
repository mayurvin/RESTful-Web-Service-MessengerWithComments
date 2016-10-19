package org.talem.messenger.resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.talem.messenger.model.Comment;
import org.talem.messenger.service.CommentService;

@Path("/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CommentResource {
	
	private CommentService commentService = new CommentService();
	
	@GET
	public List<Comment> getAllComment(@PathParam("messageId")long messageId){
		return commentService.getAllComments(messageId);
	}
	@POST
	public Comment addComment(@PathParam("messageId")long messageId, Comment comment){
		return commentService.addComment(messageId, comment);
	}
	@PUT
	@Path("/{commentId}")
	public Comment updateComment(@PathParam("messageId")long messageId,@PathParam("commentId")long commentId, Comment comment){
		comment.setId(commentId);
		return commentService.updateComment(messageId, comment);
	}
	//DELETE
	//GET perticular comment
	/*
	@GET
	public String test(){
		return "new Sub Resource";
	}
	
	@GET
	@Path("/{commentId}") // messageId is available because the this call is from a method which has messageID
	public String test2(@PathParam("messageId")long messageId, @PathParam("commentId")long commentId){
		return "Message ID: " + messageId + "\ncomment ID: " + commentId;
	}
	*/
}
