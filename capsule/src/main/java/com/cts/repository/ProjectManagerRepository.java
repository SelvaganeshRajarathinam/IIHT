package com.cts.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cts.entity.Project;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ProjectManagerRepository extends JpaRepository<Project,Long>{

    Project findByProjectId(String projectId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE project SET status=?2 WHERE project_id=?1", nativeQuery = true)
    void updateProjectStatus(String projectId, String status);

    @Transactional
    @Query(value = "SELECT p.project_id, p.project, p.start_date,p.end_date,p.priority FROM project p WHERE p.status<>?1", nativeQuery = true)
    List<Project> findActiveProjects(String projectStatus);

    @Transactional
    @Query(value = "SELECT p.project_id, p.project, p.start_date, p.end_date, p.priority FROM project p WHERE LOWER(p.project) LIKE CONCAT('%',:keyword,'%')", nativeQuery = true)
    List<Project> searchByProjectName(@Param("keyword") String projectName);
}

