package by.application.task.tracker.data.entities;

import by.application.task.tracker.data.dto.ProjectDTO;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "projects")
public class Project {

    public Project(){}

    public Project(ProjectDTO projectDTO){
        this.description = projectDTO.getDescription();
        this.projectName = projectDTO.getProjectName();
        this.mainAim = projectDTO.getMainAim();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "project_id",nullable = false)
    private Long projectId;

    @Column(name = "project_name")
    private String projectName;

    @Lob
    private String description;

    @Lob
    private String mainAim;

    @OneToOne
    private ProjectContact projectContact;

    @OneToMany(mappedBy = "project", fetch = FetchType.LAZY)
    private List<User> userList;

    @ManyToOne
    private ProjectStrategy projectStrategy;

    @OneToMany(mappedBy = "project", fetch = FetchType.LAZY)
    private List<Dashboard> dashboardList;

    public Long getProjectId() {
        return projectId;
    }
    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }
    public void setProjectName(String projectName) {this.projectName = projectName;}

    public List<User> getUserList() {return userList;}
    public void setUserList(List<User> userList) {this.userList = userList;}

    public List<Dashboard> getDashboardList() {return dashboardList;}
    public void setDashboardList(List<Dashboard> dashboardList) {this.dashboardList = dashboardList;}

    public String getDescription() {return description;}
    public void setDescription(String description) {this.description = description;}

    public String getMainAim() {return mainAim;}
    public void setMainAim(String mainAim) {this.mainAim = mainAim;}

    public ProjectContact getProjectContact() {return projectContact;}
    public void setProjectContact(ProjectContact projectContact) {this.projectContact = projectContact;}
}
