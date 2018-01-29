package by.application.task.tracker.data.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "dashboard_priorities")
public class DashboardPriority {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "priority_id", length = 5, nullable = false)
    private Long priorityId;

    @Column(name = "priority_name")
    private String priorityName;

    @OneToMany(mappedBy = "priority", fetch = FetchType.LAZY)
    private List<Dashboard> dashboardList;
}
