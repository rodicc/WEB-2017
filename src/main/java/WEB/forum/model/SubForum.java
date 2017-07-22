package WEB.forum.model;

import java.io.Serializable;
import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
public class SubForum implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long subForumID;
	private String title;
	private String description;
	private String iconURL;
	private String chiefModerator;
	private ArrayList<User> allModerators;
	
	public SubForum() {
		this.allModerators = new ArrayList<User>();
	}
	
	public SubForum(Long subForumID, String title, String description, String iconURL, String chiefModerator,ArrayList<User> allModerators ) {
		super();
		this.subForumID = subForumID;
		this.title = title;
		this.description = description;
		this.iconURL = iconURL;
		this.chiefModerator = chiefModerator;
		this.allModerators = allModerators;
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
	
	public String getIconURL() {
		return iconURL;
	}
	public void setIconURL(String iconURL) {
		this.iconURL = iconURL;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getChiefModerator() {
		return chiefModerator;
	}
	public void setChiefModerator(String chiefModerator) {
		this.chiefModerator = chiefModerator;
	}
	public ArrayList<User> getAllModerators() {
		return allModerators;
	}
	public void setAllModerators(ArrayList<User> allModerators) {
		this.allModerators = allModerators;
	}
}
