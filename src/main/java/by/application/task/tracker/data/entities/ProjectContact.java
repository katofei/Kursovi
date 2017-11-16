package by.application.task.tracker.data.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "project_contact")
public class ProjectContact {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", length = 5, nullable = false)
    private Long contact_id;

    @Column(name = "office_phone")
    private String officePhone;

    @Column(name = "office_Email")
    private String workEmail;

    @Column(name = "fax")
    private String fax;

    @ManyToOne
    private Location projectLocation;

    @OneToMany(mappedBy = "projectContact", fetch = FetchType.LAZY)
    private List<Project> projectList;

    public Long getContact_id() {return contact_id;}

    public void setContact_id(Long contact_id) {this.contact_id = contact_id;}

    public String getOfficePhone() {return officePhone;}

    public void setOfficePhone(String officePhone) {this.officePhone = officePhone;}

    public String getWorkEmail() {return workEmail;}

    public void setWorkEmail(String workEmail) {this.workEmail = workEmail;}

    public String getFax() {return fax;}

    public void setFax(String fax) {this.fax = fax;}

    public Location getProjectLocation() {return projectLocation;}

    public void setProjectLocation(Location projectLocation) {this.projectLocation = projectLocation;}
}
