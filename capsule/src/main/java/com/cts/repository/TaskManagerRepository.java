package com.cts.repository;

import com.cts.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskManagerRepository extends JpaRepository<Task,Long>{

	@Query("SELECT count(t) FROM Task t where t.taskId = ?1")
	public Long getTotalTasksForProjectId(Long projectId);
}

