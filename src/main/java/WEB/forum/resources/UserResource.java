package WEB.forum.resources;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import WEB.forum.model.Comment;
import WEB.forum.model.Rapper;
import WEB.forum.model.SubForum;
import WEB.forum.model.Topic;
import WEB.forum.model.User;
import WEB.forum.model.User.Role;
import WEB.forum.service.UserService;


@Path("/users")
public class UserResource {

	UserService userService;
	
	public UserResource() {
		this.userService  = new UserService();
	}
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<User> getAllUsers() {
		return userService.getAllUsers();
		
	}
	
	@POST
	@Path("/{userID}")
	@Produces(MediaType.APPLICATION_JSON)
	public User getUser(@PathParam ("userID") String userID, String password) {
		return userService.getUser(userID, password);
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void addUser(User user) {
		userService.addUser(user);
	}
	
	@PUT
	@Path("/{userID}/{role}")
	public void setRole(@PathParam ("userID") String userID,
			@PathParam ("role") Role role, @HeaderParam ("userID") String adminID,
			@HeaderParam ("userPass") String adminPass) {
		userService.setRole(userID, role, adminID, adminPass);
	}
	
	@GET
	@Path("/search/{search}")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<User> searchUsers(@PathParam("search") String search) {
		return userService.searchUsers(search);
	}
	
	@PUT
	@Path("/saveForum/{forumTitle}")
	public void saveForum(@HeaderParam("userID") String userID, @HeaderParam("userPass") String userPass, 
			@PathParam("forumTitle") String forumTitle ) {
		userService.saveForum(userID, userPass, forumTitle);
	}
	
	@PUT
	@Path("/saveTopic/{forumTitle}/{topicTitle}")
	public void saveTopic(@HeaderParam("userID") String userID, @HeaderParam("userPass") String userPass, 
			@PathParam("forumTitle") String forumTitle, @PathParam("topicTitle") String topicTitle ) {
		userService.saveTopic(userID, userPass, forumTitle, topicTitle);
	}
	
	@PUT
	@Path("/saveComment/{forumTitle}/{topicTitle}/{id}")
	public void saveComment(@HeaderParam("userID") String userID, @HeaderParam("userPass") String userPass, 
			@PathParam("forumTitle") String forumTitle, @PathParam("topicTitle") String topicTitle, @PathParam("id") int id, Comment comment ) {
		userService.saveComment(userID, userPass, forumTitle, topicTitle, id, comment);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/savedForums")
	public ArrayList<SubForum> getSavedForums(@HeaderParam("userID") String userID, @HeaderParam("userPass") String userPass){
		return userService.getSavedForums(userID, userPass);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/savedTopics")
	public ArrayList<Topic> getSavedTopics(@HeaderParam("userID") String userID, @HeaderParam("userPass") String userPass){
		return userService.getSavedTopics(userID, userPass);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/savedComments")
	public ArrayList<Comment> getSavedComments(@HeaderParam("userID") String userID, @HeaderParam("userPass") String userPass){
		return userService.getSavedComments(userID, userPass);
	}
};
