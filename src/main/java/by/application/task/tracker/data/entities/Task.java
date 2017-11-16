package by.application.task.tracker.data.entities;

import by.application.task.tracker.data.dto.TaskDTO;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "tasks")
public class Task {

    public Task() {}

    public Task(TaskDTO taskDTO){
        this.taskName = taskDTO.getTaskName();
        this.description = taskDTO.getDescription();
        this.estimation = taskDTO.getEstimation();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", length = 5, nullable = false)
    private Long id;

    @Column(name = "task_name")
    private String taskName;

    @Column(name = "created")
    @Temporal(TemporalType.DATE)
    private Date created;

    @Column(name = "updated")
    @DateTimeFormat(pattern="YYYY-MM-dd")
    private LocalDate updated;

    @Column(name = "resolved")
    @DateTimeFormat(pattern="YYYY-MM-dd")
    private LocalDate resolved;

    @Column(name = "estimation")
    @DateTimeFormat(pattern="YYYY-MM-dd")
    private LocalDate estimation;

    @Lob
    private String description;

    @Column(name = "time_spent")
    private Date timeSpent;

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

    public Date getCreated() {return created;}

    public void setCreated(Date created) {this.created = created;}

    public LocalDate getUpdated() {return updated;}

    public void setUpdated(LocalDate updated) {this.updated = updated;}

    public LocalDate getResolved() {return resolved;}

    public void setResolved(LocalDate resolved) {this.resolved = resolved;}

    public LocalDate getEstimation() {return estimation;}

    public void setEstimation(LocalDate estimation) {this.estimation = estimation;}

    public String getDescription() {return description;}

    public void setDescription(String description) {this.description = description;}

    public Date getTimeSpent() {return timeSpent;}

    public void setTimeSpent(Date timeSpent) {this.timeSpent = timeSpent;}

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




