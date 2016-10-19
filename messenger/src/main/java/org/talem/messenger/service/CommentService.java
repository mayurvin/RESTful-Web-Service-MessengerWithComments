package org.talem.messenger.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.talem.messenger.database.Database;
import org.talem.messenger.model.Comment;
import org.talem.messenger.model.Message;

public class CommentService {
	
private Map<Long, Message> messages = Database.getMessages();
	
	public CommentService(){
		messages.put(1L, new Message(1L, "Hellow World !!", "Mayur"));
		messages.put(2L, new Message(2L, "Hellow Jersey !!", "Mayur"));
	}
	
	public List<Comment> getAllComments(long messageId){
		Map<Long, Comment> comments = messages.get(messageId).getComments();
		return new ArrayList<Comment>(comments.values());
	}
	
	public Comment getComment(long messageId, long commentId){
		
		return messages.get(messageId).getComments().get(commentId);
	}
	
	public Comment addComment(long messageId, Comment comment){
		Map<Long, Comment> comments = messages.get(messageId).getComments();
		comment.setId(comments.size() + 1);
		comments.put(comment.getId(), comment);
		
		return comment;
	}
	 public Comment updateComment(long messageId, Comment comment){
		 if(messageId <= 0 || comment.getId() <= 0){
			 return null;
		 }
		Map<Long, Comment> comments = messages.get(messageId).getComments();
		comments.put(comment.getId(), comment);
		return comment;
	 }
	public Comment removeComment(long messageId, long commentId){
		Map<Long, Comment> comments = messages.get(messageId).getComments();

		return comments.remove(commentId);
	}

	
}
