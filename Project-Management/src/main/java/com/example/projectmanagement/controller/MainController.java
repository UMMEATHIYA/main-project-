package com.example.projectmanagement.controller;


import com.example.projectmanagement.model.Project;
import com.example.projectmanagement.repo.ProjectRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MainController {

    @Autowired
    private ProjectRepo projectRepo;

    @GetMapping("/project")
    public List<Project> getDetails(){
        return projectRepo.findAll();
    }
    @PostMapping("/project/new")
    public Project createProject(@Valid @RequestBody Project project) {
        return projectRepo.save(project);
    }
}
