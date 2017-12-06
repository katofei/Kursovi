package by.application.task.tracker.data.dto;

import org.hibernate.validator.constraints.NotBlank;

public class ProjectDTO {

    @NotBlank(message = "Project name is required")
    private String projectName;

    @NotBlank(message = "Project country is required")
    private String projectCountry;

    private String description;

    @NotBlank(message = "Main aim is required")
    private String mainAim;

    private long porjectContact;

    public String getProjectName() {return projectName;}
    public void setProjectName(String projectName) {this.projectName = projectName;}

    public String getProjectCountry() {return projectCountry;}
    public void setProjectCountry(String projectCountry) {this.projectCountry = projectCountry;}

    public String getDescription() {return description;}
    public void setDescription(String description) {this.description = description;}

    public String getMainAim() {return mainAim;}
    public void setMainAim(String mainAim) {this.mainAim = mainAim;}

    public long getPorjectContact() {return porjectContact;}
    public void setPorjectContact(long porjectContact) {this.porjectContact = porjectContact;}
}
