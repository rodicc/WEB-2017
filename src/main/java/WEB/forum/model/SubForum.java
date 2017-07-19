package WEB.forum.model;

import java.util.ArrayList;

public class SubForum {

	private Long subForumID;
	private String title;
	private String description;
	// TODO ikonica
	private User chiefModerator;
	private ArrayList<User> allModerators;
	
	public SubForum() {
	
	}
	
	
	/* GETTERS & SETTERS*/
	public String getTitle() {
		return title;
	}
	public Long getSubForumID() {
		return subForumID;
	}
	public void setSubForumID(Long subForumID) {
		this.subForumID = subForumID;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public User getChiefModerator() {
		return chiefModerator;
	}
	public void setChiefModerator(User chiefModerator) {
		this.chiefModerator = chiefModerator;
	}
	public ArrayList<User> getAllModerators() {
		return allModerators;
	}
	public void setAllModerators(ArrayList<User> allModerators) {
		this.allModerators = allModerators;
	}
}
