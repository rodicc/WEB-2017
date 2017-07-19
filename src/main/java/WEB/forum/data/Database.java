package WEB.forum.data;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class Database {

	private static File users = new File("..//users.txt");
	private File topics;
	private File comments;
	private File messages;
	private File subForums;
	
	
	public void writeObj(Object obj) {
		ObjectMapper mapper = new ObjectMapper();
		
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		
		try {
			mapper.writeValue(System.out, obj);
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
	
	
	
	/*private static Map<Long, User> users = new HashMap<>();
	private static Map<Long, SubForum> subForums = new HashMap<>();
	private static Map<Long, Topic> topics = new HashMap<>();
	private static Map<Long, Comment> comments = new HashMap<>();
	private static Map<Long, Message> messages = new HashMap<>();
	
	
	
	public static Map<Long, User> getUsers() {
		return users;
	}
	public static Map<Long, SubForum> getSubForums() {
		return subForums;
	}
	public static Map<Long, Topic> getTopics() {
		return topics;
	}
	public static Map<Long, Comment> getComments() {
		return comments;
	}
	public static Map<Long, Message> getMessages() {
		return messages;
	}
	*/
	
	
	
}
