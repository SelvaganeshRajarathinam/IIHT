package com.cts.service;

import com.cts.bo.ParentTaskVO;
import com.cts.bo.TaskVO;

import java.util.List;

public interface ProjectManagerService {
	
	public List<TaskVO> retriveTasks();
	public void updateTask(TaskVO task);
	
	public List<ParentTaskVO> retriveParentTasks();
	public List<ParentTaskVO> retriveParentTasksForProjectId(String projectId);
	public void updateParentTask(ParentTaskVO parentTask);

}
