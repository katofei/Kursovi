package by.application.task.tracker.controllers.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AuthorizationController{

    @RequestMapping(value = {"/", "/login"}, method = RequestMethod.GET)
    public ModelAndView getHomePage() {
        return new ModelAndView("authorization");
    }

    @RequestMapping(path = "/accessDenied", method = RequestMethod.GET)
    public ModelAndView getDeniedPage() {
        return new ModelAndView("accessDenied");
    }
}

