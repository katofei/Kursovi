package by.application.task.tracker.data.entities;

import by.application.task.tracker.data.dto.UserDTO;

import javax.persistence.*;

@Entity
@Table(name = "user_contact")
public class UserContact {

    public UserContact() {
    }

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

    @Column(name = "work_Email")
    private String workEmail;

    @Column(name = "private_Email")
    private String privateEmail;

    @Column(name = "fax")
    private String fax;

    @OneToOne(mappedBy = "userContact", fetch = FetchType.LAZY)
    private User user;

    @ManyToOne
    private Location location;

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

    public Location getLocation() {return location;}
    public void setLocation(Location location) {this.location = location;}

    public User getUser() {return user;}
    public void setUser(User user) {this.user = user;}
}
