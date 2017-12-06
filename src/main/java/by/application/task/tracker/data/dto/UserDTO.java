package by.application.task.tracker.data.dto;

import by.application.task.tracker.validation.ValidEmail;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Size;
import java.io.Serializable;

public class UserDTO implements Serializable{

    public UserDTO(){}

    @NotBlank(message = "User name is required")
    private String userName;

    @NotBlank(message = "User surname is required")
    private String userSurname;

    @ValidEmail
    @NotBlank(message = "E-mail can not be empty")
    private String eMail;

    @Size(min =6, message = "Login is too short: 6 symbols are required")
    private String login;

    @Size(min =6, message = "Password is too short: 6 symbols are required")
    private String password;

    private long userRole;
    private long project;
    private long position;
    private long qualification;
    private long projectRole;
    private long userContact;

    public String getUserName() {return userName;}
    public void setUserName(String userName) {this.userName = userName;}

    public String getUserSurname() {return userSurname;}
    public void setUserSurname(String userSurname) {this.userSurname = userSurname;}

    public String geteMail() {return eMail;}
    public void seteMail(String eMail) {this.eMail = eMail;}

    public String getLogin() {return login;}
    public void setLogin(String login) {this.login = login;}

    public String getPassword() {return password;}
    public void setPassword(String password) {this.password = password;}

    public long getProject() {return project;}
    public void setProject(long project) {this.project = project;}

    public long getPosition() {return position;}
    public void setPosition(long position) {this.position = position;}

    public long getQualification() {return qualification;}
    public void setQualification(long qualification) {this.qualification = qualification;}

    public long getProjectRole() {return projectRole;}
    public void setProjectRole(long projectRole) {this.projectRole = projectRole;}

    public long getUserRole() {return userRole;}
    public void setUserRole(long userRole) {this.userRole = userRole;}

    public long getUserContact() {return userContact;}
    public void setUserContact(long userContact) {this.userContact = userContact;}
}
