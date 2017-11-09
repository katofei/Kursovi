package by.application.task.tracker.data.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "qualifications")
public class Qualification {

    public Qualification(){}

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "qualification_id", length = 5, nullable = false)
    private Long qualificationId;

    @Column(name = "qualification_name")
    private String qualificationName;

    @OneToMany(mappedBy = "qualification", fetch = FetchType.LAZY)
    private List<User> users;

    public Long getQualificationId() {
        return qualificationId;
    }

    public void setQualificationId(Long qualificationId) {
        this.qualificationId = qualificationId;
    }

    public String getQualificationName() {
        return qualificationName;
    }

    public void setQualificationName(String qualificationName) {
        this.qualificationName = qualificationName;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

}
