package com.cts.bo;

public class ProjectVO {

	private Long projectId;
	private String projectName;
	private String startDate;
	private String endDate;
	private String priority;
	private String status;

	public ProjectVO() {}

	public ProjectVO(Long projectId, String projectName, String startDate, String endDate, String priority, String status) {
		this.projectId = projectId; this.projectName = projectName;
		this.startDate = startDate; this.endDate = endDate;
		this.priority = priority; this.status = status;
	}

	public Long getProjectId() {
		return projectId;
	}
	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
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
	public String getStatus() { return status; }
	public void setStatus(String status) {
		this.status = status;
	}

}
