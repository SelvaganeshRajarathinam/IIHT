package com.cts.service;

import com.cts.bo.ParentTaskVO;
import com.cts.bo.ProjectVO;
import com.cts.bo.TaskVO;
import com.cts.bo.UserVO;
import com.cts.entity.ParentTask;
import com.cts.entity.Project;
import com.cts.entity.Task;
import com.cts.entity.User;
import com.cts.repository.ParentTaskManagerRepository;
import com.cts.repository.ProjectManagerRepository;
import com.cts.repository.TaskManagerRepository;
import com.cts.repository.UserManagerRepository;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.net.ssl.SSLEngineResult.Status;
import java.util.*;
import java.util.stream.Collectors;

@Service
public 	class ProjectManagerServiceImpl implements ProjectManagerService{

	private TaskManagerRepository taskManagerRepository;
	
	private ParentTaskManagerRepository parentTaskManagerRepository;

	private ProjectManagerRepository projectManagerRepository;

	private UserManagerRepository userManagerRepository;

	private Mapper dozerMapper;
	
	@Autowired
	public ProjectManagerServiceImpl(
			TaskManagerRepository taskManagerRepository, 
			ParentTaskManagerRepository parentTaskManagerRepository,
			ProjectManagerRepository projectManagerRepository,
			UserManagerRepository userManagerRepository,
			Mapper dozerMapper) {
		this.taskManagerRepository = taskManagerRepository;
		this.parentTaskManagerRepository = parentTaskManagerRepository;
		this.projectManagerRepository = projectManagerRepository;
		this.userManagerRepository = userManagerRepository;
		this.dozerMapper = dozerMapper;
	}

	public List<TaskVO> retrieveTasks(){
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

	public TaskVO getTaskbyId(String taskId) {
		TaskVO taskres = new TaskVO();
		Task task = taskManagerRepository.findByTaskId(taskId);
		taskres.setTaskId(task.getTaskId());
		taskres.setTaskName(task.getTaskName());
		taskres.setEndDate(task.getEndDate());
		taskres.setStartDate(task.getStartDate());
		taskres.setPriority(task.getPriority());
		taskres.setParentTaskName(task.getParentTaskName());
		return taskres;
	}

	public TaskVO updateTask(Long taskId, TaskVO taskBo) {
		Task task = new Task();
		task.setTaskId(taskId);
		task.setTaskName(taskBo.getTaskName());
		task.setEndDate(taskBo.getEndDate());
		task.setStartDate(taskBo.getStartDate());
		task.setPriority(taskBo.getPriority());
		task.setStatus(new Long(1));
		task.setPriority(taskBo.getPriority());
		if(taskBo.getParentTaskName()!=null){
			Task parentTask = taskManagerRepository.findByTaskId(taskBo.getParentTaskName());
			parentTask.setTaskId(taskBo.getTaskId());
			task.setParentTaskName(taskBo.getParentTaskName());
		}
		taskManagerRepository.save(task);
		return taskBo;
	}

	public List<ParentTaskVO> retrieveParentTasks(){
		List<ParentTaskVO> taskToBeReturned = new ArrayList<ParentTaskVO>();
		List<ParentTask> tasksRetrived = parentTaskManagerRepository.findAll();
		for(ParentTask task: tasksRetrived) {
			taskToBeReturned.add(dozerMapper.map(task, ParentTaskVO.class));
		}
		return taskToBeReturned;
	}
	
	public List<ParentTaskVO> retrieveParentTasksForProjectId(String projectId){
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

	public List<ProjectVO> retrieveProjects(){
		List<ProjectVO> projectResponse = new ArrayList<ProjectVO>();
		List<Project> projectsAvailable = projectManagerRepository.findAll();
		for(Project project: projectsAvailable) {
			ProjectVO projectVO = dozerMapper.map(project, ProjectVO.class);
			//projectVO.setNoOfTasks(taskManagerRepository.getTotalTasksForProjectId(projectVO.getProjectId()));
			projectResponse.add(projectVO);
		}
		return projectResponse;
	}

	public void updateProject(ProjectVO project) {
		projectManagerRepository.save(dozerMapper.map(project, Project.class));
	}

    public ProjectVO getProjectById(String projectId) {
        ProjectVO prjDetail = new ProjectVO();
        Project projectDetails = projectManagerRepository.findByProjectId(projectId);
        Set<String> prjNameSet = new HashSet<String>();
        prjNameSet.add(projectDetails.getProjectName());
        prjDetail.setProjectId(projectDetails.getProjectId());
        prjDetail.setProjectName(projectDetails.getProjectName());
        prjDetail.setStartDate(projectDetails.getStartDate());
        prjDetail.setEndDate(projectDetails.getEndDate());
        prjDetail.setPriority(projectDetails.getPriority());
        return prjDetail;
    }

	public List<UserVO> retrieveUsers(){
		List<UserVO> userResponse = new ArrayList<UserVO>();
		List<User> usersRetrived = userManagerRepository.findAll();
		for(User user: usersRetrived) {
			userResponse.add(dozerMapper.map(user, UserVO.class));
		}
		return userResponse;
	}

	public UserVO getUserById(String userId) {
		UserVO usrDetail = new UserVO();
		User userDetails = userManagerRepository.findByUserId(userId);
		usrDetail.setEmployeeId(userDetails.getEmployeeId());
		usrDetail.setFirstName(userDetails.getFirstName());
		usrDetail.setLastName(userDetails.getLastName());
		usrDetail.setUserId(userDetails.getUserId());
		usrDetail.setProjectId(userDetails.getProjectId());
		usrDetail.setTaskId(userDetails.getTaskId());
		return usrDetail;
	}

	public ParentTaskVO retrieveParentTaskById(String parentTaskId) {
		ParentTaskVO parentTsk = new ParentTaskVO();
		ParentTask ptTask = parentTaskManagerRepository.findByParentTaskId(parentTaskId);
		parentTsk.setParentTaskId(ptTask.getParentTaskId());
		parentTsk.setParentTaskName(ptTask.getParentTaskName());
		return parentTsk;
	}

	public void updateUser(UserVO user) {
		user.setProjectId("1");user.setTaskId("10");user.setUserId("2");
		User userStore = dozerMapper.map(user, User.class);
		System.out.println("User: "+ userStore.getEmployeeId());
		System.out.println("User: "+ userStore.getUserId());
		System.out.println("User: "+ userStore.getFirstName());
		System.out.println("User: "+ userStore.getLastName());
		System.out.println("User: "+ userStore.getProjectId());
		System.out.println("User: "+ userStore.getTaskId());
		userManagerRepository.save(userStore);
	}
}
