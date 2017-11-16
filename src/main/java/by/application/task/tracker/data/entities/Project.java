package by.application.task.tracker.data.entities;

import org.hibernate.validator.constraints.NotBlank;

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

    @Lob
    private String description;

    @Lob
    private String mainAim;

    @ManyToOne
    private ProjectContact projectContact;

    @OneToMany(mappedBy = "project", fetch = FetchType.LAZY)
    private List<User> users;

    @OneToMany(mappedBy = "project", fetch = FetchType.LAZY)
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

    public String getDescription() {return description;}

    public void setDescription(String description) {this.description = description;}

    public String getMainAim() {return mainAim;}

    public void setMainAim(String mainAim) {this.mainAim = mainAim;}

    public ProjectContact getProjectContact() {return projectContact;}

    public void setProjectContact(ProjectContact projectContact) {this.projectContact = projectContact;}
}
