package com.myclass.dto;

import java.sql.Date;
import java.time.LocalDate;

import com.myclass.entity.Project;

public class ProjectDto {
	private int 		projectId;
	private String 		projectName;
	private LocalDate 		startDate;
	private LocalDate		endDate;
	private int			leaderId;
	
	public int getProjectId() {
		return projectId;
	}
	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public LocalDate getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	public LocalDate getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
	
	public int getLeaderId() {
		return leaderId;
	}
	public void setLeaderId(int leaderId) {
		this.leaderId = leaderId;
	}
	public ProjectDto(int projectId, String projectName, LocalDate startDate, LocalDate endDate, int leaderId) {
		this.projectId = projectId;
		this.projectName = projectName;
		this.startDate = startDate;
		this.endDate = endDate;
		this.leaderId = leaderId;
	}
	
	public ProjectDto(Project project) {
		this.projectId 		= project.getProjectId();
		this.projectName	= project.getProjectName();
		this.startDate		= project.getStartDate();
		this.endDate		= project.getEndDate();
		this.leaderId		= project.getLeaderId();
	}
}
