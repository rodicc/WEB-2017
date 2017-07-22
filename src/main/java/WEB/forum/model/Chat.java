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
	
	private User user1;
	private User user2;
	private ArrayList<Message> messages;
	
	
	
	public Chat() {
	
	}
	
	
	public Chat(User user1, User user2, ArrayList<Message> messages) {
		super();
		this.user1 = user1;
		this.user2 = user2;
		this.messages = messages;
	}
	
	

	public User getUser1() {
		return user1;
	}
	public void setUser1(User user1) {
		this.user1 = user1;
	}
	public User getUser2() {
		return user2;
	}
	public void setUser2(User user2) {
		this.user2 = user2;
	}
	public ArrayList<Message> getMessages() {
		return messages;
	}
	public void setMessages(ArrayList<Message> messages) {
		this.messages = messages;
	}
	
}
