package by.application.task.tracker.data.dto;

import org.hibernate.validator.constraints.NotBlank;

public class DashboardDTO {

    @NotBlank(message = "Task name is required")
    private String  dashboardName;
    private String created;
    private String updated;
    private String resolved;
    private String estimation;
    private String description;
    private double timeSpent;
    private long project;
    private long creator;
    private long reporter;
    private long status;
    private long priority;

    public String getDashboardName() {
        return dashboardName;
    }

    public void setDashboardName(String dashboardName) {
        this.dashboardName = dashboardName;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public String getResolved() {
        return resolved;
    }

    public void setResolved(String resolved) {
        this.resolved = resolved;
    }

    public String getEstimation() {
        return estimation;
    }

    public void setEstimation(String estimation) {
        this.estimation = estimation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getTimeSpent() {
        return timeSpent;
    }

    public void setTimeSpent(double timeSpent) {
        this.timeSpent = timeSpent;
    }

    public long getProject() {
        return project;
    }

    public void setProject(long project) {
        this.project = project;
    }

    public long getCreator() {
        return creator;
    }

    public void setCreator(long creator) {
        this.creator = creator;
    }

    public long getReporter() {
        return reporter;
    }

    public void setReporter(long reporter) {
        this.reporter = reporter;
    }

    public long getStatus() {
        return status;
    }

    public void setStatus(long status) {
        this.status = status;
    }

    public long getPriority() {
        return priority;
    }

    public void setPriority(long priority) {
        this.priority = priority;
    }
}
