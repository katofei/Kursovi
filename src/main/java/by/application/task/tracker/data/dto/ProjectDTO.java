package by.application.task.tracker.data.dto;

import org.hibernate.validator.constraints.NotBlank;

public class ProjectDTO {

    @NotBlank(message = "Project name is required")
    private String projectName;

    private String description;

    @NotBlank(message = "Main aim is required")
    private String mainAim;

    @NotBlank(message = "E-mail can not be empty")
    private String officeEmail;

    @NotBlank(message = "Phone can not be empty")
    private String officePhone;
    private String fax;
    private String country;
    private String city;
    private String street;
    private int houseNumber;

    public String getCountry() {return country;}
    public void setCountry(String country) {this.country = country;}

    public String getCity() {return city;}
    public void setCity(String city) {this.city = city;}

    public String getStreet() {return street;}
    public void setStreet(String street) {this.street = street;}

    public int getHouseNumber() {return houseNumber;}
    public void setHouseNumber(int houseNumber) {this.houseNumber = houseNumber;}

    public String getProjectName() {return projectName;}
    public void setProjectName(String projectName) {this.projectName = projectName;}

    public String getDescription() {return description;}
    public void setDescription(String description) {this.description = description;}

    public String getMainAim() {return mainAim;}
    public void setMainAim(String mainAim) {this.mainAim = mainAim;}

    public String getOfficeEmail() {return officeEmail;}
    public void setOfficeEmail(String officeEmail) {this.officeEmail = officeEmail;}

    public String getOfficePhone() {return officePhone;}
    public void setOfficePhone(String officePhone) {this.officePhone = officePhone;}

    public String getFax() {return fax;}
    public void setFax(String fax) {this.fax = fax;}

}
