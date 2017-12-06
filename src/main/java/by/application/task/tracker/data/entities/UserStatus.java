package by.application.task.tracker.data.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user_status")
public class UserStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "status_id", length = 5, nullable = false)
    private Long statusId;

    @Column(name = "status_name")
    private String statusName;

    @OneToMany(mappedBy = "userStatus", fetch = FetchType.LAZY)
    private List<User> users;

    public Long getStatusId() {return statusId;}

    public void setStatusId(Long statusId) {this.statusId = statusId;}

    public String getStatusName() {return statusName;}

    public void setStatusName(String statusName) {this.statusName = statusName;}

    public List<User> getUsers() {return users;}

    public void setUsers(List<User> users) {this.users = users;}
}
