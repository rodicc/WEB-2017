package WEB.forum.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;

import WEB.forum.model.Comment;
import WEB.forum.model.Rapper;
import WEB.forum.model.SubForum;
import WEB.forum.model.Topic;
import WEB.forum.model.TopicList;
import WEB.forum.model.User;
import WEB.forum.model.User.Role;
import javassist.expr.NewArray;


public class UserService {

	private ArrayList<User> users;
	private SubForumService subForumService;
	public static final String filePath = "C://Users/Dušan Rodić/Documents/ForumDB/users.dat";
	//public static final String filePath = "C://Users/TEMP.FTN.032/Desktop/users.dat";
	
	
	@SuppressWarnings("unchecked")
	public void loadUserData() {
		File f = new File(filePath);
		ObjectInputStream in = null;
		if (!f.exists()) {
			users = new ArrayList<User>();
			return;
		}
		try {
			if(f.length() > 0) {
				in = new ObjectInputStream(new FileInputStream(f));
				users = (ArrayList<User>)in.readObject();
			}
			else {
				users = new ArrayList<User>();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void writeUserData() {
		File f = new File(filePath);
		ObjectOutputStream out = null;
		if (!f.exists()) {
			try {
				f.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			out = new ObjectOutputStream(new FileOutputStream(f));
			out.writeObject(users);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void addUser(User user) {
		loadUserData();
		if(users != null) 
			for(User u : users) {
				if(u.getUserID().equals(user.getUserID()))
					return;
			}
		else {
			users = new ArrayList<User>();
		}
		user.setRegDate(LocalDate.now());
		users.add(user);
		writeUserData();
	}
	
	public ArrayList<User> getAllUsers(){
		loadUserData();
		return users;
	}
	
	public User getUser(String userID, String password) {
		loadUserData();
		if(users != null) {
			for(User u : users) 
				if(u.getUserID().equals(userID) && u.getPassword().equals(password)) {
					return u;
				}
		}
		return null;
		
	}
	
	public void removeUser (String userID) {
		loadUserData();
		for(User u : users) {
			if (u.getUserID().equals(userID))
				users.remove(u);
		}
		writeUserData();
	}
	
	public void setRole (String userID, Role role, String adminID , String adminPass ) {
		loadUserData();
		
		User admin = getUser(adminID, adminPass);
		if(admin != null) {
			if (admin.getRole().equals(Role.ADMIN))
				for(User u : users) {
					if (userID.equals(u.getUserID())) {
						u.setRole(role);
						writeUserData();
						return;
					}
				}
		}
	}
	
	public ArrayList<User> searchUsers(String search){
		loadUserData();
		ArrayList<User> result = new ArrayList<User>();
		
		if(users != null) {
			if(search.equals("$")) {
				return users;
			}
			for(User u : users) {
				if(u.getUserID().toLowerCase().contains(search.toLowerCase())) {
					result.add(u);
				}
			}
		}
		
		if(result.isEmpty()) {
			return null;
		}
	
		return result;
	}
	
	public void saveForum(String userID, String password, String forumTitle) {
		User user = getUser(userID, password);
		if(user != null) {
			subForumService = new SubForumService();
			subForumService.loadSubForumData();
			if(subForumService.getAllSubForums() != null) {
				for(SubForum forum : subForumService.getAllSubForums()) {
					if(forum.getTitle().equals(forumTitle)) {
						if(!user.getSavedForums().contains(forum)) {
							user.getSavedForums().add(forum);
						}
					}
				}
				writeUserData();
			}
		}
	}
	
	public void saveTopic(String userID, String password, String forumTitle, String topicTitle) {
		User user = getUser(userID, password);
		if(user != null) {
			if(user.getSavedTopics().containsKey(forumTitle)) {
				user.getSavedTopics().get(forumTitle).getTopics().add(topicTitle);
			}else {
				TopicList topicList = new TopicList();
				topicList.getTopics().add(topicTitle);
				user.getSavedTopics().put(forumTitle, topicList);
			}
			writeUserData();
		}
	}
	
	public void saveComment(String userID, String password, String forumTitle, String topicTitle, int id, Comment comment) {
		User user = getUser(userID, password);
		if(user != null) {
			String key = forumTitle + ":" + topicTitle + ":" + id;
			if(!user.getSavedComments().containsKey(key)) {
				user.getSavedComments().put(key, comment);
				writeUserData();
			}
		}
	}
	
	public ArrayList<SubForum> getSavedForums(String userID, String password){
		User user = getUser(userID, password);
		if(user != null) {
			return user.getSavedForums();
		}
		return null;
	}
	
	public ArrayList<Topic> getSavedTopics(String userID, String password){
		User user = getUser(userID, password);
		if(user != null) {
			ArrayList<Topic> result = new ArrayList<Topic>();
			subForumService = new SubForumService();
			subForumService.loadSubForumData();
			if(subForumService.getAllSubForums() != null) {
				for(SubForum forum : subForumService.getAllSubForums()) {
					if(user.getSavedTopics().containsKey(forum.getTitle())) {
						for(Topic topic : forum.getTopics()) {
							if(user.getSavedTopics().get(forum.getTitle()).getTopics().contains(topic.getTitle())) {
								result.add(topic);
							}
						}
					}
				}
			}
			return result;
			
		}
		return null;
	}
	
	public ArrayList<Comment> getSavedComments(String userID, String password){
		User user = getUser(userID, password);
		if(user != null) {
			ArrayList<Comment> result = new ArrayList<Comment>();
			for(Comment comment : user.getSavedComments().values()) {
				result.add(comment);
			}
			return result;
		}
		
		return null;
	}
	
}
