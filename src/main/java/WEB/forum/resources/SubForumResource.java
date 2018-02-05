package WEB.forum.resources;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import WEB.forum.model.SubForum;
import WEB.forum.model.Topic;
import WEB.forum.service.SubForumService;

@Path("/subforums")
public class SubForumResource {
	
	SubForumService subForumService;
	
	public SubForumResource() {
		this.subForumService = new SubForumService();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<SubForum> getAllSubForums(){
		return subForumService.getAllSubForums();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void addSubForum(@HeaderParam("ownerID") String ownerID, @HeaderParam("ownerPass")String ownerPass, SubForum subForum) {
		subForumService.addSubForum(ownerID, ownerPass, subForum);
	}
	
	@DELETE
	@Path("/{title}")
	public void deleteSubForum(@PathParam("title") String title, @HeaderParam("userID") String userID, @HeaderParam("userPass")String userPass) {
		subForumService.removeSubForum(title, userID, userPass);
	}
	@GET
	@Path("/{title}")
	@Produces(MediaType.APPLICATION_JSON)
	public SubForum getSubForum(@PathParam("title") String title) {
		return subForumService.getSubForum(title);
	}
	
	@GET
	@Path("/search")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<SubForum> searchSubForums(@HeaderParam("titleSearch") String titleSearch, @HeaderParam("modSearch") String modSearch,
			@HeaderParam("descSearch") String descSearch){
		return subForumService.searchSubForums(titleSearch, descSearch, modSearch);
	}
	
	@GET
	@Path("/trending")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Topic> getTrendingTopics(){
		return subForumService.getTrendingTopics();
	}
}
