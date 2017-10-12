package com.application.task.tracker.data.entities.enums;

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
