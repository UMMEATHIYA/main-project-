package com.example.taskservice.service;

import com.example.taskservice.dao.TaskDao;
import com.example.taskservice.entity.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class TaskServiceImpl implements TaskService{

    @Autowired
    TaskDao taskDao;

    @Override
    public ResponseEntity<List<Task>> findAllByCompleted(boolean completed) {

        List<Task> task = new ArrayList<>();
        for(Task t:taskDao.findAllByCompleted(completed))
        {
            task.add(t);
        }
        return ResponseEntity.ok().body(task);
    }

    @Override
    public ResponseEntity<List<Task>> findAllByAssignedTo(String assignedTo) {
        List<Task> task = new ArrayList<>();
        for(Task t:taskDao.findAllByAssignedTo(assignedTo))
        {
            task.add(t);
        }
        return ResponseEntity.ok().body(task);
    }

    @Override
    public void addTask(Task task) {
        taskDao.save(task);
    }

    @Override
    public void updateTask(Task task) {
        taskDao.save(task);
    }

    @Override
    public void deleteTask(Task task) {
        taskDao.delete(task);
    }

    @Override
    public void deleteTaskById(Long id) {
        taskDao.deleteTaskById(id);
    }
}

