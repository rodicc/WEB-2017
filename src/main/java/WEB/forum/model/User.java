package WEB.forum.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class User implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String userID;
	private char[] password;
	private String name;
	private String surname;
	public enum Role{DEFAULT, MODERATOR, ADMIN};
	private Role role;
	private String phoneNumber;
	private String email;	
	private Date regDate;
	private ArrayList<SubForum> forums;
	private ArrayList<Topic> topics;
	private ArrayList<Comment> comments;
	
	public User() {
		forums = new ArrayList<SubForum>();
		topics = new ArrayList<Topic>();
		comments = new ArrayList<Comment>();
	}
	
	public User(String userID, char[] password, String name, String surname,
			Role role, String phoneNumber,String email, Date date, ArrayList<SubForum> forums,
			ArrayList<Topic> topics, ArrayList<Comment> comments  ) {
		super();
		this.userID = userID;
		this.password = password;
		this.name = name;
		this.surname = surname;
		this.role = role;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.regDate = date;
		this.forums = forums;
		this.topics = topics;
		this.comments = comments;
	}
	
	
	
	/* GETTERS & SETTERS*/
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userId) {
		this.userID = userId;
	}
	public char[] getPassword() {
		return password;
	}
	public void setPassword(char[] password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public ArrayList<SubForum> getForums() {
		return forums;
	}
	public void setForums(ArrayList<SubForum> forums) {
		this.forums = forums;
	}
	public ArrayList<Topic> getTopics() {
		return topics;
	}
	public void setTopics(ArrayList<Topic> topics) {
		this.topics = topics;
	}
	public ArrayList<Comment> getComments() {
		return comments;
	}
	public void setComments(ArrayList<Comment> comments) {
		this.comments = comments;
	}
	
}
