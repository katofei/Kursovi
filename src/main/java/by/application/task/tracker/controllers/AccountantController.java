package by.application.task.tracker.controllers;

import by.application.task.tracker.data.entities.User;
import by.application.task.tracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class AccountantController {

    private final UserService userService;

    @Autowired
    public AccountantController(UserService userService) {
        this.userService = userService;
    }


    @RequestMapping(path = "/accountantPage", method = RequestMethod.GET)
    public ModelAndView getAccountantPage() {
        ModelAndView view = new ModelAndView("exportLogReport");
        User currentUser = userService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        view.addObject("currentUser", currentUser);
        view.addObject("position", currentUser.getPosition());
        view.addObject("project", currentUser.getProject());
        view.addObject("qualification", currentUser.getQualification());
        return view;
    }

    @RequestMapping(path = "/all-Users-Report", method = RequestMethod.GET)
    public ModelAndView getAllUsersReport() {
        ModelAndView view = new ModelAndView("allUsersReport");
        User currentUser = userService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        view.addObject("currentUser", currentUser);
        view.addObject("position", currentUser.getPosition());
        view.addObject("project", currentUser.getProject());
        view.addObject("qualification", currentUser.getQualification());

        List<User> userList = userService.getAllUsers();
        view.addObject("userList", userList);

        return view;
    }

    @RequestMapping(path = "/user-report", method = RequestMethod.GET)
    public ModelAndView getOneUserReport(@RequestParam(value = "userId", required = false) long userId) {
        ModelAndView view = new ModelAndView("allUsersReport");
        User currentUser = userService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        view.addObject("currentUser", currentUser);
        view.addObject("position", currentUser.getPosition());
        view.addObject("project", currentUser.getProject());
        view.addObject("qualification", currentUser.getQualification());

        List<User> userList = userService.getAllUsers();
        view.addObject("userList", userList);

        User user = userService.findUserById(userId);
        view.addObject("user", user);

        return view;
    }
}
