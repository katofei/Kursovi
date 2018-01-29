package by.application.task.tracker.data.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "countries")
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "country_id", length = 5, nullable = false)
    private Long countryId;

    @Column(name = "country_name")
    private String countryName;

    @OneToMany(mappedBy = "country", fetch = FetchType.LAZY)
    private List<City> cityList;

    @OneToMany(mappedBy = "country")
    private List<Location> locations;

    public Long getCountryId() {
        return countryId;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public List<City> getCityList() {
        return cityList;
    }

    public void setCityList(List<City> cityList) {
        this.cityList = cityList;
    }

}
