package com.cts.repository;

import com.cts.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Repository
public interface TaskManagerRepository extends JpaRepository<Task,Long>{
	@Transactional
	@Query(value = "SELECT count(t) FROM Task t where t.taskId = ?1", nativeQuery = true)
	public Long getTotalTasksForProjectId(Long projectId);

	@Modifying
	@Transactional
	@Query(value = "UPDATE Task SET status=?2 WHERE task_id=?1", nativeQuery = true)
	void updateTaskStatus(String taskId, String status);

	@Transactional
	@Query(value = "SELECT a.task_id, a.task, a.parent, a.start_date, a.end_date, a.priority FROM Task a WHERE LOWER(a.task) LIKE CONCAT('%',:searchTerm,'%')", nativeQuery = true)
	List<Task> findTaskByTaskName(@Param("searchTerm") String taskName);

	@Modifying
	@Transactional
	@Query(value = "UPDATE Task SET status=?2 WHERE user_id=?1", nativeQuery = true)
	void updateTaskStatusByUserId(String userId, String status);

	@Query(value="SELECT a.task_id, a.task, a.parent, a.start_date, a.end_date, a.priority, a.status FROM Task a WHERE a.status<>?1", nativeQuery = true)
	List<Task> findActiveTasks(String status);

	Task findByTaskId(String taskId);
}

