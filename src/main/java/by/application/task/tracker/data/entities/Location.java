package by.application.task.tracker.data.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "location")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "location_id", length = 5, nullable = false)
    private Long location_id;

    @Column(name = "country")
    private String country;

    @Column(name = "city")
    private String city;

    @Column(name="street")
    private String street;

    @Column(name = "house_number")
    private int houseNumber;

    @OneToMany(mappedBy = "userLocation", fetch = FetchType.LAZY)
    private List<UserContact> userLocationList;

    @OneToMany(mappedBy = "projectLocation", fetch = FetchType.LAZY)
    private List<ProjectContact> projectLocationList;

    public Long getLocation_id() {return location_id;}

    public void setLocation_id(Long location_id) {this.location_id = location_id;}

    public String getCountry() {return country;}

    public void setCountry(String country) {this.country = country;}

    public String getCity() {return city;}

    public void setCity(String city) {this.city = city;}

    public String getStreet() {return street;}

    public void setStreet(String street) {this.street = street;}

    public int getHouseNumber() {return houseNumber;}

    public void setHouseNumber(int houseNumber) {this.houseNumber = houseNumber;}

    public List<UserContact> getUserLocationList() {
        return userLocationList;
    }

    public void setUserLocationList(List<UserContact> userLocationList) {this.userLocationList = userLocationList;}

    public List<ProjectContact> getProjectLocationList() {return projectLocationList;}

    public void setProjectLocationList(List<ProjectContact> projectLocationList) {this.projectLocationList = projectLocationList;}
}
