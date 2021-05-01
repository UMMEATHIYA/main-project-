package com.example.taskservice.service;

import com.example.taskservice.entity.Task;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TaskService {

    public ResponseEntity<List<Task>> findAllByCompleted(boolean completed);
    public ResponseEntity<List<Task>> findAllByAssignedTo(String assignedTo);

    void updateTask(Task task);
    void deleteTask(Task task);
    void deleteTaskById(Long id);
    void addTask(Task task);
}
