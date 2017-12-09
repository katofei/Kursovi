package by.application.task.tracker.data.entities;


import by.application.task.tracker.data.dto.UserDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "users")
public class User implements Serializable {

    public User() {}

    public User(UserDTO userDTO) {
        this.userName = userDTO.getUserName();
        this.userSurname = userDTO.getUserSurname();
        this.login = userDTO.getLogin();
    }

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

    @ManyToOne
    private Position position;

    @ManyToOne
    private Qualification qualification;

    @ManyToOne
    private ProjectRole projectRole;

    @ManyToOne
    private UserRole userRole;

    @ManyToOne
    private Project project;

    @OneToOne
    private UserContact userContact;

    @ManyToOne
    private UserStatus userStatus;

    @Column(name = "enabled")
    private Boolean enabled = true;

    @JsonIgnore
    @OneToMany(mappedBy = "creator", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Task> createdTasks;

    @JsonIgnore
    @OneToMany(mappedBy = "executor", fetch = FetchType.LAZY)
    private List<Task> executingTasks;

    public Long getUserId() {return userId;}
    public void setUserId(Long userId) {
        this.userId = userId;
    }

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

    public Position getPosition() {
        return position;
    }
    public void setPosition(Position position) {
        this.position = position;
    }

    public Boolean getEnabled() {
        return enabled;
    }
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public UserRole getUserRole() {
        return userRole;
    }
    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public Project getProject() {
        return project;
    }
    public void setProject(Project project) {
        this.project = project;
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

    public Qualification getQualification() {
        return qualification;
    }
    public void setQualification(Qualification qualification) {
        this.qualification = qualification;
    }

    public ProjectRole getProjectRole() {
        return projectRole;
    }
    public void setProjectRole(ProjectRole projectRole) {
        this.projectRole = projectRole;
    }

    public UserContact getUserContact() {return userContact;}
    public void setUserContact(UserContact userContact) {this.userContact = userContact;}

    public UserStatus getUserStatus() {return userStatus;}
    public void setUserStatus(UserStatus userStatus) {this.userStatus = userStatus;}
}