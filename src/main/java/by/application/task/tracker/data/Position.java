package by.application.task.tracker.data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "positions")
public class Position {

    public Position(){}

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "position_id", length = 5, nullable = false)
    private Long positionId;

    @Column(name = "position_name")
    private String positionName;

    @OneToMany(mappedBy = "position", fetch = FetchType.LAZY)
    private List<User> users;

    public Long getPositionId() {return positionId;}

    public void setPositionId(Long positionId) {this.positionId = positionId;}

    public String getPositionName() {return positionName;}

    public void setPositionName(String positionName) {this.positionName = positionName;}

    public List<User> getUsers() {return users;}

    public void setUsers(List<User> users) {this.users = users;}

    @Override
    public String toString() {
        return "Position{" +
                "positionId=" + positionId +
                ", positionName='" + positionName + '\'' +
                ", users=" + users +
                '}';
    }
}
