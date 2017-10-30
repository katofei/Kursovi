package by.application.task.tracker.controllers.rest;

import by.application.task.tracker.data.User;
import by.application.task.tracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PageNavigationController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = {"/", "/authorization"}, method = RequestMethod.GET)
    public ModelAndView getHomePage() {
        return new ModelAndView("authorization");
    }

    @RequestMapping(path = "/starter", method = RequestMethod.GET)
    public ModelAndView getStarterPage() {
        ModelAndView view = new ModelAndView("starter");
        User user = userService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        view.addObject("user", user);
        return view;
    }

    @RequestMapping(path = "/profile", method = RequestMethod.GET)
    public ModelAndView getProfilePage() {
        return new ModelAndView("profile");
    }

    @RequestMapping(path = "/task_creation", method = RequestMethod.GET)
    public ModelAndView getTaskCreationPage() {
        return new ModelAndView("task_creation");
    }

    @RequestMapping(path = "/registration", method = RequestMethod.GET)
    public ModelAndView getRegistrationPage() {
        return new ModelAndView("registration");
    }

    @RequestMapping(path = "/access-denied", method = RequestMethod.GET)
    public ModelAndView getDeniedPage() {
        return new ModelAndView("accessDenied");
    }

}