package com.myclass.service;

import java.util.ArrayList;
import java.util.List;

import com.myclass.dto.ProjectDto;
import com.myclass.entity.Project;
import com.myclass.repository.ProjectRepository;
import com.myclass.repository.TaskRepository;
import com.myclass.repository.UserRepository;

public class ProjectService {
	private ProjectRepository projectRepository;
	
	public ProjectService() {
		projectRepository = new ProjectRepository();
	}
	
	public ProjectDto getProjectById(int id) {
		Project 	target = projectRepository.getProjectById(id);
		ProjectDto	result = new ProjectDto(target);
		return result;
	}
	
	public List<ProjectDto> getProjectByLeaderId(int id)
	{
		ArrayList<ProjectDto> 	result;
		ArrayList<Project>		projectList = (ArrayList<Project>) projectRepository.getProjectByLeaderId(id);
		if(projectList.isEmpty())
			return null;
		
		result = new ArrayList<ProjectDto>();
		for(Project project: projectList)
			result.add(new ProjectDto(project));
		
		return result;
	}
	
	public List<ProjectDto> getProjectByUserId(int id)
	{
		ArrayList<ProjectDto> 	result;
		ArrayList<Project>		projectList = (ArrayList<Project>) projectRepository.getProjectByUserId(id);
		if(projectList.isEmpty())
			return null;
		
		result = new ArrayList<ProjectDto>();
		for(Project project: projectList)
			result.add(new ProjectDto(project));
		
		return result;
	}
	
	public List<ProjectDto> getProjectByUserLeaderId(int id)
	{
		ArrayList<ProjectDto> 	result;
		ArrayList<Project>		projectList = (ArrayList<Project>) projectRepository.getProjectByUserLeaderId(id);
		if(projectList.isEmpty())
			return null;
		
		result = new ArrayList<ProjectDto>();
		for(Project project: projectList)
			result.add(new ProjectDto(project));
		
		return result;
	}
	
	
	public List<ProjectDto> getAllProject()
	{
		ArrayList<ProjectDto> 	result;
		ArrayList<Project>		projectList = (ArrayList<Project>) projectRepository.getAllProjects();
		if(projectList.isEmpty())
			return null;
		
		result = new ArrayList<ProjectDto>();
		for(Project project: projectList)
			result.add(new ProjectDto(project));
		
		return result;
	}
	
	public boolean deleteProject(int id)
	{
		TaskRepository taskRepository = new TaskRepository();
		taskRepository.removeTaskByProjectId(id);
		return projectRepository.removeProject(id);
	}
	
	public boolean editProject(ProjectDto target)
	{
		if(target==null)
			return false;
		
		Project temp = projectRepository.getProjectById(target.getProjectId());
		if(temp==null)
			return false;
		
		temp.setProjectName(target.getProjectName());
		temp.setStartDate(target.getStartDate());
		temp.setEndDate(target.getEndDate());
		temp.setLeaderId(target.getLeaderId());
		
		return projectRepository.editProject(target.getProjectId(), temp);
	}
	
	public boolean addProject(ProjectDto target) 
	{
		if(target==null)
			return false;
		
		Project add = new Project();
		add.setProjectId(target.getProjectId());
		add.setProjectName(target.getProjectName());
		add.setStartDate(target.getStartDate());
		add.setEndDate(target.getEndDate());
		add.setLeaderId(target.getLeaderId());
		return projectRepository.addProject(add);
	}
}
