package by.application.task.tracker.data.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "project_strategy")
public class ProjectStrategy {

    public ProjectStrategy() {}

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "project_strategy_id", length = 5, nullable = false)
    private Long projectStrategyId;

    @Column(name = "project_strategy_name")
    private String projectStrategyName;

    @OneToMany(mappedBy = "projectStrategy", fetch = FetchType.LAZY)
    private List<Project> projects;

    public Long getProjectStrategyId() { return projectStrategyId; }
    public void setProjectStrategyId(Long projectStrategyId) { this.projectStrategyId = projectStrategyId; }

    public String getProjectStrategyName() { return projectStrategyName; }
    public void setProjectStrategyName(String projectStrategyName) { this.projectStrategyName = projectStrategyName; }

    public List<Project> getProjects() { return projects; }
    public void setProjects(List<Project> projects) { this.projects = projects; }
}
