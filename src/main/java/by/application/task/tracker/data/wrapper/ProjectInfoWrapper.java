package by.application.task.tracker.data.wrapper;

import by.application.task.tracker.data.entities.Project;
import by.application.task.tracker.data.entities.ProjectContact;

public class ProjectInfoWrapper {
    private Project project;
    private ProjectContact projectContact;

    public ProjectInfoWrapper() {
    }

    public ProjectInfoWrapper(Project project, ProjectContact projectContact) {
        this.project = project;
        this.projectContact = projectContact;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public ProjectContact getProjectContact() {
        return projectContact;
    }

    public void setProjectContact(ProjectContact projectContact) {
        this.projectContact = projectContact;
    }
}
