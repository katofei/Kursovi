package by.application.task.tracker.data.dto;

import java.io.Serializable;
import java.util.Date;

public class TaskDTO implements Serializable{

    public TaskDTO() {}

    private long Id;
    private String name;
    private String taskName;
    private Date startDate;
    private Date endDate;
    private String description;
    private long taskTypeId;
    private long taskPriorityId;
    private long executorId;
    private long creatorId;
    private long taskStatusID;
    private long projectId;


    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getTaskTypeId() {
        return taskTypeId;
    }

    public void setTaskTypeId(long taskTypeId) {
        this.taskTypeId = taskTypeId;
    }

    public long getTaskPriorityId() {
        return taskPriorityId;
    }

    public void setTaskPriorityId(long taskPriorityId) {
        this.taskPriorityId = taskPriorityId;
    }

    public long getExecutorId() {
        return executorId;
    }

    public void setExecutorId(long executorId) {
        this.executorId = executorId;
    }

    public long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(long creatorId) {
        this.creatorId = creatorId;
    }

    public long getTaskStatusID() {
        return taskStatusID;
    }

    public void setTaskStatusID(long taskStatusID) {
        this.taskStatusID = taskStatusID;
    }

    public long getProjectId() {
        return projectId;
    }

    public void setProjectId(long projectId) {
        this.projectId = projectId;
    }
}
