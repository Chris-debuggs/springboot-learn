package com.springbootdemo.springbootlearn.repository;

import com.springbootdemo.springbootlearn.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, String> {
    List<Task> findByOwnerUsername(String ownerUsername);
    long countByOwnerUsername(String ownerUsername);
    long countByOwnerUsernameAndCompleted(String ownerUsername, boolean completed);
}
