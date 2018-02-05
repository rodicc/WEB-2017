package WEB.forum.model;

import java.io.Serializable;

public class Rapper implements Serializable{

	private String forumTitle;
	
	public Rapper() {
		
	}
	
	public Rapper(String title) {
		forumTitle = title;
	}

	public String getForumTitle() {
		return forumTitle;
	}

	public void setForumTitle(String forumTitle) {
		this.forumTitle = forumTitle;
	}
	
	
}
