package com.cts.service;

import com.cts.bo.ParentTaskVO;
import com.cts.bo.ProjectVO;
import com.cts.bo.TaskVO;
import com.cts.bo.UserVO;

import java.util.List;

public interface ProjectManagerService {
	
	public List<TaskVO> retrieveTasks();
	public void updateTask(TaskVO task);
	public TaskVO getTaskbyId(String taskId);
	public TaskVO updateTask(Long taskId, TaskVO task);

	public List<ParentTaskVO> retrieveParentTasks();
	public List<ParentTaskVO> retrieveParentTasksForProjectId(String projectId);
	public void updateParentTask(ParentTaskVO parentTask);
	public ParentTaskVO retrieveParentTaskById(String parentTaskId);

	public List<ProjectVO> retrieveProjects();
	public void updateProject(ProjectVO project);
	public ProjectVO getProjectById(String projectId);

	public List<UserVO> retrieveUsers();
	public void updateUser(UserVO user);
	public UserVO getUserById(String userId);
}
