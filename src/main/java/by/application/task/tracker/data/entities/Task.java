package by.application.task.tracker.data.entities;

import by.application.task.tracker.data.dto.TaskDTO;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tasks")
public class Task {

    public Task() {}

    public Task(TaskDTO taskDTO){
        this.taskName = taskDTO.getTaskName();
        this.startDate = taskDTO.getStartDate();
        this.endDate = taskDTO.getEndDate();
        this.description = taskDTO.getDescription();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", length = 5, nullable = false)
    private Long id;

    @Column(name = "task_name")
    private String taskName;

    @Column(name = "start_date")
    @DateTimeFormat(pattern="YYYY-MM-dd")
    private Date startDate;

    @Column(name = "end_date")
    @DateTimeFormat(pattern="YYYY-MM-dd")
    private Date endDate;

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

    public TaskStatus getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(TaskStatus taskStatus) {
        this.taskStatus = taskStatus;
    }

    public TaskPriority getTaskPriority() {
        return taskPriority;
    }

    public void setTaskPriority(TaskPriority taskPriority) {this.taskPriority = taskPriority;}

    public TaskType getTaskType() {return taskType;}

    public void setTaskType(TaskType taskType) {this.taskType = taskType;}

    public Date getTimeSpent() {return timeSpent;}

    public void setTimeSpent(Date timeSpent) {this.timeSpent = timeSpent;}

    public Project getProject() {return project;}

    public void setProject(Project project) {this.project = project;}

    public Double getPersentage() {return percentage;}

    public void setPersentage(Double percentage) {this.percentage = percentage;}

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}




