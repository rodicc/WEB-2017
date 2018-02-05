package WEB.forum.service;

import java.awt.geom.CubicCurve2D;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;

import WEB.forum.model.SubForum;
import WEB.forum.model.Topic;
import WEB.forum.model.User;
import WEB.forum.model.User.Role;

public class SubForumService {

	private String filePath = "C://Users/Dušan Rodić/Documents/ForumDB/forums.dat";
	//private String filePath = "C://Users/TEMP.FTN.032/Desktop/forums.dat";
	private ArrayList<SubForum> subForums;
	private UserService userService = new UserService();
	
	public void loadSubForumData() {
		File f = new File(filePath);
		ObjectInputStream in = null;
		if (!f.exists()) {
			subForums = new ArrayList<SubForum>();
			return;
		}
		try {
			if (f.length() > 0) {
				in = new ObjectInputStream(new FileInputStream(f));
				subForums = (ArrayList<SubForum>) in.readObject();
			}
			else{
				subForums = new ArrayList<SubForum>();
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
	
	public void writeSubForumData() {
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
			out.writeObject(subForums);
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

	public void addSubForum(String userID, String password, SubForum subForum) {
		
		User user = userService.getUser(userID, password);
		if((user.getRole()==Role.ADMIN || user.getRole()==Role.MODERATOR)) {
			if(user != null) {
				loadSubForumData();
				if (subForums != null) {
					for (SubForum sf : subForums) {
						if(sf.getTitle().equals(subForum.getTitle())){
							return;
						}
					}				
				} else 
					subForums = new ArrayList<SubForum>();
			}
			subForums.add(subForum);
			subForum.setChiefModerator(user.getUserID());
			subForum.getAllModerators().add(user.getUserID());
			writeSubForumData();
		}
	}
	
	public ArrayList<SubForum> getAllSubForums(){
		loadSubForumData();
		return subForums;
	}
	
	public void setSubForums(ArrayList<SubForum> forums) {
		this.subForums = forums;
	}
	
	
	public SubForum getSubForum(String title) {
		loadSubForumData();
		if(subForums != null)
			for(SubForum sf : subForums)  
				if(sf.getTitle().equals(title))
					return sf;
			
		return null;
	}
	
	public void removeSubForum(String title, String userID, String password) {
		loadSubForumData();
		User user = userService.getUser(userID, password);
		SubForum sf = getSubForum(title);
		if(user !=null)
			if(sf.getAllModerators().contains(user.getUserID())) {
				subForums.remove(sf);
				writeSubForumData();
				return;
			}	
	}	
	
	public ArrayList<SubForum> searchSubForums(String titleSearch, String descSearch, String modSearch ){
		loadSubForumData();
		ArrayList<SubForum> result = new ArrayList<SubForum>();
		if(subForums != null) {
			if(titleSearch.equals("$") && descSearch.equals("$") && modSearch.equals("$")) {
				return subForums;
			}
			
			for(SubForum forum : subForums) {
				if(forum.getTitle().toLowerCase().contains(titleSearch.toLowerCase()) || forum.getChiefModerator().toLowerCase().contains(modSearch.toLowerCase()) ||
						forum.getDescription().toLowerCase().contains(descSearch.toLowerCase())) {
					result.add(forum);
				}
			}
		}
		
		if (result.isEmpty())
			return null;
		else
			return result;
	}
	
	public ArrayList<Topic> getTrendingTopics(){
		loadSubForumData();
		ArrayList<Topic> result = new ArrayList<>();
		if(subForums != null) {
			ArrayList<Topic> allTopics = new ArrayList<Topic>();
			for(SubForum forum : subForums) {
				if(!forum.getTopics().isEmpty()) {
					for(Topic topic : forum.getTopics()) {
						allTopics.add(topic);
					}
				}
			}
			Collections.sort(allTopics);
			
			for(int i=1; i<6; i++) {
				if(i < allTopics.size()) {
					result.add(allTopics.get(i));
				}
			}
		}
		return result;
	}
}