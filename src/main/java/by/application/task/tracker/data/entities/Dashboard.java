package by.application.task.tracker.data.entities;

import by.application.task.tracker.data.dto.DashboardDTO;

import javax.persistence.*;

@Entity
@Table(name = "dashboard")
public class Dashboard {

   public Dashboard(DashboardDTO dashboardDTO){
        this.dashboardName = dashboardDTO.getDashboardName();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "dashboard_id", length = 5, nullable = false)
    private Long dashboardId;

    @Column(name = "dashboard_name")
    private String  dashboardName;

    @Column(name = "created")
    private String created;

    @Column(name = "updated")
    private String updated;

    @Column(name = "resolved")
    private String resolved;

    @Column(name = "estimation")
    private String estimation;

    @Lob
    private String description;

    @Column(name = "time_spent")
    private double timeSpent;

    @ManyToOne
    private Project project;

    @ManyToOne
    private User creator;

    @ManyToOne
    private User reporter;

    @ManyToOne
    private DashboardStatus status;

    @ManyToOne
    private DashboardPriority priority;

    public Long getDashboardId() {return dashboardId;}
    public void setDashboardId(Long dashboardId) {this.dashboardId = dashboardId;}

    public String getDashboardName() {return dashboardName;}
    public void setDashboardName(String dashboardName) {this.dashboardName = dashboardName;}

    public String getCreated() {return created;}
    public void setCreated(String created) {this.created = created;}

    public String getUpdated() {return updated;}
    public void setUpdated(String updated) {this.updated = updated;}

    public String getResolved() {return resolved;}
    public void setResolved(String resolved) {this.resolved = resolved;}

    public String getEstimation() {return estimation;}
    public void setEstimation(String estimation) {this.estimation = estimation;}

    public String getDescription() {return description;}
    public void setDescription(String description) {this.description = description;}

    public double getTimeSpent() {return timeSpent;}
    public void setTimeSpent(double timeSpent) {this.timeSpent = timeSpent;}

    public User getCreator() {return creator;}
    public void setCreator(User creator) {this.creator = creator;}

    public User getReporter() {return reporter;}
    public void setReporter(User reporter) {this.reporter = reporter;}

    public DashboardStatus getStatus() {return status;}
    public void setStatus(DashboardStatus status) {this.status = status;}

    public DashboardPriority getPriority() {return priority;}
    public void setPriority(DashboardPriority priority) {this.priority = priority;}

    public Project getProject() {return project;}
    public void setProject(Project project) {this.project = project;}
}
