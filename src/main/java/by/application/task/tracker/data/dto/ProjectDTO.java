package by.application.task.tracker.data.dto;

import org.hibernate.validator.constraints.NotBlank;

public class ProjectDTO {

    @NotBlank(message = "Project name is required")
    private String projectName;
    @NotBlank(message = "Project country is required")
    private String country;

    public String getProjectName() {return projectName;}
    public void setProjectName(String projectName) {this.projectName = projectName;}
    public String getCountry() {return country;}
    public void setCountry(String country) {this.country = country;}
}
