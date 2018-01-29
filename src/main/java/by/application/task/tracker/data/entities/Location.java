package by.application.task.tracker.data.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "location")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "location_id", length = 5, nullable = false)
    private Long locationId;

    @ManyToOne
    private Country country;

    @ManyToOne
    private City city;

    @ManyToOne
    private Street street;

    @ManyToOne
    private House house;

    @OneToMany(mappedBy ="location")
    private List<UserContact> userContacts;

    @OneToMany(mappedBy ="location")
    private List<ProjectContact> projectContacts;

    public Long getLocationId() {return locationId;}
    public void setLocationId(Long locationId) {this.locationId = locationId;}

    public Country getCountry() {return country;}
    public void setCountry(Country country) {this.country = country;}

    public City getCity() {return city;}
    public void setCity(City city) {this.city = city;}

    public Street getStreet() {return street;}
    public void setStreet(Street street) {this.street = street;}

    public House getHouse() {return house;}
    public void setHouse(House house) {this.house = house;}

    public List<UserContact> getUserContacts() {return userContacts;}
    public void setUserContacts(List<UserContact> userContacts) {this.userContacts = userContacts;}

    public List<ProjectContact> getProjectContacts() {return projectContacts;}
    public void setProjectContacts(List<ProjectContact> projectContacts) {this.projectContacts = projectContacts;}
}
