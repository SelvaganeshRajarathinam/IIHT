package com.cts.repository;

import com.cts.entity.ParentTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParentTaskManagerRepository extends JpaRepository<ParentTask,Long>{

	@Query("SELECT pt FROM ParentTask pt where pt.parentTaskId = ?1")
	public List<ParentTask> findAllParentTaskByProjectId(String projectId);
}

