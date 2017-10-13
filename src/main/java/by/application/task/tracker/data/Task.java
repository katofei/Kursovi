package by.application.task.tracker.data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tasks")
public class Task {

    public Task() {}

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", length = 5, nullable = false)
    private Long id;

    @Column(name = "task_name")
    private String taskName;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

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
    private Project projectId;

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

    public Project getProjectId() {return projectId;}

    public void setProjectId(Project projectId) {this.projectId = projectId;}

    public Double getPersentage() {return percentage;}

    public void setPersentage(Double percentage) {this.percentage = percentage;}

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




