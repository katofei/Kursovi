package by.application.task.tracker.data.dto;

import java.io.Serializable;

public class UserDTO implements Serializable{

    public UserDTO(){}

    private String userName;
    private String userSurname;
    private String eMail;
    private String login;
    private String password;
    private long project;
    private long position;
    private long qualification;
    private long projectRole;

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
}
