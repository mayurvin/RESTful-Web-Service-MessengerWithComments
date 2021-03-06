package org.talem.messenger.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.talem.messenger.database.Database;
import org.talem.messenger.exception.DataNotFoundException;
import org.talem.messenger.model.Message;

public class MessageService {
	
	private Map<Long, Message> messages = Database.getMessages();
	
	public MessageService(){
		messages.put(1L, new Message(1L, "Hellow World !!", "Mayur"));
		messages.put(2L, new Message(2L, "Hellow Jersey !!", "Mayur"));
	}
	
	public List<Message> getAllMessages(){
		return new ArrayList<Message>(messages.values());
	}
	
	public Message getMessage(long id){
		Message message = messages.get(id);
		if(message == null){
			throw new DataNotFoundException("Message with ID: " + id + "not found.");
		}
		return message;
	}
	
	public List<Message> getMessageForYear(int year){
		List<Message> messageForYear = new ArrayList<>();
		Calendar cal =  Calendar.getInstance();
		for(Message message: messages.values()){
			cal.setTime(message.getCreated());
			if(cal.get(Calendar.YEAR) == year){
				messageForYear.add(message);
			}
		}
		return messageForYear;
	}
	
	
	public List<Message> getAllMessagesPaginated(int start, int size){
		ArrayList<Message> list = new ArrayList<Message>(messages.values());
		if(start + size > list.size()) return new ArrayList<>();
		return list.subList(start, size);
	}
	
	public Message addMessage(Message message){
		message.setId(messages.size()+1);
		messages.put(message.getId(), message);
		return message;
	}
	 public Message updateMessage(Message message){
		 if(message.getId() <= 0){
			 return null;
		 }
		 messages.put(message.getId(), message);
		 return message;
	 }
	public Message removeMessage(long id){
		if(messages.get(id).equals(null)){
			return null;
		}
		return messages.remove(id);
	}
}
