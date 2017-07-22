package WEB.forum.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;


import WEB.forum.model.User;

public class UserService {

	private ArrayList<User> users;
	private  String filePath = "..//users.dat";
	
	
	
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
		users.add(user);
		writeUserData();
	}
	
	
	public ArrayList<User> getAllUsers(){
		loadUserData();
		return users;
	}
	
	public User getUser(String userID, char[] password) {
		loadUserData();
		if(users != null) {
			for(User u : users) 
				if(u.getUserID().equals(userID) && u.getPassword().equals(password)) 
					return u;
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
	
}
