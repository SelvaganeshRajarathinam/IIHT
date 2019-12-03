package com.cts.entity;

import javax.persistence.*;

@Entity
@Table(name = "Parent_Task")
public class ParentTask {

	@Id
	@Column(name = "Parent_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long parentTaskId;

	@Column(name = "Parent_Task")
	private String parentTaskName;

	public ParentTask() {}

	public ParentTask(Long parentTaskId, String parentTaskName) {
		this.parentTaskId = parentTaskId;
		this.parentTaskName = parentTaskName;
	}
	public Long getParentTaskId() {
		return parentTaskId;
	}
	public void setParentTaskId(Long parentTaskId) {
		this.parentTaskId = parentTaskId;
	}
	public String getParentTaskName() {
		return parentTaskName;
	}
	public void setParentTaskName(String parentTaskName) {
		this.parentTaskName = parentTaskName;
	}

}
