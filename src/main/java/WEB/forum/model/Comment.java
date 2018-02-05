package WEB.forum.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
public class Comment implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int commentID;
	private String parentTopic;
	private String author;
	private LocalDate date;
	private Comment parentComment;
	//private ArrayList<Comment> childComments;
	private String content;
	private int likes;
	private int disslikes;
	private Boolean edited;
	private Boolean deleted;
	private ArrayList<String> alreadyVoted;
	
	public Comment() {
		this.alreadyVoted = new ArrayList<String>();
	}
	
	public Comment(int commentID, String parentTopic, String author, LocalDate date, Comment parentComment, 
			String content, int likes, int disslikes, Boolean edited, Boolean deleted) { //ArrayList<Comment> childComments,
		super();
		this.commentID = commentID;
		this.parentTopic = parentTopic;
		this.author = author;
		this.date = date;
		this.parentComment = parentComment;
		//this.alreadyVoted = alreadyVoted;
		this.content = content;
		this.likes = likes;
		this.disslikes = disslikes;
		this.edited = edited;
		this.deleted = deleted;
	}
	
	
	
	/* GETTERS & SETTERS*/
	
	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	public void setParentTopic(String parentTopic) {
		this.parentTopic = parentTopic;
	}

	public String getParentTopic() {
		return parentTopic;
	}
	public int getCommentID() {
		return commentID;
	}
	public void setCommentID(int commentID) {
		this.commentID = commentID;
	}
	public void setParenttopic(String parentTopic) {
		this.parentTopic = parentTopic;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public Comment getParentComment() {
		return parentComment;
	}
	public void setParentComment(Comment parentComment) {
		this.parentComment = parentComment;
	}
	public ArrayList<String> getAlreadyVoted() {
		return alreadyVoted;
	}
	public void setAlreadyVoted(ArrayList<String> alreadyVoted) {
		this.alreadyVoted = alreadyVoted;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getLikes() {
		return likes;
	}
	public void setLikes(int likes) {
		this.likes = likes;
	}
	public int getDisslikes() {
		return disslikes;
	}
	public void setDisslikes(int disslikes) {
		this.disslikes = disslikes;
	}
	public Boolean getEdited() {
		return edited;
	}
	public void setEdited(Boolean edited) {
		this.edited = edited;
	}
	
}
