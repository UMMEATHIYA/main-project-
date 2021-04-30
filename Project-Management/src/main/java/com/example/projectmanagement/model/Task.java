package com.example.projectmanagement.model;

import javax.persistence.*;

@Entity
@Table(name="TASK")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer TID;
    @Column(name="START_DATE")
    private String Start_Date;
    @Column(name="END_DATE")
    private String End_Date;
    @Column(name="OWNER")
    private String Owner;
    @Column(name="SUBTASKS")
    private String Task;
    @Column(name="STATUS")
    private boolean status;
    @Column(name="PROJECTID")
    private Integer ProjectId;

    public Task() {
        super();
    }

    public Task(Integer TID, String start_Date, String end_Date, String owner, String task, boolean status, Integer projectId) {
        this.TID = TID;
        this.Start_Date = start_Date;
        this.End_Date = end_Date;
        this.Owner = owner;
        this.Task = task;
        this.status = status;
        this.ProjectId = projectId;
    }

    public Integer getTID() {
        return TID;
    }

    public void setTID(Integer TID) {
        this.TID = TID;
    }

    public String getStart_Date() {
        return Start_Date;
    }

    public void setStart_Date(String start_Date) {
        Start_Date = start_Date;
    }

    public String getEnd_Date() {
        return End_Date;
    }

    public void setEnd_Date(String end_Date) {
        End_Date = end_Date;
    }

    public String getOwner() {
        return Owner;
    }

    public void setOwner(String owner) {
        Owner = owner;
    }

    public String getTask() {
        return Task;
    }

    public void setTask(String task) {
        Task = task;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Integer getProjectId() {
        return ProjectId;
    }

    public void setProjectId(Integer projectId) {
        ProjectId = projectId;
    }
}
