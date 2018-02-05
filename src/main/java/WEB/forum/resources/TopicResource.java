package WEB.forum.resources;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import WEB.forum.model.Comment;
import WEB.forum.model.Topic;
import WEB.forum.service.TopicService;

@Path("/{parentForum}/topics")
public class TopicResource {

	private TopicService topicService = new TopicService();
	
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void addTopic(@HeaderParam("userID") String userID, @HeaderParam("userPass") String userPass, 
			@PathParam("parentForum") String parentForumTitle, Topic newTopic) {
		topicService.addTopic(userID, userPass, parentForumTitle, newTopic);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Topic> getAllTopics(@PathParam("parentForum") String parentForumTitle) {
		return topicService.getAllTopics(parentForumTitle);
	}
	
	@GET
	@Path("/{topicTitle}")
	@Produces(MediaType.APPLICATION_JSON)
	public Topic getTopic(@PathParam("parentForum") String parentForumTitle, @PathParam("topicTitle")String topicTitle) {
		return topicService.getTopic(parentForumTitle, topicTitle);
	}
	
	@DELETE
	@Path("/{topicTitle}")
	public void removeTopic(@HeaderParam("userID") String userID, @HeaderParam("userPass") String userPass, @PathParam("parentForum") String parentForumTitle, @PathParam("topicTitle") String topicTitle){
		topicService.removeTopic(userID, userPass, parentForumTitle, topicTitle);
	}
	
	@PUT
	@Path("/{topicTitle}")
	@Consumes(MediaType.APPLICATION_JSON)
	public void editTopic(@HeaderParam("userID") String userID, @HeaderParam("userPass") String userPass,
			@PathParam("parentForum") String parentForumTitle, Topic topic){
		topicService.editTopic(userID, userPass, parentForumTitle, topic);
	}
	
	@POST
	@Path("/{topicTitle}/vote")
	@Consumes(MediaType.APPLICATION_JSON)
	public boolean topicVote (@HeaderParam("userID") String userID, @HeaderParam("userPass") String userPass, 
			@PathParam("parentForum") String parentForumTitle, @PathParam("topicTitle")String parentTopicTitle, String vote){
		return topicService.topicVote(userID, userPass, parentForumTitle, parentTopicTitle, vote);
	}
	
	@POST
	@Path("/{topicTitle}/comment")
	@Consumes(MediaType.APPLICATION_JSON)
	public void addComment(@HeaderParam("userID") String userID, @HeaderParam("userPass") String userPass, 
			@PathParam("parentForum") String parentForumTitle, @PathParam("topicTitle")String parentTopicTitle, Comment newComment) {
		topicService.addComment(userID, userPass, parentForumTitle, parentTopicTitle, newComment); 
	
	}
	
	@DELETE
	@Path("/{topicTitle}/comment/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public void removeComment(@HeaderParam("userID") String userID, @HeaderParam("userPass") String userPass, 
			@PathParam("parentForum") String parentForumTitle, @PathParam("topicTitle")String parentTopicTitle, @PathParam("id") int id) {
		topicService.removeComment(userID, userPass, parentForumTitle, parentTopicTitle, id); 
	}
	
	@PUT
	@Path("/{topicTitle}/comment/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public void editComment(@HeaderParam("userID") String userID, @HeaderParam("userPass") String userPass, 
			@PathParam("parentForum") String parentForumTitle, @PathParam("topicTitle")String parentTopicTitle, @PathParam("id") int id, String newContent) {
		topicService.editComment(userID, userPass, parentForumTitle, parentTopicTitle, id, newContent); 
	}
	
	@POST
	@Path("/{topicTitle}/comment/{id}/vote")
	@Consumes(MediaType.APPLICATION_JSON)
	public boolean commentVote (@HeaderParam("userID") String userID, @HeaderParam("userPass") String userPass, 
			@PathParam("parentForum") String parentForumTitle, @PathParam("topicTitle")String parentTopicTitle, @PathParam("id") int id, String vote){
		return topicService.commentVote(userID, userPass, parentForumTitle, parentTopicTitle, id, vote);
	}
	
	@GET
	@Path("/search")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Topic> searchTopics(@PathParam("parentForum")String parentForum, @HeaderParam("titleSearch")String titleSearch,
			@HeaderParam("contentSearch")String contentSearch, @HeaderParam("authorSearch")String authorSearch){
		return topicService.searchTopics(parentForum, titleSearch, contentSearch, authorSearch);
	}
	
	
}
