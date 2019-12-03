package com.cts.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.cts.bo.UserVO;
import com.cts.bo.ProjectVO;
import com.cts.bo.ParentTaskVO;
import com.cts.bo.TaskVO;
import com.cts.service.ProjectManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CapsuleController {
	
	private ProjectManagerService projectManagerService;
	private static final Logger LOGGER = LogManager.getLogger(CapsuleController.class);

	@Autowired
	public CapsuleController(ProjectManagerService projectManagerService) {
		this.projectManagerService = projectManagerService;
	}

	@RequestMapping(value="/home", method = RequestMethod.GET)
	@ResponseBody
	public String home() {
		return "Welcome to the Final project - IIHT - Capsule Module";
	}
	
	@GetMapping("/api/tasks")
	public List<TaskVO> getTasks() {
		List<TaskVO> tasks = projectManagerService.retrieveTasks();
		return tasks;
	}

	@CrossOrigin
	@RequestMapping(value = "/api/task/{taskId}", method = RequestMethod.GET)
	public ResponseEntity<TaskVO> getTaskById(@PathVariable String taskId) throws Exception {
		TaskVO tskDetails = projectManagerService.getTaskbyId(taskId);
		return new ResponseEntity<TaskVO>(tskDetails, HttpStatus.OK);
	}

	@PostMapping(path = "/api/tasks", consumes = "application/json", produces = "application/json")
	public boolean updateTask(@RequestBody TaskVO task) {
		try {
			System.out.println("Received:" + task.getParentTaskName());
			projectManagerService.updateTask(task); 
		}catch(Exception e)
		{
			return false;
		}
		return true;
	}

	@GetMapping("/api/parenttasks")
	public List<ParentTaskVO> getAllParentTasks() {
		List<ParentTaskVO> tasks = projectManagerService.retrieveParentTasks();
		return tasks;
	}

	@GetMapping("/api/parenttask/{parentTaskId}")
	public ParentTaskVO getAllParentTasks(@PathVariable String parentTaskId) {
		ParentTaskVO parentTask = projectManagerService.retrieveParentTaskById(parentTaskId);
		return parentTask;
	}

	@GetMapping("/api/parenttasks/projects/{projectId}")
	public List<ParentTaskVO> getParentTasksForProjectId(@PathVariable(name="projectId") String projectId) {
		List<ParentTaskVO> tasks = projectManagerService.retrieveParentTasksForProjectId(projectId);
		return tasks;
	}

	@PostMapping(path = "/api/parenttasks", consumes = "application/json", produces = "application/json")
	public boolean updateParentTask(@RequestBody ParentTaskVO parentTask) {
		try {
			projectManagerService.updateParentTask(parentTask);
		}catch(Exception e)
		{
			return false;
		}
		return true;
	}

	@GetMapping("/api/projects")
	public List<ProjectVO> getProjects() {
		List<ProjectVO> projects = projectManagerService.retrieveProjects();
		return projects;
	}

	@PostMapping(path = "/api/projects", consumes = "application/json", produces = "application/json")
	public boolean updateProject(@RequestBody ProjectVO project) {
		try {
			projectManagerService.updateProject(project);
		}catch(Exception e)
		{
			return false;
		}
		return true;
	}

	@CrossOrigin
	@RequestMapping(value = "/api/project/{projectId}", method = RequestMethod.GET)
	public ResponseEntity<ProjectVO> getPrjById(@PathVariable String projectId) throws Exception {
		ProjectVO prjDetails = projectManagerService.getProjectById(projectId);
		return new ResponseEntity<ProjectVO>(prjDetails, HttpStatus.OK);
	}

	@GetMapping("/api/users")
	public List<UserVO> getUsers() {
		List<UserVO> users = projectManagerService.retrieveUsers();
		return users;
	}

	@GetMapping("/api/user/{userId}")
	public UserVO getUserById(@PathVariable String userId) {
		UserVO users = projectManagerService.getUserById(userId);
		return users;
	}

	@PostMapping(path = "/api/adduser", consumes = "application/json", produces = "application/json")
	public boolean updateUsers(@RequestBody UserVO user) {
		try {
			System.out.println("Post call - Users: ");
			projectManagerService.updateUser(user);
		}catch(Exception e)
		{
			return false;
		}
		return true;
	}
}
