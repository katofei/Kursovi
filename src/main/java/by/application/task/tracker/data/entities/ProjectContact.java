package by.application.task.tracker.data.entities;

import by.application.task.tracker.data.dto.ProjectDTO;

import javax.persistence.*;

@Entity
@Table(name = "project_contact")
public class ProjectContact {

    public ProjectContact(){}

    public ProjectContact(ProjectDTO projectDTO){
        this.fax = projectDTO.getFax();
        this.officeEmail = projectDTO.getOfficeEmail();
        this.officePhone = projectDTO.getOfficePhone();
        this.country = projectDTO.getCountry();
        this.city = projectDTO.getCity();
        this.street = projectDTO.getCity();
        this.houseNumber = projectDTO.getHouseNumber();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", length = 5, nullable = false)
    private Long contactId;

    @Column(name = "office_phone")
    private String officePhone;

    @Column(name = "office_Email")
    private String officeEmail;

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

    @OneToOne(mappedBy = "projectContact", fetch = FetchType.LAZY)
    private Project project;

    public Long getContactId() {return contactId;}
    public void setContactId(Long contact_id) {this.contactId = contact_id;}

    public String getOfficePhone() {return officePhone;}
    public void setOfficePhone(String officePhone) {this.officePhone = officePhone;}

    public String getOfficeEmail() {return officeEmail;}
    public void setOfficeEmail(String officeEmail) {this.officeEmail = officeEmail;}

    public String getFax() {return fax;}
    public void setFax(String fax) {this.fax = fax;}

    public String getCountry() {return country;}
    public void setCountry(String country) {this.country = country;}

    public String getCity() {return city;}
    public void setCity(String city) {this.city = city;}

    public String getStreet() {return street;}
    public void setStreet(String street) {this.street = street;}

    public int getHouseNumber() {return houseNumber;}
    public void setHouseNumber(int houseNumber) {this.houseNumber = houseNumber;}

    public Project getProject() {return project;}
    public void setProject(Project project) {this.project = project;}
}
