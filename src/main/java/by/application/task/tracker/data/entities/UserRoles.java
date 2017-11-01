package by.application.task.tracker.data.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "roles")
public class UserRoles implements Serializable {

    public UserRoles(){}

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "role_id", length = 5, nullable = false)
    private Long roleId;

    @Column(name = "role_name")
    private String roleName;

    @OneToMany(mappedBy = "userRole", fetch = FetchType.LAZY)
    private List<User> users;

    public Long getRoleId() {return roleId;}

    public void setRoleId(Long roleId) {this.roleId = roleId;}

    public String getRoleName() {return roleName;}

    public void setRoleName(String roleName) {this.roleName = roleName;}

    public List<User> getUsers() {return users;}

    public void setUsers(List<User> users) {this.users = users;}

}
