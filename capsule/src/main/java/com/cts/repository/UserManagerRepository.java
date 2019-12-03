package com.cts.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cts.entity.User;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface UserManagerRepository extends JpaRepository<User,Long>{
    @Transactional
    @Query(value = "SELECT u.user_id, u.first_name, u.last_name, u.employee_id, u.project_id, u.task_id FROM users u WHERE u.status=?1", nativeQuery = true)
    List<User> searchActiveUsers(String user_status);

    @Modifying
    @Transactional
    @Query(value = "UPDATE users SET task_id=?2 WHERE user_id=?1",nativeQuery = true)
    void updateUserStatusById(String id, String user_status);

    @Transactional
    @Query(value = "SELECT u.user_id, u.employee_id, u.first_name, u.last_name, u.project_id, u.task_id FROM users u WHERE LOWER(u.first_name) LIKE CONCAT('%',:searchTerm,'%') OR LOWER(u.last_name) LIKE CONCAT('%',:searchTerm,'%')", nativeQuery = true)
    List<User> searchUserByName(@Param("searchTerm") String userName);

    User findByUserId(String userId);
}

