package by.application.task.tracker.service;


import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Service
public class DataConverterService {

    private static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MMM-dd HH:mm:ss");

    public Date convertStringToDate(String date) throws ParseException {
        return formatter.parse(date);
    }

    public LocalDate convertDateToLocal(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public String generateTodayStringDay() {
        Date today = new Date();
        return today.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
                .format(DateTimeFormatter.ISO_DATE);
    }

    public Date generateTodayDateDay() throws ParseException {
        Date today = new Date();
        return formatter.parse(formatter.format(today));
    }
}
