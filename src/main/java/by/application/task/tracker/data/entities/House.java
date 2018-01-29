package by.application.task.tracker.data.entities;

import javax.persistence.*;

@Entity
@Table(name = "houses")
public class House {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "house_id", length = 5, nullable = false)
    private Long houseId;

    @Column(name = "house_number")
    private int houseNumber;

    @ManyToOne
    private Street street;

    public Long getHouseId() {
        return houseId;
    }

    public void setHouseId(Long houseId) {
        this.houseId = houseId;
    }

    public int getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(int houseNumber) {
        this.houseNumber = houseNumber;
    }
}
