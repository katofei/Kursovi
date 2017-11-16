package by.application.task.tracker.data.dto;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

public class TaskDTO implements Serializable{

    public TaskDTO() {}

    @NotBlank(message = "Task name is required")
    private String taskName;

    @DateTimeFormat(pattern="YYYY-MM-dd")
    private Date created;

    @DateTimeFormat(pattern="YYYY-MM-dd")
    private Date resolved;

    @DateTimeFormat(pattern="YYYY-MM-dd")
    private Date updated;

    @DateTimeFormat(pattern="YYYY-MM-dd")
    private Date estimation;

    private String description;

    private long taskType;

    private long taskPriority;

    private long executor;

    private long creatorId;

    private long taskStatus;

    private long projectId;

    public String getTaskName() {return taskName;}

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getResolved() {
        return resolved;
    }

    public void setResolved(Date resolved) {
        this.resolved = resolved;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public Date getEstimation() {return estimation;}

    public void setEstimation(Date estimation) {this.estimation = estimation;}

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

    public long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(long creatorId) {
        this.creatorId = creatorId;
    }

    public long getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(long taskStatus) {
        this.taskStatus = taskStatus;
    }

    public long getProjectId() {
        return projectId;
    }

    public void setProjectId(long projectId) {
        this.projectId = projectId;
    }
}
