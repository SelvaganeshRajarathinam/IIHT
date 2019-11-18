package com.cts.entity;

import javax.persistence.*;

@Entity
@Table(name = "Task")
public class Task {

	@Id
	@Column(name = "Task_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long taskId;

	@Column(name = "Task")
	private String taskName;

	@Column(name = "Priority")
	private String priority;

	/*@OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	@JoinColumn(name = "Parent_id", insertable = true, updatable = true)*/
	@Column(name = "Parent")
	private String parentTaskName;

	@Column(name = "Start_Date")
	private String startDate;

	@Column(name = "End_Date")
	private String endDate;

	public Task() {}

	public String getParentTaskName() {
		return parentTaskName;
	}

	public void setParentTaskName(String parentTaskName) {
		this.parentTaskName = parentTaskName;
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

}
