package by.application.task.tracker.data.dto;

import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

public class UserDTO implements Serializable {

    public UserDTO() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getEstimationDate() {
        return estimationDate;
    }

    public void setEstimationDate(Date estimationDate) {
        this.estimationDate = estimationDate;
    }

    private long id;
    private Date estimationDate;

    private String userName;
    private String userSurname;

    @Size(min = 6, message = "Login is too short: 6 symbols are required")
    private String login;

    private String password;
    private String confirmationPassword;
    private String confirmationToken;
    private boolean enabled;

    private long userRole;
    private long project;
    private long position;
    private long qualification;
    private long projectRole;

    private String country;
    private String city;
    private String street;
    private int houseNumber;

    //@NotBlank(message = "E-mail can not be empty")
    private String workEmail;

   // @NotBlank(message = "Prone can not be empty")
    private String workPhone;

    private String privateEmail;
    private String privatePhone;
    private String fax;
    private String dueDate;

    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }

    public String getUserSurname() { return userSurname; }
    public void setUserSurname(String userSurname) { this.userSurname = userSurname; }

    public String getLogin() { return login; }
    public void setLogin(String login) { this.login = login; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public long getProject() { return project; }
    public void setProject(long project) { this.project = project; }

    public long getPosition() { return position; }
    public void setPosition(long position) { this.position = position; }

    public long getQualification() { return qualification; }
    public void setQualification(long qualification) { this.qualification = qualification; }

    public long getProjectRole() { return projectRole; }
    public void setProjectRole(long projectRole) { this.projectRole = projectRole; }

    public long getUserRole() { return userRole; }
    public void setUserRole(long userRole) { this.userRole = userRole; }

    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getStreet() { return street; }
    public void setStreet(String street) { this.street = street; }

    public int getHouseNumber() { return houseNumber; }
    public void setHouseNumber(int houseNumber) { this.houseNumber = houseNumber; }

    public String getPrivateEmail() { return privateEmail; }
    public void setPrivateEmail(String privateEmail) { this.privateEmail = privateEmail; }

    public String getPrivatePhone() { return privatePhone; }
    public void setPrivatePhone(String privatePhone) { this.privatePhone = privatePhone; }

    public String getWorkEmail() { return workEmail; }
    public void setWorkEmail(String workEmail) { this.workEmail = workEmail; }

    public String getWorkPhone() { return workPhone; }
    public void setWorkPhone(String workPhone) { this.workPhone = workPhone; }

    public String getFax() { return fax; }
    public void setFax(String fax) { this.fax = fax; }

    public String getConfirmationPassword() { return confirmationPassword; }
    public void setConfirmationPassword(String confirmationPassword) {
        this.confirmationPassword = confirmationPassword;
    }

    public String getConfirmationToken() { return confirmationToken; }
    public void setConfirmationToken(String confirmationToken) {
        this.confirmationToken = confirmationToken;
    }

    public boolean isEnabled() { return enabled; }
    public void setEnabled(boolean enabled) { this.enabled = enabled; }

    public String getEstimation() { return dueDate; }
    public void setEstimation(String dueDate) { this.dueDate = dueDate; }
}
