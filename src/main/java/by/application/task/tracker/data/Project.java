package by.application.task.tracker.data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "projects")
public class Project {

    public Project(){}

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "project_id",nullable = false)
    private Long projectId;

    @Column(name = "project_name")
    private String projectName;

    @Column(name = "project_country")
    private String projectCountry;

    @OneToMany(mappedBy = "projectId", fetch = FetchType.LAZY)
    private List<User> users;

    @OneToMany(mappedBy = "projectId", fetch = FetchType.LAZY)
    private List<Task> tasks;

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectCountry() {
        return projectCountry;
    }

    public void setProjectCountry(String projectCountry) {
        this.projectCountry = projectCountry;
    }

    public List<User> getUsers() {return users;}

    public void setUsers(List<User> users) {this.users = users;}

    public List<Task> getTasks() {return tasks;}

    public void setTasks(List<Task> tasks) {this.tasks = tasks;}

    @Override
    public String toString() {
        return "Project{" +
                "projectId=" + projectId +
                ", projectName='" + projectName + '\'' +
                ", projectCountry='" + projectCountry + '\'' +
                ", users=" + users +
                ", tasks=" + tasks +
                '}';
    }
}
