package WEB.forum.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
public class Message implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String sender;
	private String reciever;
	private String content;
	
	public Message() {
		this. sender = "";
		this. reciever = "";
		this.content = "";
	}
	
	public Message( String sender, String reciever, String content) {
		
		super();
		
		this. sender = sender;
		this. reciever = reciever;
		this.content = content;
	}
	
	
	/* GETTERS & SETTERS*/
	
	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getReciever() {
		return reciever;
	}
	public void setReciever(String reciever) {
		this.reciever = reciever;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
}
