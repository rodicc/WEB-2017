package WEB.forum.resources;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import WEB.forum.data.Database;
import WEB.forum.model.User;
import WEB.forum.service.UserService;

@Path("/users")
public class UserResource {

	UserService userService = new UserService();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<User> getUsers() {
		return userService.getAllUsers();
		
	}
}
