package by.application.task.tracker.xlsview.resolver;

import by.application.task.tracker.xlsview.ExcelView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;

import java.util.Locale;

public class ExcelViewResolver implements ViewResolver {
    @Override
    public View resolveViewName(String s, Locale locale) {
        return new ExcelView();
    }
}