package com.cts.bo;

public class UserVO {

	private String employeeId;
	private String firstName;
	private String lastName;
	private String userId;
	private String projectId;
	private String taskId;

	public UserVO() {}

	public UserVO(String employeeId, String firstName, String lastName, String userId, String projectId, String taskId) {
		this.employeeId = employeeId; this.firstName = firstName;; this.lastName = lastName;
		this.userId = userId; this.projectId = projectId; this.taskId = taskId;
	}

	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
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
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
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
