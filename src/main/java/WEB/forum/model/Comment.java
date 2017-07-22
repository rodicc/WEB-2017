package WEB.forum.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
public class Comment implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long commentID;
	private Topic parentTopic;
	private User author;
	private Date date;
	private Comment parentComment;
	private ArrayList<Comment> childComments;
	private String content;
	private Short likes;
	private Short disslikes;
	private Boolean edited;
	
	public Comment() {
		this. childComments = new ArrayList<Comment>();
	}
	
	public Comment(Long commentID, Topic parentTopic, User author, Date date, Comment parentComment, 
			ArrayList<Comment> childComments,String content, Short likes, Short disslikes, Boolean edited) {
		super();
		this.commentID = commentID;
		this.parentTopic = parentTopic;
		this.author = author;
		this.date = date;
		this.parentComment = parentComment;
		this.childComments = childComments;
		this.content = content;
		this.likes = likes;
		this.disslikes = disslikes;
		this.edited = edited;
	}
	
	
	
	/* GETTERS & SETTERS*/
	
	public Topic getParentTopic() {
		return parentTopic;
	}
	public Long getCommentID() {
		return commentID;
	}
	public void setCommentID(Long commentID) {
		this.commentID = commentID;
	}
	public void setParenttopic(Topic parentTopic) {
		this.parentTopic = parentTopic;
	}
	public User getAuthor() {
		return author;
	}
	public void setAuthor(User author) {
		this.author = author;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Comment getParentComment() {
		return parentComment;
	}
	public void setParentComment(Comment parentComment) {
		this.parentComment = parentComment;
	}
	public ArrayList<Comment> getChildComments() {
		return childComments;
	}
	public void setChildComments(ArrayList<Comment> childComments) {
		this.childComments = childComments;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
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
	public Boolean getEdited() {
		return edited;
	}
	public void setEdited(Boolean edited) {
		this.edited = edited;
	}
	
}
