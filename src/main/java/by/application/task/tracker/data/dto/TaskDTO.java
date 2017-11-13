package by.application.task.tracker.data.dto;

import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;
import java.util.Date;

public class TaskDTO implements Serializable{

    public TaskDTO() {}

    @NotBlank(message = "Task name is required")
    private String taskName;
    private Date startDate;
    private Date endDate;
    private String description;
    private long taskType;
    private long taskPriority;
    private long executor;
    private long creator;
    private long taskStatus;
    private long project;

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public Date getStartDate() {return startDate;}

    public void setStartDate(Date startDate) {this.startDate = startDate;}

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

    public long getTaskType() {
        return taskType;
    }

    public void setTaskType(long taskType) {
        this.taskType = taskType;
    }

    public long getTaskPriority() {
        return taskPriority;
    }

    public void setTaskPriority(long taskPriority) {
        this.taskPriority = taskPriority;
    }

    public long getExecutor() {
        return executor;
    }

    public void setExecutor(long executor) {
        this.executor = executor;
    }

    public long getCreator() {
        return creator;
    }

    public void setCreator(long creator) {
        this.creator = creator;
    }

    public long getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(long taskStatus) {
        this.taskStatus = taskStatus;
    }

    public long getProject() {
        return project;
    }

    public void setProject(long project) {
        this.project = project;
    }
}
