package by.application.task.tracker.data.entities;

import by.application.task.tracker.data.dto.UserDTO;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

@Entity
@Table(name = "user_contact")
public class UserContact {

    public UserContact() {}

    public UserContact(UserDTO userDTO) {
        this.fax = userDTO.getFax();
        this.privateEmail = userDTO.getPrivateEmail();
        this.workEmail = userDTO.getWorkEmail();
        this.workPhone = userDTO.getWorkPhone();
        this.privatePhone = userDTO.getPrivatePhone();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", length = 5, nullable = false)
    private Long contactId;

    @Column(name = "work_phone")
    private String workPhone;

    @Column(name = "private_phone")
    private String privatePhone;

    @Column(name = "work_Email", nullable = false, unique = true)
    @Email(message = "Please provide a valid e-mail")
    @NotEmpty(message = "Please provide an e-mail")
    private String workEmail;

    @Column(name = "private_email")
    private String privateEmail;

    @Column(name = "fax")
    private String fax;

    @Column(name = "country_name")
    private String countryName;

    @Column(name = "city_name")
    private String cityName;

    @Column(name = "street_name")
    private String streetName;

    @Column(name = "house_number")
    private int houseNumber;

    @OneToOne(mappedBy = "userContact", fetch = FetchType.LAZY)
    private User user;

    public Long getContactId() {
        return contactId;
    }
    public void setContactId(Long contactId) {
        this.contactId = contactId;
    }

    public String getWorkPhone() {
        return workPhone;
    }
    public void setWorkPhone(String workPhone) {
        this.workPhone = workPhone;
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

    public String getPrivateEmail() {
        return privateEmail;
    }
    public void setPrivateEmail(String privateEmail) {
        this.privateEmail = privateEmail;
    }

    public String getFax() {
        return fax;
    }
    public void setFax(String fax) {this.fax = fax;}

    public User getUser() {return user;}
    public void setUser(User user) {this.user = user;}

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public int getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(int houseNumber) {
        this.houseNumber = houseNumber;
    }
}
