package by.application.task.tracker.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.activation.DataSource;

@Controller
public class SQLExceptionHandler extends MainExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(SQLExceptionHandler.class);

    @Autowired
    private DataSource dataSource;

}
