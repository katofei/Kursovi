package by.application.task.tracker.data.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "streets")
public class Street {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "street_id", length = 5, nullable = false)
    private Long streetId;

    @Column(name = "street_name")
    private String streetName;

    @ManyToOne
    private City city;

    @OneToMany(mappedBy = "street", fetch = FetchType.LAZY)
    private List<House> houseList;

    @OneToMany(mappedBy = "street")
    private List<Location> locations;

    public Long getStreetId() {
        return streetId;
    }
    public void setStreetId(Long streetId) {
        this.streetId = streetId;
    }

    public String getStreetName() {
        return streetName;
    }
    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public City getCity() {
        return city;
    }
    public void setCity(City city) {
        this.city = city;
    }

    public List<House> getHouseList() {
        return houseList;
    }
    public void setHouseList(List<House> houseList) {
        this.houseList = houseList;
    }
}
