package com.myclass.dto;

import java.sql.Date;
import java.time.LocalDate;

import com.myclass.entity.Task;

public class TaskDto {
	private int 		taskId;
	private String 		taskName;
	private LocalDate 		startDate;
	private LocalDate		endDate;
	private int 		statusId;
	private int 		userId;
	private int 		projectId;
	private String 		userName;
	
	public int getTaskId() {
		return taskId;
	}
	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}
	public String getTaskName() {
		return taskName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
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
	public int getStatusId() {
		return statusId;
	}
	public TaskDto(int taskId, String taskName, LocalDate startDate, LocalDate endDate, int statusId, int userId,
			int projectId, String userName) {
		this.taskId = taskId;
		this.taskName = taskName;
		this.startDate = startDate;
		this.endDate = endDate;
		this.statusId = statusId;
		this.userId = userId;
		this.projectId = projectId;
		this.userName = userName;
	}
	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getProjectId() {
		return projectId;
	}
	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}
	public TaskDto(int taskId, String taskName, LocalDate startDate, LocalDate endDate, int statusId, int userId,
			int projectId) {
		this.taskId = taskId;
		this.taskName = taskName;
		this.startDate = startDate;
		this.endDate = endDate;
		this.statusId = statusId;
		this.userId = userId;
		this.projectId = projectId;
	}
	
	public TaskDto(Task task) {
		this.taskId=task.getTaskId();
		this.taskName=task.getTaskName();
		this.startDate=task.getStartDate();
		this.endDate=task.getEndDate();
		this.statusId=task.getStatusId();
		this.projectId=task.getProjectId();
		this.userId=task.getUserId();
	}
}
