package by.application.task.tracker.data.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "cities")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "city_id", length = 5, nullable = false)
    private Long cityId;

    @Column(name = "city_name")
    private String cityName;

    @ManyToOne
    private Country country;

    @OneToMany(mappedBy = "city", fetch = FetchType.LAZY)
    private List<Street> streetList;

    @OneToMany(mappedBy = "city")
    private List<Location> locations;

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public List<Street> getStreetList() {
        return streetList;
    }

    public void setStreetList(List<Street> streetList) {
        this.streetList = streetList;
    }
}
