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
public class HomeController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = {"/","/authorization"}, method = RequestMethod.GET)
    public ModelAndView getHomePage()
    {        return new ModelAndView("authorization");
    }

    @RequestMapping(path = "/starter", method = RequestMethod.GET)
    public ModelAndView index(){
        ModelAndView view = new ModelAndView("starter");
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String username = loggedInUser.getName();
//        view.addObject("user", userService.findUserByName(userCredentials.getUsername()));
        return view;
    }

/*    @RequestMapping(path = "/access-denied", method = RequestMethod.GET)
    public ModelAndView accessDenied(){
        return new ModelAndView("access-denied");
    }

    @RequestMapping(path = "/error", method = RequestMethod.GET)
    public ModelAndView error(){
        return new ModelAndView("error");
    }*/
}
