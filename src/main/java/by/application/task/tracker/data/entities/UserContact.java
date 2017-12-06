package by.application.task.tracker.data.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user_contact")
public class UserContact {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", length = 5, nullable = false)
    private Long contacId;

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

    @ManyToOne
    private Location userLocation;

    @OneToMany(mappedBy = "userContact", fetch = FetchType.LAZY)
    private List<User> users;

    public Long getContacId() {return contacId;}
    public void setContacId(Long contacId) {this.contacId = contacId;}

    public String getWorkPhone() {return workPhone;}
    public void setWorkPhone(String workPhone) {this.workPhone = workPhone;}

    public String getPrivatePhone() {return privatePhone;}
    public void setPrivatePhone(String privatePhone) {this.privatePhone = privatePhone;}

    public String getWorkEmail() {return workEmail;}
    public void setWorkEmail(String workEmail) {this.workEmail = workEmail;}

    public String getPrivateEmail() {return privateEmail;}
    public void setPrivateEmail(String privateEmail) {this.privateEmail = privateEmail;}

    public String getFax() {return fax;}
    public void setFax(String fax) {this.fax = fax;}

    public Location getUserLocation() {return userLocation;}
    public void setUserLocation(Location userLocation) {this.userLocation = userLocation;}
}
