package com.application.task.tracker.data.entities;


import com.application.task.tracker.data.entities.Enums.UserRolesEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "users")
public class User implements Serializable {

    public User(){}

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name")
    private String userName;

    @Column(name = "surname")
    private String userSurname;

    @Column(name = "login")
    private String login;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "password")
    private String password;

    @Column(name = "e_mail")
    private String eMail;

    @Column(name = "position")
    private String position;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private UserRolesEnum role;

    @ManyToOne
    //@JoinColumn(name = "project_id", nullable = false)
    private Project project;

    @JsonIgnore
    @OneToMany(mappedBy = "creator", fetch = FetchType.LAZY)
    private List<Task> createdTasks;

    @JsonIgnore
    @OneToMany(mappedBy = "executor", fetch = FetchType.LAZY)
    private List<Task> executingTasks;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserSurname() {
        return userSurname;
    }

    public void setUserSurname(String userSurname) {
        this.userSurname = userSurname;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public UserRolesEnum getRole() {
        return role;
    }

    public void setRole(UserRolesEnum role) {
        this.role = role;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public List<Task> getCreatedTasks() {
        return createdTasks;
    }

    public void setCreatedTasks(List<Task> createdTasks) {
        this.createdTasks = createdTasks;
    }

    public List<Task> getExecutingTasks() {
        return executingTasks;
    }

    public void setExecutingTasks(List<Task> executingTasks) {
        this.executingTasks = executingTasks;
    }

    @Override
    public String toString() {
        return "User{" +
                "user_id=" + id +
                ", user_name='" + userName + '\'' +
                ", user_surname='" + userSurname + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", eMail='" + eMail + '\'' +
                ", position='" + position + '\'' +
                ", role=" + role +
                ", project_id=" + project +
                '}';
    }
}