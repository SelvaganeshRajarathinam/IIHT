package com.cts.nr;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.cts.bo.ParentTaskVO;
import com.cts.bo.ProjectVO;
import com.cts.bo.TaskVO;
import com.cts.bo.UserVO;
import com.cts.controller.CapsuleController;
import com.cts.entity.ParentTask;
import com.cts.entity.Project;
import com.cts.entity.Task;
import com.cts.entity.User;
import com.cts.service.ProjectManagerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;

import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.net.ssl.SSLEngineResult;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class NrApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@InjectMocks
	CapsuleController capsuleController;

	@MockBean
	private ProjectManagerService projectManagerService;
	ParentTaskVO parentTaskVO;
	ProjectVO projectVO;
	TaskVO taskVO;
	UserVO userVO;
	List<ParentTaskVO> parentTaskList;
	List<ProjectVO> prjList;
	List<TaskVO> taskList;
	List<UserVO> userList;
	ParentTask parentTask;
	Project prj;
	Task task;
	User user;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(capsuleController).build();
		parentTaskVO = new ParentTaskVO(new Long(123), "TestParentTask");
        parentTask = new ParentTask(parentTaskVO.getParentTaskId(), parentTaskVO.getParentTaskName());
		parentTaskList = new ArrayList<ParentTaskVO>();
		parentTaskList.add(parentTaskVO);
		projectVO = new ProjectVO(new Long(12354), "TestProject", "12-11-2019", "15-12-2019", "15", "1");
		prj = new Project(projectVO.getProjectId(), projectVO.getProjectName(), projectVO.getStartDate(), projectVO.getEndDate(), projectVO.getPriority(), projectVO.getStatus());
		prjList = new ArrayList<ProjectVO>();
		prjList.add(projectVO);
		taskVO = new TaskVO(new Long(12345), "TestTask", "15", "TestParentTask", "01-11-2019", "31-12-2019", new Long(123), "John");
		task = new Task(taskVO.getTaskId(), taskVO.getTaskName(), taskVO.getPriority(), taskVO.getParentTaskName(), taskVO.getStartDate(), taskVO.getEndDate(), taskVO.getStatus(), taskVO.getUser());
		taskList = new ArrayList<TaskVO>();
		taskList.add(taskVO);
		userVO = new UserVO("213070", "Joji", "John", "123", "12354", "12345");
		user = new User(userVO.getEmployeeId(), userVO.getFirstName(), userVO.getLastName(), userVO.getUserId(), userVO.getProjectId(), userVO.getTaskId());
		userList = new ArrayList<UserVO>();
		userList.add(userVO);
	}



	@Test
	public void getAllParentTasksTest() throws Exception {
		when(projectManagerService.retrieveParentTasks()).thenReturn(parentTaskList);
		mockMvc.perform(MockMvcRequestBuilders.get("/api/parenttasks")
				.contentType(MediaType.APPLICATION_JSON).content(asJsonString(parentTask)))
				.andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
	}

    @Test
    public void getParentTaskTest() throws Exception {
        when(projectManagerService.retrieveParentTaskById("12354")).thenReturn(parentTaskVO);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/parenttask/12354")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(parentTask)))
                .andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
    }

	@Test
	public void getAllProjectsTest() throws Exception {
		when(projectManagerService.retrieveProjects()).thenReturn(prjList);
		mockMvc.perform(MockMvcRequestBuilders.get("/api/projects")
				.contentType(MediaType.APPLICATION_JSON).content(asJsonString(prj)))
				.andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
	}

	@Test
	public void getPrjTest() throws Exception {
		when(projectManagerService.getProjectById("12354")).thenReturn(projectVO);
		mockMvc.perform(MockMvcRequestBuilders.get("/api/project/12354")
				.contentType(MediaType.APPLICATION_JSON).content(asJsonString(prj)))
				.andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
	}

	@Test
	public void getAllTasksTest() throws Exception {
		when(projectManagerService.retrieveTasks()).thenReturn(taskList);
		mockMvc.perform(MockMvcRequestBuilders.get("/api/tasks")
				.contentType(MediaType.APPLICATION_JSON).content(asJsonString(task)))
				.andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
	}

	@Test
	public void getTaskTest() throws Exception {
		when(projectManagerService.getTaskbyId("12354")).thenReturn(taskVO);
		mockMvc.perform(MockMvcRequestBuilders.get("/api/task/12345")
				.contentType(MediaType.APPLICATION_JSON).content(asJsonString(task)))
				.andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
	}

	@Test
	public void getAllUsersTest() throws Exception {
		when(projectManagerService.retrieveUsers()).thenReturn(userList);
		mockMvc.perform(MockMvcRequestBuilders.get("/api/users")
				.contentType(MediaType.APPLICATION_JSON).content(asJsonString(user)))
				.andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
	}

	@Test
	public void getUserTest() throws Exception {
		when(projectManagerService.getUserById("12354")).thenReturn(userVO);
		mockMvc.perform(MockMvcRequestBuilders.get("/api/user/12345")
				.contentType(MediaType.APPLICATION_JSON).content(asJsonString(task)))
				.andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
	}

	public String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Test
	public void contextLoads() {
	}

}
