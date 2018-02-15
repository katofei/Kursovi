package by.application.task.tracker.service.impl;

import by.application.task.tracker.data.entities.Position;
import by.application.task.tracker.repositories.PositionRepository;
import by.application.task.tracker.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PositionServiceImpl implements PositionService {

    @Autowired
    private PositionRepository positionRepository;


    @Override
    public Position findPositionById(long positionId) {
        return positionRepository.findOne(positionId);
    }

    @Override
    public List<Position> getAllPositions() {
        List<Position> positions = new ArrayList<>();
        positionRepository.findAll().forEach(positions::add);
        return positions;
    }

    @Override
    public Position findByPositionName(String positionName) {
        return positionRepository.findByPositionName(positionName);
    }
}
