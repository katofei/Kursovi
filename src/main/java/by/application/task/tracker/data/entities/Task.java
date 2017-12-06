package by.application.task.tracker.data.entities;

import by.application.task.tracker.data.dto.TaskDTO;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tasks")
public class Task {

    public Task() {}

    public Task(TaskDTO taskDTO){
        this.taskName = taskDTO.getTaskName();
        this.description = taskDTO.getDescription();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", length = 5, nullable = false)
    private Long id;

    @Column(name = "task_name")
    private String taskName;

    @Column(name = "created")
    private String created;

    @Column(name = "updated")
    private String updated;

    @Column(name = "resolved")
    private String resolved;

    @Column(name = "estimation")
    private String estimation;

    @Lob
    private String description;

    @Column(name = "time_spent")
    private double timeSpent;

    @Column(name = "percentage")
    private Double percentage;

    @ManyToOne
    private User creator;

    @ManyToOne
    private User executor;

    @ManyToOne
    private TaskStatus taskStatus;

    @ManyToOne
    private TaskPriority taskPriority;

    @ManyToOne
    private TaskType taskType;

    @ManyToOne
    private Project project;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {this.taskName = taskName;}

    public String getCreated() {return created;}

    public void setCreated(String created) {this.created = created;}

    public String getUpdated() {return updated;}

    public void setUpdated(String updated) {this.updated = updated;}

    public String getResolved() {return resolved;}

    public void setResolved(String resolved) {this.resolved = resolved;}

    public String getEstimation() {return estimation;}

    public void setEstimation(String estimation) {this.estimation = estimation;}

    public String getDescription() {return description;}

    public void setDescription(String description) {this.description = description;}

    public double getTimeSpent() {return timeSpent;}

    public void setTimeSpent(double timeSpent) {this.timeSpent = timeSpent;}

    public Double getPercentage() {return percentage;}

    public void setPercentage(Double percentage) {this.percentage = percentage;}

    public User getCreator() {return creator;}

    public void setCreator(User creator) {this.creator = creator;}

    public User getExecutor() {return executor;}

    public void setExecutor(User executor) {this.executor = executor;}

    public TaskStatus getTaskStatus() {return taskStatus;}

    public void setTaskStatus(TaskStatus taskStatus) {this.taskStatus = taskStatus;}

    public TaskPriority getTaskPriority() {
        return taskPriority;
    }

    public void setTaskPriority(TaskPriority taskPriority) {this.taskPriority = taskPriority;}

    public TaskType getTaskType() {return taskType;}

    public void setTaskType(TaskType taskType) {this.taskType = taskType;}

    public Project getProject() {return project;}

    public void setProject(Project project) {this.project = project;}
}




