package by.application.task.tracker.data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "project_roles")
public class ProjectRole {

    public ProjectRole(){}

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "project_role_id", length = 5, nullable = false)
    private Long positionId;

    @Column(name = "project_role_name")
    private String positionName;

    @OneToMany(mappedBy = "projectRole", fetch = FetchType.LAZY)
    private List<User> users;

    public Long getPositionId() {return positionId;}

    public void setPositionId(Long positionId) {this.positionId = positionId;}

    public String getPositionName() {return positionName;}

    public void setPositionName(String positionName) {this.positionName = positionName;}

    public List<User> getUsers() {return users;}

    public void setUsers(List<User> users) {this.users = users;}

    @Override
    public String toString() {
        return "ProjectRole{" +
                "positionId=" + positionId +
                ", positionName='" + positionName + '\'' +
                ", users=" + users +
                '}';
    }
}
