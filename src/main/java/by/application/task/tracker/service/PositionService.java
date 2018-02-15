package by.application.task.tracker.service;

import by.application.task.tracker.data.entities.Position;

import java.util.List;


public interface PositionService {

    Position findPositionById(long positionId);
    List<Position> getAllPositions();
    Position findByPositionName(String positionName);
}
