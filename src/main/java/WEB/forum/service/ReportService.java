package WEB.forum.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;

import WEB.forum.model.Comment;
import WEB.forum.model.Message;
import WEB.forum.model.Report;
import WEB.forum.model.SubForum;
import WEB.forum.model.Topic;
import WEB.forum.model.User;
import WEB.forum.model.Report.ReportStatus;
import WEB.forum.model.ReportWrapper;
import WEB.forum.model.User.Role;

public class ReportService {

	private String filePath = "C://Users/Dušan Rodić/Documents/ForumDB/reports.dat";
	private ArrayList<Report> reports;
	private UserService userService = new UserService();
	private SubForumService subForumService = new SubForumService();
	private ChatService chatService = new ChatService();
	private TopicService topicService = new TopicService();
	
	public void sendReport (String userID, String userPass, Report report) {
		if(report.getReportContent() == null) {
			report.setReportContent("");
		}
		if(report.getSubject() == null) {
			report.setSubject("");
		}
		
		
		User user = userService.getUser(userID, userPass);		
		if(user != null) {
			loadReportData();
			if(reports == null) {
				reports = new ArrayList<Report>();
			}
			
			if(report.getSubmittedByUser() == null) {
				report.setSubmittedByUser(user.getUserID());
			}
			report.setId(reports.size());
			report.setDate(LocalDate.now());
			report.setReportStatus(ReportStatus.ACTIVE);
			reports.add(report);
			writeReportData();
		}
		
		
	}
	
	public void respondToReport(String userID, String userPass, ReportWrapper reportWrapper) {
		loadReportData();
		if(reports != null) {
			User user = userService.getUser(userID, userPass);
			if(user != null) {
				for(Report r : reports) {
					if( reportWrapper.getReport().getId() == r.getId()) {
						r.setReportStatus(reportWrapper.getStatus());
						
						String newContent = 
								"Response regarding report no."+reportWrapper.getReport().getId()+"\n"
								+"******************************************************************************************************************\n" 
								+"'"+reportWrapper.getReport().getReportContent()+"'"+"\n"
								+"******************************************************************************************************************\n" +
								"Report status changed to:"+reportWrapper.getStatus().toString()+"\n"
								+"******************************************************************************************************************\n" 
								+""+reportWrapper.getMessage().getContent()
								+"******************************************************************************************************************\n" 
								;
						
						reportWrapper.getMessage().setContent(newContent);	
						reportWrapper.getMessage().setSender(userID);
						chatService.sendMessage(userID, userPass, r.getSubmittedByUser(), reportWrapper.getMessage());
						if(reportWrapper.getStatus().equals(ReportStatus.WARNED) || reportWrapper.getStatus().equals(ReportStatus.REMOVED)) {
							String author = new String();
							String[] subject = r.getSubject().split(":");
							if(subject.length < 2) {
								author = subForumService.getSubForum(subject[0]).getChiefModerator();
								if(reportWrapper.getStatus().equals(ReportStatus.REMOVED)) {
									subForumService.removeSubForum(subject[0], userID, userPass);
								}
							}
							else {
								for(Topic t : subForumService.getSubForum(subject[0]).getTopics()) {
									if(t.getTitle().equals(subject[1])) {
										if(subject.length == 2) {
											author = t.getAuthor();
											if(reportWrapper.getStatus().equals(ReportStatus.REMOVED)) {
												topicService.removeTopic(userID, userPass, subject[0], subject[1]);
											}
										}
										else if(subject.length == 3) {
											for(Comment c : t.getComments()) {
												if (Integer.parseInt(subject[2]) == c.getCommentID()) {
													author = c.getAuthor();
													if(reportWrapper.getStatus().equals(ReportStatus.REMOVED)) {
														topicService.removeComment(userID, userPass, subject[0], subject[1],Integer.parseInt(subject[2]));	
													}
													break;
												}
											}
										}
										break;
									}
								}
							}
							chatService.sendMessage(userID, userPass, author, reportWrapper.getMessage());	
						}
						
							writeReportData();
					}
				}
			}
		}
	}
	
	public ArrayList<Report> getAllReportsFor(String userID, String userPass){
		loadReportData();
		if(reports != null) {
			User user = userService.getUser(userID,userPass);
			if(user != null) {
				if(user.getRole() == Role.ADMIN) {
					return reports;
				}
				else if (user.getRole() == Role.MODERATOR) {
					ArrayList<Report> result = new ArrayList<Report>();
					for(Report r : reports) {
						String[] subject = r.getSubject().split(":");
						if(subject.length >= 2) {
							if(subForumService.getSubForum(subject[0]).getAllModerators().contains(user.getUserID())) {
								result.add(r);
							}
						}
					}
					return result;
				}
			}
		}
		return null;
	}
	
	public Report getReport(String userID, String userPass, int id) {
		loadReportData();
		if(reports != null) {
			User user = userService.getUser(userID, userPass);
			if(user != null) {
				for(Report r : reports) {
					if(r.getId() == id) {
						return r;
					}
				}
			}
		}
		
		return null;
	}
	
	public void loadReportData() {
		File f = new File(filePath);
		ObjectInputStream in = null;
		if (!f.exists()) {
			reports = new ArrayList<Report>();
			return;
		}
		try {
			if (f.length() > 0) {
				in = new ObjectInputStream(new FileInputStream(f));
				reports = (ArrayList<Report>) in.readObject();
			}
			else{
				reports = new ArrayList<Report>();
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
	
	public void writeReportData() {
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
			out.writeObject(reports);
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
