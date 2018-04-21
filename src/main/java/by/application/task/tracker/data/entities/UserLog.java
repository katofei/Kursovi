package by.application.task.tracker.data.entities;

import by.application.task.tracker.data.dto.LogDTO;

import javax.persistence.*;

@Entity
@Table(name = "user_log")
public class UserLog {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "log_id", length = 5, nullable = false)
    private Long logId;

    @Column(name = "log_date")
    private String logDate;

    @Column(name = "time_spent")
    private double timeSpent;

    @Column(name = "percentage")
    private Double percentage;

    @OneToOne
    private User executor;

    public UserLog(LogDTO logDTO){
        this.percentage = logDTO.getPercentage();
        this.timeSpent  = logDTO.getTimeSpent();
    }

    public Long getLogId() { return logId;}
    public void setLogId(Long logId) { this.logId = logId;}

    public String getLogDate() { return logDate;}
    public void setLogDate(String logDate) { this.logDate = logDate;}

    public double getTimeSpent() { return timeSpent;}
    public void setTimeSpent(double timeSpent) { this.timeSpent = timeSpent;}

    public Double getPercentage() { return percentage;}
    public void setPercentage(Double percentage) { this.percentage = percentage;}

    public User getExecutor() { return executor;}
    public void setExecutor(User executor) { this.executor = executor;}

}
