package by.application.task.tracker.controllers;

import by.application.task.tracker.data.dto.UserDTO;
import by.application.task.tracker.data.entities.User;
import by.application.task.tracker.service.ProjectService;
import by.application.task.tracker.service.UserService;
import by.application.task.tracker.service.UserStatusService;
import by.application.task.tracker.service.impl.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
public class AdminController {

    private final UserService userService;
    private final ProjectService projectService;
    private final UserStatusService userStatusService;
    private final EmailService emailService;

    @Autowired
    public AdminController(UserService userService, ProjectService projectService,
                           UserStatusService userStatusService, EmailService emailService) {
        this.userService = userService;
        this.projectService = projectService;
        this.userStatusService = userStatusService;
        this.emailService = emailService;
    }

    @RequestMapping(path = "/adminPage", method = RequestMethod.GET)
    public ModelAndView getAdminStartPage() {
        ModelAndView view = new ModelAndView("adminStartPage");
        User currentUser = userService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        view.addObject("currentUser", currentUser);
        view.addObject("position", currentUser.getPosition());
        view.addObject("project", currentUser.getProject());
        view.addObject("qualification", currentUser.getQualification());
        return view;
    }


    @RequestMapping(path = "assign/{userId}", method = RequestMethod.POST)
    public ModelAndView assignUser(@Valid @ModelAttribute("user") UserDTO userDTO, BindingResult result, @PathVariable("userId") long userId) {
        User currentUser = userService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        User userForAssign = userService.findUserById(userId);
        ModelAndView view = new ModelAndView("userAssign");
        /* getCurrentUser(userService, view);*/
        if (result.hasErrors()) {
            view.setViewName("userAssign");
            return view;
        }
        view.setViewName("redirect:project/{id}/profile/{userId}");
        return view;
    }

    @RequestMapping(path = "/allUsers", method = RequestMethod.GET)
    public ModelAndView getAllUsers(@Valid @ModelAttribute("userDTO") UserDTO userDTO, BindingResult result) {
        ModelAndView view = new ModelAndView("allUsersPage");
        List<User> userList = userService.getAllUsers();
        User currentUser = userService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        view.addObject("userDTO", userDTO);
        view.addObject("currentUser", currentUser);
        view.addObject("position", currentUser.getPosition());
        view.addObject("project", currentUser.getProject());
        view.addObject("qualification", currentUser.getQualification());
        view.addObject("userList", userList);
        view.addObject("projectList", projectService.getAllProjects());
        return view;
    }

    @RequestMapping(path = "/homePage", method = RequestMethod.GET)
    public ModelAndView getHomePage() {
        return new ModelAndView("homePage");
    }
}
