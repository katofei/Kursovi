package by.application.task.tracker.data.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "dashboard_priority")
public class DashboardPriority {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "priority_id", length = 5, nullable = false)
    public Long priorityId;

    @Column(name = "priority_name")
    public String priorityName;

    @OneToMany(mappedBy = "priority", fetch = FetchType.LAZY)
    private List<Dashboard> dashboardList;

    public Long getPriorityId() {return priorityId;}
    public void setPriorityId(Long priorityId) {this.priorityId = priorityId;}

    public String getPriorityName() {return priorityName;}
    public void setPriorityName(String priorityName) {this.priorityName = priorityName;}

    public List<Dashboard> getDashboardList() {return dashboardList;}
    public void setDashboardList(List<Dashboard> dashboardList) {this.dashboardList = dashboardList;}
}
