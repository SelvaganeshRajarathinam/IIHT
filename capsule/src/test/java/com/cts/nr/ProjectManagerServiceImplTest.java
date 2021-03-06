package com.cts.nr;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import com.cts.service.ProjectManagerService;
import org.dozer.DozerBeanMapper;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import com.cts.service.ProjectManagerServiceImpl;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/*@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc*/
@RunWith(SpringRunner.class)
//@RunWith(MockitoJUnitRunner.class)
public class ProjectManagerServiceImplTest {

    public ProjectManagerRepository projectManagerRepository;
    public TaskManagerRepository taskManagerRepository;
    public ParentTaskManagerRepository parentTaskManagerRepository;
    public UserManagerRepository userManagerRepository;

    private ProjectManagerServiceImpl projectManagerServiceImpl;
    @Spy
    private DozerBeanMapper dozerMapper;
    ObjectMapper mapper = new ObjectMapper();

    @Before
    public void setUp() throws Exception {
        /*final DozerBeanMapper dozerMapper = new DozerBeanMapper();*/
        projectManagerRepository = Mockito.mock(ProjectManagerRepository.class);
        taskManagerRepository = Mockito.mock(TaskManagerRepository.class);
        parentTaskManagerRepository = Mockito.mock(ParentTaskManagerRepository.class);
        userManagerRepository = Mockito.mock(UserManagerRepository.class);
        projectManagerServiceImpl = new ProjectManagerServiceImpl(taskManagerRepository, parentTaskManagerRepository, projectManagerRepository, userManagerRepository, dozerMapper);
    }

    @After
    public void tearDown() throws Exception {}

    @Test
    public void testRetriveTasks() throws JsonParseException, JsonMappingException, IOException {
        TypeReference<List<Task>> mapType = new TypeReference<List<Task>>() {};
        List<Task> allTasks = null;
        ClassLoader classLoader = getClass().getClassLoader();
        //File file = new File(classLoader.getResource("C:\\json\\tasks.json").getFile());
        File file = new File("C:\\json\\tasks.json");
        allTasks = mapper.readValue(file, mapType);
        when(taskManagerRepository.findAll()).thenReturn(allTasks);
        List<TaskVO> taskDetails = projectManagerServiceImpl.retrieveTasks();
        Assert.assertNotNull(taskDetails);
        verify(taskManagerRepository,times(1)).findAll();
        verifyNoMoreInteractions(taskManagerRepository);
    }

    @Test
    public void testUpdateTask() throws JsonParseException, JsonMappingException, IOException {
        Task task = null;
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File("C:\\json\\task.json");
        task = mapper.readValue(file, Task.class);
        TaskVO taskVO = null;
        File fileTaskVO = new File("C:\\json\\task.json");
        taskVO = mapper.readValue(fileTaskVO, TaskVO.class);
        when(taskManagerRepository.save(task)).thenReturn(task);
        projectManagerServiceImpl.updateTask(taskVO);
    }

    @Test
    public void testRetriveParentTasks() throws JsonParseException, JsonMappingException, IOException {
        TypeReference<List<ParentTask>> mapType = new TypeReference<List<ParentTask>>() {};
        List<ParentTask> allParentTasks = null;
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File("C:\\json\\parenttasks.json");
        allParentTasks = mapper.readValue(file, mapType);
        when(parentTaskManagerRepository.findAll()).thenReturn(allParentTasks);
        List<ParentTaskVO> allParentTasksDetails = projectManagerServiceImpl.retrieveParentTasks();
        Assert.assertNotNull(allParentTasksDetails);
        verify(parentTaskManagerRepository,times(1)).findAll();
        verifyNoMoreInteractions(parentTaskManagerRepository);
    }

    @Test
    public void testRetriveParentTasksForProjectId() throws JsonParseException, JsonMappingException, IOException {
        TypeReference<List<ParentTask>> mapType = new TypeReference<List<ParentTask>>() {};
        List<ParentTask> allParentTasks = null;
        String projectId = "3";
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File("C:\\json\\parenttasks.json");
        allParentTasks = mapper.readValue(file, mapType);
        when(parentTaskManagerRepository.findAllParentTaskByProjectId(projectId)).thenReturn(allParentTasks);
        List<ParentTaskVO> allParentTasksDetails = projectManagerServiceImpl.retrieveParentTasksForProjectId(projectId);
        Assert.assertNotNull(allParentTasksDetails);
        verify(parentTaskManagerRepository,times(1)).findAllParentTaskByProjectId(projectId);
        verifyNoMoreInteractions(parentTaskManagerRepository);
    }

    @Test
    public void testUpdateParentTask() throws JsonParseException, JsonMappingException, IOException {
        ParentTask parent = null;
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File("C:\\json\\parenttask.json");
        parent = mapper.readValue(file, ParentTask.class);
        ParentTaskVO parentTaskVO = null;
        File fileTaskVO = new File("C:\\json\\parenttask.json");
        parentTaskVO = mapper.readValue(fileTaskVO, ParentTaskVO.class);
        when(parentTaskManagerRepository.save(parent)).thenReturn(parent);
        projectManagerServiceImpl.updateParentTask(parentTaskVO);
    }

    @Test
    public void testRetriveProjects() throws JsonParseException, JsonMappingException, IOException {
        TypeReference<List<Project>> mapType = new TypeReference<List<Project>>() {};
        List<Project> allProjects = null;
        Long projectId = (long) 3;
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File("C:\\json\\projects.json");
        allProjects = mapper.readValue(file, mapType);
        when(projectManagerRepository.findAll()).thenReturn(allProjects);
        when(taskManagerRepository.getTotalTasksForProjectId(projectId)).thenReturn(projectId);
        List<ProjectVO> allProjectDetails = projectManagerServiceImpl.retrieveProjects();
        Assert.assertNotNull(allProjectDetails);
        verify(projectManagerRepository,times(1)).findAll();
        verifyNoMoreInteractions(projectManagerRepository);
    }

    @Test
    public void testUpdateProject() throws JsonParseException, JsonMappingException, IOException {
        Project project = null;
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File("C:\\json\\project.json");
        project = mapper.readValue(file, Project.class);
        ProjectVO projectVO = null;
        File fileProjectVO = new File("C:\\json\\project.json");
        projectVO = mapper.readValue(fileProjectVO, ProjectVO.class);
        when(projectManagerRepository.save(project)).thenReturn(project);
        projectManagerServiceImpl.updateProject(projectVO);
    }

    @Test
    public void testRetriveUsers() throws JsonParseException, JsonMappingException, IOException {
        TypeReference<List<User>> mapType = new TypeReference<List<User>>() {};
        List<User> allUsers = null;
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File("C:\\json\\users.json");
        allUsers = mapper.readValue(file, mapType);
        when(userManagerRepository.findAll()).thenReturn(allUsers);
        List<UserVO> allUsersDetails = projectManagerServiceImpl.retrieveUsers();
        Assert.assertNotNull(allUsersDetails);
        verify(userManagerRepository,times(1)).findAll();
        verifyNoMoreInteractions(userManagerRepository);
    }

    @Test
    public void testUpdateUser() throws JsonParseException, JsonMappingException, IOException {
        User user = null;
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File("C:\\json\\user.json");
        user = mapper.readValue(file, User.class);
        UserVO userVO = null;
        File fileUserVO = new File("C:\\json\\user.json");
        userVO = mapper.readValue(fileUserVO, UserVO.class);
        when(userManagerRepository.save(user)).thenReturn(user);
        projectManagerServiceImpl.updateUser(userVO);
    }

}