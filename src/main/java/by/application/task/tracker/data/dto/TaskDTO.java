package by.application.task.tracker.data.dto;

import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

public class TaskDTO implements Serializable{

    public TaskDTO() {}

    @NotBlank(message = "Task name is required")
    private String taskName;

    private String resolved;
    private String updated;
    private String dueDate;
    private double estimation;
    private String description;
    private long taskType;
    private long taskPriority;
    private long taskStatus;
    private long executor;
    private long creator;
    private long project;
    private double percentage;
    private double timeSpent;

    public String getTaskName() {return taskName;}
    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getResolved() {return resolved;}
    public void setResolved(String resolved) {this.resolved = resolved;}

    public String getUpdated() {return updated;}
    public void setUpdated(String updated) {this.updated = updated;}

    public double getEstimation() {return estimation;}
    public void setEstimation(double estimation) {this.estimation = estimation;}

    public String getDueDate() { return dueDate; }
    public void setDueDate(String dueDate) { this.dueDate = dueDate; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public long getTaskType() { return taskType; }
    public void setTaskType(long taskType) { this.taskType = taskType; }

    public long getTaskPriority() { return taskPriority; }
    public void setTaskPriority(long taskPriority) { this.taskPriority = taskPriority; }

    public long getExecutor() { return executor; }
    public void setExecutor(long executor) { this.executor = executor; }

    public long getCreator() {return creator;}
    public void setCreator(long creator) { this.creator = creator; }

    public long getTaskStatus() { return taskStatus; }
    public void setTaskStatus(long taskStatus) { this.taskStatus = taskStatus; }

    public long getProject() { return project; }
    public void setProject(long project) { this.project = project; }

    public double getPercentage() {return percentage;}
    public void setPercentage(double percentage) {this.percentage = percentage;}

    public double getTimeSpent() {return timeSpent;}
    public void setTimeSpent(double timeSpent) {this.timeSpent = timeSpent;}
}
