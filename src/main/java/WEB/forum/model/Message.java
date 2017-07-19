package WEB.forum.model;

public class Message {
	
	private Long messageID;
	private User sender;
	private User reciever;
	private String content;
	private Boolean seen;
	
	public Message() {
		
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
