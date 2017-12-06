package by.application.task.tracker.service;

import by.application.task.tracker.data.entities.Location;

import java.util.List;

public interface LocationService {

    Location findByLocationId(Long locationId);
    List<Location> getAllUserLocations();
}
