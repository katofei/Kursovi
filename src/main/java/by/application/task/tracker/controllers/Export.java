package by.application.task.tracker.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class Export {

    @RequestMapping(value = "/download", method = RequestMethod.GET)
    public String download(Model model) {
        return "";
    }
}
