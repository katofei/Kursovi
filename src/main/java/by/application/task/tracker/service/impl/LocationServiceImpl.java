package by.application.task.tracker.service.impl;

import by.application.task.tracker.data.entities.Location;
import by.application.task.tracker.repositories.LocationRepository;
import by.application.task.tracker.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LocationServiceImpl implements LocationService {

    @Autowired
    private LocationRepository locationRepository;


    @Override
    public Location findByLocationId(Long locationId) {
        return locationRepository.findOne(locationId);
    }

    @Override
    public List<Location> getAllUserLocations() {
        List<Location> locations = new ArrayList<>();
        locationRepository.findAll().forEach(locations::add);
        return locations;
    }
}
