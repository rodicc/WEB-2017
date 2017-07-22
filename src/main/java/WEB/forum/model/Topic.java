package WEB.forum.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
public class Topic implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long topicID;
	private SubForum parentForum;
	private String title;
	private enum Type{TEXT, PIC, LINK};
	private Type type;
	private User author;
	private ArrayList<Comment> comments;
	private String content;
	private Date date;
	private Short likes;
	private Short disslikes;
	 
	public Topic(){
		this.comments = new ArrayList<Comment>();
	}
	
	public Topic(Long topicID, SubForum parent, String title, Type type, User author,
			ArrayList<Comment> comments, String content, Date date, Short likes, Short disslikes) {
		super();
		this.topicID = topicID;
		this.parentForum = parent;
		this.title = title;
		this.type = type;
		this.author = author;
		this.comments = comments;
		this.content = content;
		this.date = date;
		this.likes = likes;
		this.disslikes = disslikes;
	}
	
	
	
	/* GETTERS & SETTERS*/
	
	public SubForum getParentForum() {
		return parentForum;
	}
	public Long getTopicID() {
		return topicID;
	}
	public void setTopicID(Long topicID) {
		this.topicID = topicID;
	}
	public void setParentForum(SubForum parentForum) {
		this.parentForum = parentForum;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Type getType() {
		return type;
	}
	public void setType(Type type) {
		this.type = type;
	}
	public User getAuthor() {
		return author;
	}
	public void setAuthor(User author) {
		this.author = author;
	}
	public ArrayList<Comment> getComments() {
		return comments;
	}
	public void setComments(ArrayList<Comment> comments) {
		this.comments = comments;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Short getLikes() {
		return likes;
	}
	public void setLikes(Short likes) {
		this.likes = likes;
	}
	public Short getDisslikes() {
		return disslikes;
	}
	public void setDisslikes(Short disslikes) {
		this.disslikes = disslikes;
	}
	
}