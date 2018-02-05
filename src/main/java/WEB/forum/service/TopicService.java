package WEB.forum.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;

import WEB.forum.model.Comment;
import WEB.forum.model.SubForum;
import WEB.forum.model.Topic;
import WEB.forum.model.User;
import WEB.forum.model.User.Role;


public class TopicService {

	
	private SubForumService subForumService = new SubForumService();
	private UserService userService = new UserService();
	
	private ArrayList<SubForum> subForums;
	
	
	public void addTopic(String userID, String password, String parentForumTitle, Topic topic) {
		User user = userService.getUser(userID, password);
		if(user != null) {
			subForumService.loadSubForumData();
			subForums = subForumService.getAllSubForums();
			SubForum parentForum = subForumService.getSubForum(parentForumTitle);
			ArrayList<Topic> parentForumTopics = parentForum.getTopics();
			if(parentForumTopics != null) {
				if(topic.getTitle() == null && !topic.getTitle().equals("")) {
					System.out.println("title je null");
				}
				for(Topic tpc : parentForumTopics) {
					if (tpc.getTitle().equals(topic.getTitle()))
							return;
					}
			}
			else {
				parentForum.setTopics(new ArrayList<Topic>());
			}
				topic.setDate(LocalDate.now());
				topic.setLikes(0);
				topic.setDisslikes(0);
				topic.setAuthor(user.getUserID());
				topic.setParentForum(parentForum.getTitle());
				
				subForumService.getSubForum(parentForumTitle).getTopics().add(topic);
				
				subForumService.writeSubForumData();
		}
	}
	
	public ArrayList<Topic> getAllTopics(String forumTitle){
		subForumService.loadSubForumData();
		subForums = subForumService.getAllSubForums();
		if(subForums != null){
			
			for(SubForum forum : subForums){
				if(forum.getTitle().equals(forumTitle)){
					return forum.getTopics();
				}
			}
		}
		return null;
	}
	
	public Topic getTopic(String forumTitle, String topicTitle) {
		subForumService.loadSubForumData();
		subForums = subForumService.getAllSubForums();
		if(subForums != null){
			for(SubForum forum : subForums){
				if(forum.getTitle().equals(forumTitle)){
					ArrayList<Topic> topics = forum.getTopics();
					
					for(Topic topic : topics) {
						if(topic.getTitle().equals(topicTitle)) {
							return topic;
						}
					}
				}
			}
		}
		return null;
	}
	
	public void removeTopic(String userId, String userPass, String forumTitle, String topicTitle) {
		User user = userService.getUser(userId, userPass);
		if(user != null) {
			subForumService.loadSubForumData();
			subForums = subForumService.getAllSubForums();
			if(subForums != null){
				for(SubForum forum : subForums){
					if(forum.getTitle().equals(forumTitle)) {
						int i = 0;
						for(Topic topic : forum.getTopics()) {
							if(topic.getTitle().equals(topicTitle)) {
								subForumService.getSubForum(forumTitle).getTopics().remove(i);
								//nije htelo sa remove(Object o)
								subForumService.writeSubForumData();
								return;
							}
							i++;
						}
					}
				}
			}
		}
	}
	
	public ArrayList<Topic> searchTopics(String parentForum, String titleSearch, String contentSearch, String authorSearch){
		subForumService.loadSubForumData();
		subForums = subForumService.getAllSubForums();
		ArrayList<Topic> result = new ArrayList<Topic>();
		
		if(subForums != null) {
			
			
			for(SubForum forum : subForums) {
				if(forum.getTitle().equals(parentForum)) {
					ArrayList<Topic> topics = forum.getTopics();
					if(titleSearch.equals("$") && contentSearch.equals("$") && authorSearch.equals("$")) {
						return topics;
					}
					for(Topic topic : topics) {
						if(topic.getTitle().toLowerCase().contains(titleSearch.toLowerCase()) || topic.getContent().toLowerCase().contains(contentSearch.toLowerCase()) || 
							topic.getAuthor().toLowerCase().contains(authorSearch.toLowerCase())) {
							result.add(topic);
						}
					}
				}
			}
		}
		
		if (result.isEmpty())
			return null;
		else
			return result;
		
	
	}
	
	public void editTopic(String userID, String userPass, String forumTitle, Topic topic) {
		
		User user = userService.getUser(userID, userPass);
		if(user != null) {
			subForumService.loadSubForumData();
			subForums = subForumService.getAllSubForums();
			SubForum parentForum = subForumService.getSubForum(forumTitle);
			ArrayList<Topic> parentForumTopics = parentForum.getTopics();
			if(parentForumTopics != null) {
				for(Topic tpc : parentForumTopics) {
					if (tpc.getTitle().equals(topic.getTitle())) {
						if(user.getRole()== Role.ADMIN ||
								parentForum.getChiefModerator().equals(user.getUserID()) ||
								 tpc.getAuthor().equals(userID)) {
							tpc.setContent(topic.getContent());
							tpc.setImgString(topic.getImgString());
							subForumService.writeSubForumData();
							return;
						}
					}
			
				}
			}
		}
	}
	
