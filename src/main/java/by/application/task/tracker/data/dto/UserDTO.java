package by.application.task.tracker.data.dto;

import by.application.task.tracker.validation.ValidEmail;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Size;
import java.io.Serializable;

public class UserDTO implements Serializable {

    public UserDTO() {
    }

    @NotBlank(message = "User name is required")
    private String userName;

    @NotBlank(message = "User surname is required")
    private String userSurname;

    @Size(min = 6, message = "Login is too short: 6 symbols are required")
    private String login;

    @Size(min = 6, message = "Password is too short: 6 symbols are required")
    private String password;

    private long userRole;
    private long project;
    private long position;
    private long qualification;
    private long projectRole;
    private long userContact;

    private String country;
    private String city;
    private String street;
    private int houseNumber;

    @ValidEmail
    @NotBlank(message = "E-mail can not be empty")
    private String workEmail;
    private String workPhone;
    private String privateEmail;
    private String privatePhone;
    private String fax;

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

    public long getProject() {
        return project;
    }

    public void setProject(long project) {
        this.project = project;
    }

    public long getPosition() {
        return position;
    }

    public void setPosition(long position) {
        this.position = position;
    }

    public long getQualification() {
        return qualification;
    }

    public void setQualification(long qualification) {
        this.qualification = qualification;
    }

    public long getProjectRole() {
        return projectRole;
    }

    public void setProjectRole(long projectRole) {
        this.projectRole = projectRole;
    }

    public long getUserRole() {
        return userRole;
    }

    public void setUserRole(long userRole) {
        this.userRole = userRole;
    }

    public long getUserContact() {
        return userContact;
    }

    public void setUserContact(long userContact) {
        this.userContact = userContact;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(int houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getPrivateEmail() {
        return privateEmail;
    }

    public void setPrivateEmail(String privateEmail) {
        this.privateEmail = privateEmail;
    }

    public String getPrivatePhone() {
        return privatePhone;
    }

    public void setPrivatePhone(String privatePhone) {
        this.privatePhone = privatePhone;
    }

    public String getWorkEmail() {
        return workEmail;
    }

    public void setWorkEmail(String workEmail) {
        this.workEmail = workEmail;
    }

    public String getWorkPhone() {
        return workPhone;
    }

    public void setWorkPhone(String workPhone) {
        this.workPhone = workPhone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

}
