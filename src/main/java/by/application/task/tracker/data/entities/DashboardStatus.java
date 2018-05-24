package by.application.task.tracker.data.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "dashboard_status")
public class DashboardStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "status_id", length = 5, nullable = false)
    public Long statusId;

    @Column(name = "status_name")
    public String statusName;

    @OneToMany(mappedBy = "status", fetch = FetchType.LAZY)
    private List<Dashboard> dashboardList;

    public Long getStatusId() {
        return statusId;
    }
    public void setStatusId(Long statusId) {
        this.statusId = statusId;
    }

    public String getStatusName() {
        return statusName;
    }
    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public List<Dashboard> getDashboardList() {
        return dashboardList;
    }
    public void setDashboardList(List<Dashboard> dashboardList) {
        this.dashboardList = dashboardList;
    }
}
