package by.application.task.tracker.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AuthorizationController{

    @RequestMapping(value = {"/", "/login"}, method = RequestMethod.GET)
    public ModelAndView getLoginPage(@RequestParam(value = "error", required = false) String error,
                                     @RequestParam(value = "logout", required = false) String logout){
        ModelAndView model = new ModelAndView("authorization");

        if (error != null) {
            model.addObject("error", "Bad credentials");
        }
        if (logout != null) {
            model.addObject("logout", "Logged out successfully.");
        }

        model.setViewName("authorization");
        return model;
    }

    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logoutPage () {
        return "redirect:authorization?logout=true";
    }

    @RequestMapping(path = "/accessDenied", method = RequestMethod.GET)
    public ModelAndView getDeniedPage() {
        return new ModelAndView("accessDenied");
    }

}

