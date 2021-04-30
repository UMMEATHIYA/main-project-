package com.example.taskservice.dao;

import com.example.taskservice.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskDao extends JpaRepository<Task, Long> {

    public Iterable<Task> findAllByCompleted(boolean completed);
    public Iterable<Task> findAllByAssignedTo(String assignedTo);
    public void deleteTaskById(Long id);

}
