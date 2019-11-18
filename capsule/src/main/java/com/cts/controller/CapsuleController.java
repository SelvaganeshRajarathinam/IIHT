package com.cts.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.cts.bo.ParentTaskVO;
import com.cts.bo.TaskVO;
import com.cts.service.ProjectManagerService;
import org.springframework.beans.factory.annotation.Autowired;
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
		List<TaskVO> tasks = projectManagerService.retriveTasks();
		System.out.println("parent:"+ tasks.get(0).getParentTaskName());
		return tasks;
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
		List<ParentTaskVO> tasks = projectManagerService.retriveParentTasks();
		return tasks;
	}

	@GetMapping("/api/parenttasks/projects/{projectId}")
	public List<ParentTaskVO> getParentTasksForProjectId(@PathVariable(name="projectId") String projectId) {
		List<ParentTaskVO> tasks = projectManagerService.retriveParentTasksForProjectId(projectId);
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
}
