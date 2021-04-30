package com.example.projectmanagement;

import com.example.projectmanagement.model.Project;
import com.example.projectmanagement.model.User;
import com.example.projectmanagement.repo.ProjectRepo;
import com.example.projectmanagement.repo.TaskRepo;
import com.example.projectmanagement.repo.UserRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableEurekaClient
@EnableZuulProxy
public class ProjectManagementApplication  {

    public static void main(String[] args) {
        SpringApplication.run(ProjectManagementApplication.class, args);
    }

}
