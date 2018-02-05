package WEB.forum.model;

import java.io.Serializable;
import java.time.LocalDate;

public class Report implements Serializable{

	private int id;
	private String submittedByUser;
	private LocalDate date;
	private String subject;
	public enum SubjectType {FORUM, TOPIC, COMMENT};
	private SubjectType subjectType;
	private String reportContent;
	public enum ReportStatus {ACTIVE, REJECTED, REMOVED, WARNED};
	private ReportStatus reportStatus;
	
	public Report() {
		
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSubmittedByUser() {
		return submittedByUser;
	}
	public void setSubmittedByUser(String submittedByUser) {
		this.submittedByUser = submittedByUser;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public SubjectType getSubjectType() {
		return subjectType;
	}
	public void setSubjectType(SubjectType subjectType) {
		this.subjectType = subjectType;
	}
	public String getReportContent() {
		return reportContent;
	}
	public void setReportContent(String reportContent) {
		this.reportContent = reportContent;
	}
	public ReportStatus getReportStatus() {
		return reportStatus;
	}
	public void setReportStatus(ReportStatus reportStatus) {
		this.reportStatus = reportStatus;
	}
	
}
