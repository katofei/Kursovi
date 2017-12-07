package by.application.task.tracker.data.entities;

import by.application.task.tracker.data.dto.UserDTO;

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
        this.country = userDTO.getCountry();
        this.city = userDTO.getCity();
        this.street = userDTO.getCity();
        this.houseNumber = userDTO.getHouseNumber();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", length = 5, nullable = false)
    private Long contactId;

    @Column(name = "work_phone")
    private String workPhone;

    @Column(name = "private_phone")
    private String privatePhone;

    @Column(name = "work_Email")
    private String workEmail;

    @Column(name = "private_Email")
    private String privateEmail;

    @Column(name = "fax")
    private String fax;

    @Column(name = "country")
    private String country;

    @Column(name = "city")
    private String city;

    @Column(name="street")
    private String street;

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
    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getCountry() {return country;}
    public void setCountry(String country) {this.country = country;}

    public String getCity() {return city;}
    public void setCity(String city) {this.city = city;}

    public String getStreet() {return street;}
    public void setStreet(String street) {this.street = street;}

    public int getHouseNumber() {return houseNumber;}
    public void setHouseNumber(int houseNumber) {this.houseNumber = houseNumber;}

    public User getUsers() {
        return user;
    }
    public void setUsers(User users) {
        this.user = users;
    }
}
