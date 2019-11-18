package com.cts.service;

import com.cts.bo.ParentTaskVO;
import com.cts.bo.TaskVO;
import com.cts.entity.ParentTask;
import com.cts.entity.Task;
import com.cts.repository.ParentTaskManagerRepository;
import com.cts.repository.TaskManagerRepository;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectManagerServiceImpl implements ProjectManagerService{

	private TaskManagerRepository taskManagerRepository;
	
	private ParentTaskManagerRepository parentTaskManagerRepository;

	private Mapper dozerMapper;
	
	@Autowired
	public ProjectManagerServiceImpl(
			TaskManagerRepository taskManagerRepository, 
			ParentTaskManagerRepository parentTaskManagerRepository, Mapper dozerMapper) {
		this.taskManagerRepository = taskManagerRepository;
		this.parentTaskManagerRepository = parentTaskManagerRepository;
		this.dozerMapper = dozerMapper;
	}

	
	public List<TaskVO> retriveTasks(){
		List<TaskVO> taskToBeReturned = new ArrayList<TaskVO>();
		List<Task> tasksRetrived = taskManagerRepository.findAll();
		for(Task task: tasksRetrived) {
			taskToBeReturned.add(dozerMapper.map(task, TaskVO.class));
		}
		return taskToBeReturned;
	}
	
	public void updateTask(TaskVO task) {
		taskManagerRepository.save(dozerMapper.map(task, Task.class));
	}
	
	
	public List<ParentTaskVO> retriveParentTasks(){
		List<ParentTaskVO> taskToBeReturned = new ArrayList<ParentTaskVO>();
		List<ParentTask> tasksRetrived = parentTaskManagerRepository.findAll();
		for(ParentTask task: tasksRetrived) {
			taskToBeReturned.add(dozerMapper.map(task, ParentTaskVO.class));
		}
		return taskToBeReturned;
	}
	
	public List<ParentTaskVO> retriveParentTasksForProjectId(String projectId){
		List<ParentTaskVO> taskToBeReturned = new ArrayList<ParentTaskVO>();
		List<ParentTask> tasksRetrived = parentTaskManagerRepository.findAllParentTaskByProjectId(projectId); 
		for(ParentTask task: tasksRetrived) {
			taskToBeReturned.add(dozerMapper.map(task, ParentTaskVO.class));
		}
		return taskToBeReturned;
	}
	
	public void updateParentTask(ParentTaskVO parentTask) {
		parentTaskManagerRepository.save(dozerMapper.map(parentTask, ParentTask.class));
	}
}
