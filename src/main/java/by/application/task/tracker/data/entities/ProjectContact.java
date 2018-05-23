package by.application.task.tracker.data.entities;

import by.application.task.tracker.data.dto.ProjectDTO;

import javax.persistence.*;

@Entity
@Table(name = "project_contact")
public class ProjectContact {

    public ProjectContact() {
    }

    public ProjectContact(ProjectDTO projectDTO) {
        this.fax = projectDTO.getFax();
        this.officeEmail = projectDTO.getOfficeEmail();
        this.officePhone = projectDTO.getOfficePhone();
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

    @Column(name = "country_name")
    private String countryName;

    @Column(name = "city_name")
    private String cityName;

    @Column(name = "street_name")
    private String streetName;

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

    public Project getProject() {
        return project;
    }
    public void setProject(Project project) {
        this.project = project;
    }

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
