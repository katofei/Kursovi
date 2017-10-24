package by.application.task.tracker.controllers.rest;

import by.application.task.tracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PageController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = {"/", "/authorization"}, method = RequestMethod.GET)
    public ModelAndView getHomePage() {
        return new ModelAndView("authorization");
    }

    @RequestMapping(path = "/starter", method = RequestMethod.GET)
    public ModelAndView getStarterPage() {
        ModelAndView view = new ModelAndView("starter");
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String username = loggedInUser.getName();
        //view.addObject("user", userService.findUserByName(userCredentials.getUsername()));
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

}
