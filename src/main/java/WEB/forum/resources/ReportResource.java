package WEB.forum.resources;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

import WEB.forum.model.Message;
import WEB.forum.model.Report;
import WEB.forum.model.Report.ReportStatus;
import WEB.forum.model.ReportWrapper;
import WEB.forum.service.ReportService;
	



@Path("/report")
public class ReportResource {
	
	private ReportService reportService = new ReportService();	
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void sendReport(@HeaderParam("userID") String userID, @HeaderParam("userPass") String userPass,
			Report report) {
		reportService.sendReport(userID, userPass, report);
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public void respondToReport(@HeaderParam("userID") String userID, @HeaderParam("userPass") String userPass, ReportWrapper reportWrapper ) {
		reportService.respondToReport(userID, userPass, reportWrapper);
	}
	
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	public ArrayList<Report> getAllreportsFor(@HeaderParam("userID") String userID, @HeaderParam("userPass") String userPass){
		return reportService.getAllReportsFor(userID, userPass);
	}
	
	@GET
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Report getReport(@HeaderParam("userID") String userID, @HeaderParam("userPass") String userPass, @PathParam("id") int id){
		return reportService.getReport(userID, userPass, id);
	}
	
}