	public void addComment(String userID, String userPass, 
			String parentForumTitle, String parentTopicTitle, Comment newComment) {
		User user = userService.getUser(userID, userPass);
		if(user != null) {
			subForumService.loadSubForumData();
			subForums = subForumService.getAllSubForums();
			SubForum parentForum = subForumService.getSubForum(parentForumTitle);
			ArrayList<Topic> parentForumTopics = parentForum.getTopics();
			if(parentForumTopics != null) {
				for(Topic tpc : parentForumTopics) {
					if (tpc.getTitle().equals(parentTopicTitle)) {
						newComment.setAuthor(user.getUserID());
						newComment.setDate(LocalDate.now());
						newComment.setDisslikes(0);
						newComment.setLikes(0);
						newComment.setEdited(false);
						newComment.setDeleted(false);
						newComment.setParenttopic(tpc.getTitle());
						newComment.setCommentID(tpc.getComments().size()+1);
						tpc.getComments().add(newComment);
						subForumService.writeSubForumData();
						return;
					}
				}
			}
		
		}
	}
	
	public Boolean removeComment(String userID, String userPass, String parentForumTitle, String parentTopicTitle, int id) {
		subForumService.loadSubForumData();
		subForums = subForumService.getAllSubForums();
		User user = userService.getUser(userID, userPass);
		if(subForums != null) {
			for(SubForum forum : subForums)
				if(forum.getTitle().equals(parentForumTitle)) {
					for(Topic topic : forum.getTopics()) 
						if(topic.getTitle().equals(parentTopicTitle)) {
							for(Comment c : topic.getComments()) 
								if(c.getCommentID() == id) {
									if(c.getAuthor().equals(user.getUserID()) || user.getRole()==Role.ADMIN || user.getRole()==Role.MODERATOR) {
										c.setDeleted(true);
										subForumService.writeSubForumData();
										return true;
									}	
								}
						}
				}
		}
		
		return false;
		
	}
	
	public Boolean editComment(String userID, String userPass, 
			String parentForumTitle, String parentTopicTitle, int id, String newContent) {
		subForumService.loadSubForumData();
		subForums = subForumService.getAllSubForums();
		User user = userService.getUser(userID, userPass);
		if(subForums != null) {
			for(SubForum forum : subForums)
				if(forum.getTitle().equals(parentForumTitle)) 
					for(Topic topic : forum.getTopics()) 
						if(topic.getTitle().equals(parentTopicTitle)) 
							for(Comment c : topic.getComments()) 
								if(c.getCommentID() == id) 
									if(c.getAuthor().equals(user.getUserID())) {
										c.setContent(newContent);
										if(user.getRole() == Role.DEFAULT) {
											c.setEdited(true);
										}
										subForumService.writeSubForumData();
										return true;
									}	
		}
		
		return false;
		
	}
	
	public Boolean commentVote(String userID, String userPass, 
			String parentForumTitle, String parentTopicTitle, int id, String vote  ) {
		
		subForumService.loadSubForumData();
		subForums = subForumService.getAllSubForums();
		User user = userService.getUser(userID, userPass);
		if(subForums != null) {
			for(SubForum forum : subForums)
				if(forum.getTitle().equals(parentForumTitle)) 
					for(Topic topic : forum.getTopics()) 
						if(topic.getTitle().equals(parentTopicTitle)) 
							for(Comment c : topic.getComments()) 
								if(c.getCommentID() == id) {
									for(String voted : c.getAlreadyVoted()) {
										if(voted.equals(user.getUserID())) {
											return false;
										}
									}
										c.getAlreadyVoted().add(user.getUserID());
										if(vote.equals("+")) {
											c.setLikes(c.getLikes()+1);
										}else if(vote.equals("-")) {
											c.setDisslikes(c.getDisslikes()+1);
										}
										subForumService.writeSubForumData();
										return true;
									
								}
		}
		return false;
	}
		
	public Boolean topicVote(String userID, String userPass, 
			String parentForumTitle, String parentTopicTitle, String vote  ) {
		
		subForumService.loadSubForumData();
		subForums = subForumService.getAllSubForums();
		User user = userService.getUser(userID, userPass);
		if(subForums != null) {
			for(SubForum forum : subForums)
				if(forum.getTitle().equals(parentForumTitle)) 
					for(Topic topic : forum.getTopics()) {
						if(topic.getTitle().equals(parentTopicTitle)) {
							for(String voted : topic.getAlreadyVoted()) 
								if(voted.equals(user.getUserID())) {
									return false;
								}
							}
								topic.getAlreadyVoted().add(user.getUserID());
								if(vote.equals("+")) {
									topic.setLikes(topic.getLikes()+1);
								}else if(vote.equals("-")){
									topic.setDisslikes(topic.getDisslikes()+1);
								}
								subForumService.writeSubForumData();
								return true;
							
					}
		}
		return false;
	}
	
	
		
		
		

	
	
}
