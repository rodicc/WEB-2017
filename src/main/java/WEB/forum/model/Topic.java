package WEB.forum.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Base64;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement

public class Topic implements Serializable, Comparable<Topic>{		

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long topicID;
	private String parentForum;
	private String title;
	private String author;
	private ArrayList<Comment> comments;
	private String content;
	private LocalDate date;
	private int likes;
	private int disslikes;
	private ArrayList<String> alreadyVoted;
	private String imgString;
	 
	
	public Topic(){
		this.comments = new ArrayList<Comment>();
		this.alreadyVoted = new ArrayList<String>();
	}
	

	public Topic(Long topicID, String parent, String title, String author,
			ArrayList<Comment> comments, String content, LocalDate date, Short likes, Short disslikes) {
		super();
		this.topicID = topicID;
		this.parentForum = parent;
		this.title = title;
		this.author = author;
		this.comments = comments;
		this.content = content;
		this.date = date;
		this.likes = likes;
		this.disslikes = disslikes;
	}
	
	
	
	/* GETTERS & SETTERS*/
	
	public String getParentForum() {
		return parentForum;
	}
	public Long getTopicID() {
		return topicID;
	}
	public void setTopicID(Long topicID) {
		this.topicID = topicID;
	}
	public void setParentForum(String parentForum) {
		this.parentForum = parentForum;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
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
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate localDate) {
		this.date = localDate;
	}
	public int getLikes() {
		return likes;
	}
	public void setLikes(int i) {
		this.likes = i;
	}
	public int getDisslikes() {
		return disslikes;
	}
	public void setDisslikes(int i) {
		this.disslikes = i;
	}
	
	public ArrayList<String> getAlreadyVoted() {
		return alreadyVoted;
	}

	public void setAlreadyVoted(ArrayList<String> alreadyVoted) {
		this.alreadyVoted = alreadyVoted;
	}
	
	public String getImgString() {
		return imgString;
	}

	public void setImgString(String imgString) {
		this.imgString = imgString;
	}
	
	@Override
	public int compareTo(Topic t) {
		return -((likes - disslikes) - (t.getLikes()-t.getDisslikes()));
	}
	
}