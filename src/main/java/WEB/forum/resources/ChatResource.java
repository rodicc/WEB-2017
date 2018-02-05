package WEB.forum.resources;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import WEB.forum.service.ChatService;
import WEB.forum.model.Message;

@Path("/messages")
public class ChatResource {

	private ChatService chatService = new ChatService();
	
	@GET
	@Path("/{user}")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Message> getChat(@HeaderParam("userID") String userID, @HeaderParam("userPass") String userPass,
				@PathParam("user") String reciever){
		return chatService.getChat(userID, userPass, reciever);
		
	}
	
	@POST
	@Path("/{user}")
	@Consumes(MediaType.APPLICATION_JSON)
	public void sendMessage(@PathParam("user") String reciever, @HeaderParam("userID") String userID,@HeaderParam("userPass") String userPass,
			Message message){
		chatService.sendMessage(userID, userPass, reciever, message);
	}
}

