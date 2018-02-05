package WEB.forum.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import WEB.forum.model.Chat;
import WEB.forum.model.Message;
import WEB.forum.model.User;

public class ChatService {		

	public static final String filePath = "C://Users/Dušan Rodić/Documents/ForumDB/messages.dat";
	private HashMap<String, Chat> chats;
	private UserService userService = new UserService();
	
	
	public void sendMessage(String userID, String userPass, String reciever, Message message) {
		User sender = userService.getUser(userID, userPass);
		String key;
		
		if(sender != null && !sender.getUserID().equals(reciever)) {
			if(userID.compareTo(reciever)<0) {
				key = userID + "$" + reciever;
			}else {
				key = reciever + "$" + userID;
			}
			
			loadChatData();
			if(chats != null) {
				if(chats.containsKey(key)) {
					chats.get(key).getMessages().add(message);
				}else {
					Chat c = new Chat();
					if(userID.compareTo(reciever)<0) {
						c.setUser1(userID);
						c.setUser2(reciever);
					}else {
						c.setUser1(reciever);
						c.setUser2(userID);
					}
					c.getMessages().add(message);
					chats.put(key, c);
				}
			}else {
				chats = new HashMap<String, Chat>();
				Chat c = new Chat();
				if(userID.compareTo(reciever)<0) {
					c.setUser1(userID);
					c.setUser2(reciever);
				}else {
					c.setUser1(reciever);
					c.setUser2(userID);
				}
				c.getMessages().add(message);
				chats.put(key, c);	
			}
			writeChatData();
			
			/*if(!sender.getRecipients().contains(reciever)) {
				sender.getRecipients().add(reciever);
			}*/
			userService.loadUserData();
			for(User u :  userService.getAllUsers()) {
				if(u.getUserID().equals(reciever)) {
					if(!u.getRecipients().contains(sender.getUserID())) {
						u.getRecipients().add(sender.getUserID());
						userService.writeUserData();
						break;
					}
				}
			}
			
		}
	}
	
	
	
	public ArrayList<Message> getChat(String userID, String userPass, String reciever) {
		User sender = userService.getUser(userID, userPass);
		String key;
		
		if(sender != null) {
			loadChatData();
			if(userID.compareTo(reciever)<0) {
				key = userID + "$" + reciever;
			}else {
				key = reciever + "$" + userID;
			}
			
			if(chats != null) {
				
				if(chats.containsKey(key)) {
					return chats.get(key).getMessages();
				}
			}
		}
		return null;
	}
	
	
	public void loadChatData() {
		File f = new File(filePath);
		ObjectInputStream in = null;
		if (!f.exists()) {
			chats = new HashMap<String, Chat>();
			return;
		}
		try {
			if (f.length() > 0) {
				in = new ObjectInputStream(new FileInputStream(f));
				chats = (HashMap<String, Chat>) in.readObject();
			}
			else{
				chats = new HashMap<String, Chat>();
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
	
	public void writeChatData() {
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
			out.writeObject(chats);
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
	
}
