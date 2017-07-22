package WEB.forum.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
public class Message implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long messageID;
	private User sender;
	private User reciever;
	private String content;
	private Boolean seen;
	
	public Message() {
		
	}
	
	public Message(Long messageID, User sender, User reciever, String content, Boolean seen) {
		this. messageID = messageID;
		this. sender = sender;
		this. reciever = reciever;
		this.content = content;
		this.seen = seen;
		
	}
	
	
	/* GETTERS & SETTERS*/
	
	public User getSender() {
		return sender;
	}
	public Long getMessageID() {
		return messageID;
	}
	public void setMessageID(Long messageID) {
		this.messageID = messageID;
	}
	public void setSender(User sender) {
		this.sender = sender;
	}
	public User getReciever() {
		return reciever;
	}
	public void setReciever(User reciever) {
		this.reciever = reciever;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Boolean getSeen() {
		return seen;
	}
	public void setSeen(Boolean seen) {
		this.seen = seen;
	}
	
}
