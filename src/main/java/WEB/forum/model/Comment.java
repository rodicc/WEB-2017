package WEB.forum.model;

import java.sql.Date;
import java.util.ArrayList;

public class Comment {

	private Long commentID;
	private Topic parenttopic;
	private User author;
	private Date date;
	private Comment parentComment;
	private ArrayList<Comment> childComments;
	private String content;
	private Short likes;
	private Short disslikes;
	private Boolean edited;
	
	public Comment() {
		
	}
	
	
	
	/* GETTERS & SETTERS*/
	
	public Topic getParenttopic() {
		return parenttopic;
	}
	public Long getCommentID() {
		return commentID;
	}
	public void setCommentID(Long commentID) {
		this.commentID = commentID;
	}
	public void setParenttopic(Topic parenttopic) {
		this.parenttopic = parenttopic;
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
