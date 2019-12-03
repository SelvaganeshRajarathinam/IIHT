package com.cts.bo;

import javafx.scene.Parent;

public class ParentTaskVO {

	private Long parentTaskId;
	private String parentTaskName;

	public ParentTaskVO() {}
	public ParentTaskVO(Long parentTaskId, String parentTaskName) {
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
