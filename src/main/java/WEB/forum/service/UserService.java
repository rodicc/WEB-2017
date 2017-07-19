package WEB.forum.service;

import java.util.ArrayList;
import java.util.Map;

import WEB.forum.data.Database;
import WEB.forum.model.User;

public class UserService {

	private Database db;
	
	// TEST
	public ArrayList<User> getAllUsers(){
		User user1 = new User();
		user1.setUserID("user1");
		user1.setRole(User.Role.DEFAULT);
		user1.setEmail("a@b.com");
		user1.setSurname("Rodic");
		
		User user2 = new User();
		user2.setUserID("admin1");
		user2.setRole(User.Role.ADMIN);
		
		ArrayList<User> users = new ArrayList<User>();
		users.add(user1);
		users.add(user2);
		
		//exception
		//db.writeObj(user1);
		//db.writeObj(user2);
		return users;
	}
}
