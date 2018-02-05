package WEB.forum.model;

import java.io.Serializable;
import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
public class Chat implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String user1;
	private String user2;
	private ArrayList<Message> messages;
	
	
	
	public Chat() {
		messages = new ArrayList<Message>();
	}
	
	
	public Chat(String user1, String user2, ArrayList<Message> messages) {
		super();
		this.user1 = user1;
		this.user2 = user2;
		this.messages = messages;
	}
	
	

	public String getUser1() {
		return user1;
	}
	public void setUser1(String user1) {
		this.user1 = user1;
	}
	public String getUser2() {
		return user2;
	}
	public void setUser2(String user2) {
		this.user2 = user2;
	}
	public ArrayList<Message> getMessages() {
		return messages;
	}
	public void setMessages(ArrayList<Message> messages) {
		this.messages = messages;
	}
	
}
