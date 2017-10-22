package by.application.task.tracker.data;


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
    @Column(name = "user_id", length = 5, nullable = false)
    private Long userId;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "user_surname")
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

    @Column(name = "enabled")
    private Boolean enabled = true;

    @ManyToOne
    private UserRoles userRole;

    @ManyToOne
    private Project projectId;

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

    public UserRoles getRole() {
        return userRole;
    }

    public void setRole(UserRoles userRole) {
        this.userRole = userRole;
    }

    public Project getProjectId() {
        return projectId;
    }

    public void setProjectId(Project projectId) {
        this.projectId = projectId;
    }

    public Long getId() {
        return userId;
    }

    public void setId(Long userId) {
        this.userId = userId;
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

    public Long getUserId() {return userId;}

    public void setUserId(Long userId) {this.userId = userId;}

    public Boolean getEnabled() {return enabled;}

    public void setEnabled(Boolean enabled) {this.enabled = enabled;}

    public UserRoles getUserRole() {return userRole;}

    public void setUserRole(UserRoles userRole) {this.userRole = userRole;}

    @Override
    public String toString() {
        return "User{" +
                "user_id=" + userId +
                ", user_name='" + userName + '\'' +
                ", user_surname='" + userSurname + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", eMail='" + eMail + '\'' +
                ", position='" + position + '\'' +
                ", role=" + userRole +
                ", project_id=" + projectId +
                '}';
    }
}