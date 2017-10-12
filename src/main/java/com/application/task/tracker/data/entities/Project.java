package com.application.task.tracker.data.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "projects")
public class Project {

    public Project(){}

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id",nullable = false)
    private Long projectId;

    @Column(name = "project_name")
    private String projectName;

    @Column(name = "project_country")
    private String projectCountry;

    @OneToMany(mappedBy = "project", fetch = FetchType.LAZY)
    private List<User> users;

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

    @Override
    public String toString() {
        return "Project{" +
                "projectId=" + projectId +
                ", projectName='" + projectName + '\'' +
                ", projectCountry='" + projectCountry + '\'' +
                '}';
    }
}
