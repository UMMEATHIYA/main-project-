package com.example.taskservice.controller;

import com.example.taskservice.dao.TaskDao;
import com.example.taskservice.entity.Task;
import com.example.taskservice.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/task")
public class TaskController {

    @Autowired
    TaskService taskService;

    @Autowired
    TaskDao taskDao;

    @GetMapping("tasksByStatus/{status}")
    public ResponseEntity<List<Task>> getTaskByStatus(@PathVariable boolean status)
    {
        return taskService.findAllByCompleted(status);
    }
    @GetMapping("tasksByAssignedTo/{assignedTo}")
    public ResponseEntity<List<Task>> getTaskByUser(@PathVariable String assignedTo)
    {
        return taskService.findAllByAssignedTo(assignedTo);
    }
    @PostMapping("/addTask")
    public Task createTask(@RequestBody Task task)
    {
        return taskDao.save(task);
    }
    @PutMapping("/updateTask/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable(value="id") Long id,@RequestBody Task task)
    {
        Task task1 = taskDao.findById(id).orElse(null);
        task1.setCompleted(true);

        final Task updatedTask = taskDao.save(task1);

        return ResponseEntity.ok(updatedTask);
    }

    @DeleteMapping("/deleteTask/{id}")
    public Map<String, Boolean> deletetask(@PathVariable(value = "id") Long id)
    {
        Task task = taskDao.findById(id).orElse(null);
        taskDao.delete(task);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;

    }
    @GetMapping("/tasks")
    public List<Task> getAllTask() {
        return taskDao.findAll();
    }

}

