package com.application.task_tracker.data.entities.Enums;

import javax.persistence.Entity;

@Entity
public enum TaskPriorityEnum {

    LOW,
    NORMAL,
    CRITICAL,
    MAJOR,
    MINOR,
    DEFECT;

    TaskPriorityEnum(){}
}
