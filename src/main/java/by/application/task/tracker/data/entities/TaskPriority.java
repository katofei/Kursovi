package by.application.task.tracker.data.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "task_priority")
public class TaskPriority implements Serializable{

   public TaskPriority(){}

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   @Column(name = "priority_id", length = 5, nullable = false)
   private Long priorityId;

   @Column(name = "priority_name")
   private String priorityName;

   @OneToMany(mappedBy = "taskPriority", fetch = FetchType.LAZY)
   private List<Task> tasks;

   public Long getPriorityId() {return priorityId;}
   public void setPriorityId(Long priorityId) {this.priorityId = priorityId;}

   public String getPriorityName() {return priorityName;}
   public void setPriorityName(String priorityName) {this.priorityName = priorityName;}

   public List<Task> getTasks() {return tasks;}
   public void setTasks(List<Task> tasks) {this.tasks = tasks;}

   //todo add user_logs table

}
