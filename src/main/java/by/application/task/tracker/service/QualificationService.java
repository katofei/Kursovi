package by.application.task.tracker.service;

import by.application.task.tracker.data.entities.Qualification;

import java.util.List;

public interface QualificationService {

    Qualification findQualificationById(long qualificationId);
    List<Qualification> getAllQualifications();
    Qualification findByQualificationName(String qualificationName);
}
