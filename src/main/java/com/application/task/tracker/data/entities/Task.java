package com.application.task.tracker.data.entities;

import com.application.task.tracker.data.entities.Enums.TaskPriorityEnum;
import com.application.task.tracker.data.entities.Enums.TaskStatusEnum;
import com.application.task.tracker.data.entities.Enums.TaskTypeEnum;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tasks")
public class Task {

    public Task() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "task_name")
    private String taskName;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @Column(name = "time_spent")
    private Date timeSpent;

    @ManyToOne
    //@JoinColumn(name = "creator_id", nullable = false)
    private User creator;

    @ManyToOne
    //@JoinColumn(name = "executor_id", nullable = false)
    private User executor;

    @Column(name = "task_status")
    @Enumerated(EnumType.STRING)
    private TaskStatusEnum taskStatus;

    @Column(name = "task_priority")
    @Enumerated(EnumType.STRING)
    private TaskPriorityEnum taskPriority;

    @Column(name = "task_type")
    @Enumerated(EnumType.STRING)
    private TaskTypeEnum taskType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Date getStimeSpent() {
        return timeSpent;
    }

    public void setStimeSpent(Date stimeSpent) {
        this.timeSpent = stimeSpent;
    }

    public User getCreator() {return creator;}

    public void setCreator(User creator) {this.creator = creator;}

    public User getExecutor() {return executor;}

    public void setExecutor(User executor) {this.executor = executor;}

    public TaskStatusEnum getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(TaskStatusEnum taskStatus) {
        this.taskStatus = taskStatus;
    }

    public TaskPriorityEnum getTaskPriority() {
        return taskPriority;
    }

    public void setTaskPriority(TaskPriorityEnum taskPriority) {
        this.taskPriority = taskPriority;
    }

    public TaskTypeEnum getTaskType() {
        return taskType;
    }

    public void setTaskType(TaskTypeEnum taskType) {
        this.taskType = taskType;
    }

    @Override
    public String toString() {
        return "Task{" +
                "task_id=" + id +
                ", taskName='" + taskName + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", stimeSpent=" + timeSpent +
                ", creatorId=" + creator +
                ", executorId=" + executor +
                ", taskStatus=" + taskStatus +
                ", taskPriority=" + taskPriority +
                ", taskType=" + taskType +
                '}';
    }
}
