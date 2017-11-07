package by.application.task.tracker.data.dto;

import java.io.Serializable;

public class UserDTO implements Serializable{

   public UserDTO(){}

    private String name;
    private String surname;
    private String email;
    private String login;
    private String password;
    private long projectId;
    private long positionId;
    private long qualificationId;
    private long projectRoleId;

    public void setName(String name) {this.name = name;}

    public String getName() {return name;}

    public String getSurname() {return surname;}

    public void setSurname(String surname) {this.surname = surname;}

    public String getEmail() {return email;}

    public void setEmail(String email) {this.email = email;}

    public String getLogin() {return login;}

    public void setLogin(String login) {this.login = login;}

    public String getPassword() {return password;}

    public void setPassword(String password) {this.password = password;}

    public long getProjectId() {return projectId;}

    public void setProjectId(long projectId) {this.projectId = projectId;}

    public long getPositionId() {return positionId;}

    public void setPositionId(long positionId) {this.positionId = positionId;}

    public long getQualificationId() {return qualificationId;}

    public void setQualificationId(long qualificationId) {this.qualificationId = qualificationId;}

    public long getProjectRoleId() {return projectRoleId;}

    public void setProjectRoleId(long projectRoleId) {this.projectRoleId = projectRoleId;}
}
