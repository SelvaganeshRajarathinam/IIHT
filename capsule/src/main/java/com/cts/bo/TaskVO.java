package com.cts.bo;

public class TaskVO {

	private Long taskId;
	private String taskName;
	private String priority;
	private String parentTaskName;
	private String startDate;
	private String endDate;
	private Long status;
	private String user;

	public TaskVO(){}

	public TaskVO(Long taskId, String taskName, String priority, String parentTaskName, String startDate, String endDate, Long status, String user){
		this.taskId = taskId; this.taskName = taskName; this.priority = priority;
		this.parentTaskName = parentTaskName; this.startDate = startDate; this.endDate = endDate;
		this.status = status; this.user = user;
	}

	public Long getTaskId() {
		return taskId;
	}
	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public String getParentTaskName() {
		return parentTaskName;
	}
	public void setParentTaskName(String parentTaskName) {
		this.parentTaskName = parentTaskName;
	}
	public Long getStatus() {
		return status;
	}
	public void setStatus(Long status) {
		this.status = status;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
}
