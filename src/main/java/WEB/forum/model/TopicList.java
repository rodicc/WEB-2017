package WEB.forum.model;

import java.io.Serializable;
import java.util.ArrayList;

public class TopicList implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<String> topics;
	
	public TopicList() {
		topics = new ArrayList<>();
	}
	
	public TopicList(ArrayList<String> topics) {
		this.topics = topics;
	}

	public ArrayList<String> getTopics() {
		return topics;
	}

	public void setTopics(ArrayList<String> topics) {
		this.topics = topics;
	}
	
	
	
	
}
