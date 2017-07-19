package WEB.forum.model;

import java.sql.Date;
import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class User {

	
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
		userID = new String("");
		password = new char[0] ;
		name = new String("");
		surname = new String("");
		role = Role.DEFAULT;
		phoneNumber = new String("");
		email = new String("");
		regDate = new Date(0);
		forums = new ArrayList<SubForum>();
		topics = new ArrayList<Topic>();
		comments = new ArrayList<Comment>();
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
