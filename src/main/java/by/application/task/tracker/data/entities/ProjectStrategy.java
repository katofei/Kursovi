/*
package by.application.task.tracker.data.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "project_strategies")
public class ProjectStrategy {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "strategy_id", length = 5, nullable = false)
    private Long strategyId;

    @Column(name = "strategy_name")
    private String strategyName;

    @OneToMany(mappedBy = "projectStrategy", fetch = FetchType.LAZY)
    private List<Project> projectList;

    public Long getStrategyId() {
        return strategyId;
    }

    public void setStrategyId(Long strategyId) {
        this.strategyId = strategyId;
    }

    public String getStrategyName() {
        return strategyName;
    }

    public void setStrategyName(String strategyName) {
        this.strategyName = strategyName;
    }

    public List<Project> getProjectList() {
        return projectList;
    }

    public void setProjectList(List<Project> projectList) {
        this.projectList = projectList;
    }
}
*/
