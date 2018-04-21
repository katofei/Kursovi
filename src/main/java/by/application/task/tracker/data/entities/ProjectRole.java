package by.application.task.tracker.data.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "project_role")
public class ProjectRole {

    public ProjectRole(){}

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "project_role_id", length = 5, nullable = false)
    private Long projectRoleId;

    @Column(name = "project_role_name")
    private String projectRoleName;

    @OneToMany(mappedBy = "projectRole", fetch = FetchType.LAZY)
    private List<User> users;

    public Long getPositionId() {return projectRoleId;}
    public void setPositionId(Long projectRoleId) {this.projectRoleId = projectRoleId;}

    public String getPositionName() {return projectRoleName;}
    public void setPositionName(String positionName) {this.projectRoleName = positionName;}

    public List<User> getUsers() {return users;}
    public void setUsers(List<User> users) {this.users = users;}

    public Long getProjectRoleId() {return projectRoleId;}
    public void setProjectRoleId(Long projectRoleId) {this.projectRoleId = projectRoleId;}

    public String getProjectRoleName() {return projectRoleName;}
    public void setProjectRoleName(String projectRoleName) {this.projectRoleName = projectRoleName;}
}
