package WEB.forum.model;

import WEB.forum.model.Report.ReportStatus;

public class ReportWrapper {
	
	private Message message;
	private ReportStatus status;
	private Report report;
	
	public ReportWrapper() {
		
	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	public ReportStatus getStatus() {
		return status;
	}

	public void setStatus(ReportStatus status) {
		this.status = status;
	}

	public Report getReport() {
		return report;
	}

	public void setReport(Report report) {
		this.report = report;
	}
	
	
}
