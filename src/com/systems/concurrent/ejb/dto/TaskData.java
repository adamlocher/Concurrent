package com.systems.concurrent.ejb.dto;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "PROJECT_TASKS")
public class TaskData extends AbstractData {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8979139405142741523L;

	public interface Status {
		public static final String OPEN = "Open";
		public static final String CLOSED = "Closed";
		public static final String REOPEN = "Reopen";
		public static final String VERIFICATION = "Verification";
		public static final String IN_PROGRESS = "In progress";
	}
	public interface Component {
		public static final String EJB = "EJB";
		public static final String WEB = "WEB";
		public static final String DATABASE = "DATABASE";
		public static final String ANALYSIS = "ANALYSIS";
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="TSK_ID")
	Long id;
	
	@Column(name="TSK_NAME")
	private String name;
	
	@Column(name="TSK_DESCRIPTION")
	private String description;
	
	@Column(name="TSK_COMPONENT")
	private String component;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "TSK_PROJECT_ID", referencedColumnName = "PRJ_ID", insertable = false, updatable = false )
	ProjectData project;
	
	@Column(name="TSK_PROJECT_ID")
	private Long projectId;
	
	@Column(name="TSK_ELAPSED_TIME")
	private Long elapsedTime;
	
	@Column(name="TSK_ESTIMATED_TIME")
	private String estimatedTime;
	
	@Column(name="TSK_PRIORITY")
	private String priority;
	
	@Column(name="TSK_STATUS")
	private String status;
	
	@Column(name="TSK_TYPE")
	private String type;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "TSK_ASSIGNED_USER_ID", referencedColumnName = "USR_ID", insertable = false, updatable = false )
	UserData assignedUser;
	
	@Column(name="TSK_ASSIGNED_USER_ID")
	private Long assignedUserId;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval=true)
	@JoinColumn(name = "COM_TASK_ID", referencedColumnName = "TSK_ID", insertable = true, updatable = true )
	List<CommentData> comments=new ArrayList<>();
	
	@Column(name="TSK_START_WORK")
	@Temporal(TemporalType.TIMESTAMP)
	Date startWorkDate;
	
	public Date getStartWorkDate() {
		return startWorkDate;
	}
	public void setStartWorkDate(Date startWorkDate) {
		this.startWorkDate = startWorkDate;
	}

	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getComponent() {
		return component;
	}

	public void setComponent(String component) {
		this.component = component;
	}

	public ProjectData getProject() {
		return project;
	}

	public void setProject(ProjectData project) {
		this.project = project;
	}

	public String getFormattedElapsedTime() {
		long diffMinutes=0l;
		long diffHours=0l; 
		long diff;
		if(startWorkDate==null  && elapsedTime!=null){
			 diffMinutes = elapsedTime % 60;
			 diffHours = elapsedTime / 60;
		}
		else if(startWorkDate!=null  && elapsedTime==null){
			diff= new Date().getTime()-startWorkDate.getTime();
			diffMinutes = diff / (60 * 1000) % 60;
			diffHours = diff / (60 * 60 * 1000);
		}
		else if(startWorkDate==null  && elapsedTime==null){
			return "00h 00m";
		}
		else{
			diff= new Date().getTime()-startWorkDate.getTime();
		    diffMinutes =diff / (60 * 1000);
			diffMinutes+=elapsedTime;
			diffHours=diffMinutes/60;
			diffMinutes=diffMinutes%60;
		}
			
		return diffHours+"h "+diffMinutes+"m";
			
	}
	public Long getElapsedTime() {
		return elapsedTime;
	}
	public void setElapsedTime(Long elapsedTime) {
		this.elapsedTime = elapsedTime;
	}
	/*
	public String getFormattedElapsedTime() {
		String temp="  ";
		if(elapsedTime!=null && elapsedTime.length()>0){
			String tab[]=elapsedTime.split(":");
			temp+=tab[0]+"h "+tab[1]+" m";
		}else{
			temp+="00h 00m";
		}
		return temp;
		return "";
		
	}*/
	public String getEstimatedTime() {
		return estimatedTime;
	}
	
	/*public String getFormattedEstimatedTime() {
		String temp="";
		if(estimatedTime!=null && estimatedTime.length()>0){
			String tab[]=estimatedTime.split(":");
			temp=tab[0]+"h "+tab[1]+" m";
		}
		return temp;
	}*/
	
	public void setEstimatedTime(String estimatedTime) {
		this.estimatedTime = estimatedTime;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}
	
	public UserData getAssignedUser() {
		return assignedUser;
	}

	public void setAssignedUser(UserData assignedUser) {
		this.assignedUser = assignedUser;
	}

	public Long getAssignedUserId() {
		return assignedUserId;
	}

	public void setAssignedUserId(Long assignedUserId) {
		this.assignedUserId = assignedUserId;
	}

	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public Long getProjectId() {
		return projectId;
	}
	
	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	
	public String getDescriptionEscaped() {
		return RemoveTag(description);
	}

	public String getNameEscaped() {
		return RemoveTag(name);
	}
	String RemoveTag(String html){
		   html = html.replaceAll("\\<.*?>","");
		   html = html.replaceAll("&nbsp;","");
		   html = html.replaceAll("&amp;","");
		   
		   return html;
		}
	
	public List<CommentData> getComments() {
		return comments;
	}
	
	public List<CommentData> getUserComments() {
		List<CommentData> usrComm=new ArrayList<>();
		for(CommentData comm:comments){
			if(comm.getSystem())
				continue;
			usrComm.add(comm);
		}
		return usrComm;
	}
	
	public void setComments(List<CommentData> comments) {
		this.comments = comments;
	}
}
