package com.cts.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="users")
public class User {

	@Id
	@Column(name="User_ID")
	private String userId;
	 
	@Column(name="First_Name")
	private String firstName;
	 
	@Column(name="Last_Name")
	private String lastName;
	 
	@Column(name="Employee_ID")
	private String employeeId;

	@Column(name="Project_ID")
	private String projectId;

	@Column(name="Task_ID")
	private String taskId;
	
	public User() {}

	public User(String employeeId, String firstName, String lastName, String userId, String projectId, String taskId) {
		this.employeeId = employeeId; this.firstName = firstName;; this.lastName = lastName;
		this.userId = userId; this.projectId = projectId; this.taskId = taskId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

}
