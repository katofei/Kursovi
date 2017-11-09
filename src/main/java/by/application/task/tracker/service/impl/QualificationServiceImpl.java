package by.application.task.tracker.service.impl;

import by.application.task.tracker.data.entities.Qualification;
import by.application.task.tracker.repositories.QualificationRepository;
import by.application.task.tracker.service.QualificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QualificationServiceImpl implements QualificationService {

    @Autowired
    private QualificationRepository qualificationRepository;

    @Override
    public Qualification findQualificationById(Long qualificationId) {
        return qualificationRepository.findOne(qualificationId);
    }

    @Override
    public List<Qualification> getAllQualifications() {
        List<Qualification> qualifications = new ArrayList<>();
        qualificationRepository.findAll().forEach(qualifications::add);
        return qualifications;
    }

    @Override
    public Qualification findByQualificationName(String qualificationName) {
        return qualificationRepository.findByQualificationName(qualificationName) ;
    }
}
