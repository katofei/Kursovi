package by.application.task.tracker.data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "types")
public class TaskType implements Serializable{
    public TaskType(){}

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "type_id", length = 5, nullable = false)
    private Long typeId;

    @Column(name = "type_name")
    private String typeName;

    @OneToMany(mappedBy = "taskType", fetch = FetchType.LAZY)
    private List<Task> tasks;

    public Long getTypeId() {return typeId;}

    public void setTypeId(Long typeId) {this.typeId = typeId;}

    public String getTypeName() {return typeName;}

    public void setTypeName(String typeName) {this.typeName = typeName;}

    public List<Task> getTasks() {return tasks;}

    public void setTasks(List<Task> tasks) {this.tasks = tasks;}

    @Override
    public String toString() {
        return "TaskType{" +
                "typeId=" + typeId +
                ", typeName='" + typeName + '\'' +
                ", tasks=" + tasks +
                '}';
    }
}