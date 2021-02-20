package com.myclass.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.myclass.dto.TaskDto;
import com.myclass.entity.Task;
import com.myclass.repository.TaskRepository;
import com.myclass.repository.UserRepository;

public class TaskService {
	private TaskRepository taskRepository;
	private UserRepository userRepository;
	
	public TaskService(){
		taskRepository = new TaskRepository();
		userRepository = new UserRepository();
	}
	
	public TaskDto getTaskById(int id) {
		return new TaskDto(taskRepository.getTaskById(id));
	}
	
	public List<TaskDto> getTaskByUserId(int id) {
		ArrayList<TaskDto> result = null;
		ArrayList<Task>	taskList;
		
		taskList = (ArrayList<Task>) taskRepository.getTasksByUserId(id);
		if(taskList.isEmpty())
			return null;
		
		result = new ArrayList<TaskDto>();
		for(Task task : taskList)
			result.add(new TaskDto(task));
		
		return result;
	}
	
	public Boolean checkOwnTask(int userId,int taskId) {
		ArrayList<Task>	taskList;
		
		taskList = (ArrayList<Task>) taskRepository.getTasksByUserId(userId);
		if(taskList.isEmpty())
			return false;
		
		for(Task task:taskList) 
			if(task.getTaskId()==taskId)
				return true;
		
		return false;
	}
	
	public List<TaskDto> getTaskByProjectId(int id) {
		ArrayList<TaskDto> result = null;
		ArrayList<Task>	taskList;
		
		taskList = (ArrayList<Task>) taskRepository.getTasksByProjectId(id);
		if(taskList.isEmpty())
			return null;
		
		HashMap<Integer,String> userList=userRepository.getAllUsersHashMap();
		result = new ArrayList<TaskDto>();
		for(Task task : taskList)
		{
			result.add(new TaskDto(task));
			int idUser=result.get(result.size()-1).getUserId();
			result.get(result.size()-1).setUserName(userList.get(idUser));
		}
		
		return result;
	}
	
	public Boolean checkTaskInProject(int taskId,int projectId) {
		ArrayList<Task> taskList = (ArrayList<Task>) taskRepository.getTasksByProjectId(projectId);
		if(taskList.isEmpty())
			return false;
		
		for(Task task : taskList)
			if(task.getTaskId()==taskId)
				return true;
		return false;
		
	}
	
	public List<TaskDto> getTaskByStatusId(int id) {
		ArrayList<TaskDto> result = null;
		ArrayList<Task>	taskList;
		
		taskList = (ArrayList<Task>) taskRepository.getTasksByStatusId(id);
		if(taskList.isEmpty())
			return null;
		
		result = new ArrayList<TaskDto>();
		for(Task task : taskList)
			result.add(new TaskDto(task));
		
		return result;
	}
	
	public List<TaskDto> getAllTasks(){
		ArrayList<TaskDto> result = null;
		ArrayList<Task>	taskList;
		
		taskList = (ArrayList<Task>) taskRepository.getAllTasks();
		if(taskList.isEmpty())
			return null;
		
		result = new ArrayList<TaskDto>();
		for(Task task : taskList)
			result.add(new TaskDto(task));
		
		return result;
	}
	
	public boolean addTask(TaskDto target)
	{
		if(target==null)
			return false;
		
		if(taskRepository.getTaskById(target.getTaskId())!=null)
			return false;
		
		Task add = new Task();
		add.setTaskId(target.getTaskId());
		add.setTaskName(target.getTaskName());
		add.setStartDate(target.getStartDate());
		add.setEndDate(target.getEndDate());
		add.setStatusId(target.getStatusId());
		add.setProjectId(target.getProjectId());
		add.setUserId(target.getUserId());
		
		return taskRepository.addTask(add);
		
	}
	
	public boolean deleteTask(int id)
	{
		return taskRepository.removeTask(id);
	}
	
	public boolean editTask(TaskDto target)
	{
		if(target==null)
			return false;
		
		Task temp = taskRepository.getTaskById(target.getTaskId());
		if(temp==null)
			return false;
		
		temp.setTaskId(target.getTaskId());
		temp.setTaskName(target.getTaskName());
		temp.setStartDate(target.getStartDate());
		temp.setEndDate(target.getEndDate());
		temp.setStatusId(target.getStatusId());
		temp.setProjectId(target.getProjectId());
		temp.setUserId(target.getUserId());
		
		return taskRepository.editTask(target.getTaskId(), temp);
	}
}
