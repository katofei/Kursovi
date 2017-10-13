package by.application.task.tracker.data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "statuses")
public class TaskStatus implements Serializable{
    public TaskStatus(){}

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "status_id", length = 5, nullable = false)
    private Long statusId;

    @Column(name = "status_name")
    private String statusName;

    @OneToMany(mappedBy = "taskStatus", fetch = FetchType.LAZY)
    private List<Task> tasks;

    public Long getStatusId() {return statusId;}

    public void setStatusId(Long statusId) {this.statusId = statusId;}

    public String getStatusName() {return statusName;}

    public void setStatusName(String statusName) {this.statusName = statusName;}

    public List<Task> getTasks() {return tasks;}

    public void setTasks(List<Task> tasks) {this.tasks = tasks;}

    @Override
    public String toString() {
        return "TaskStatus{" +
                "statusId=" + statusId +
                ", statusName='" + statusName + '\'' +
                ", tasks=" + tasks +
                '}';
    }
}
