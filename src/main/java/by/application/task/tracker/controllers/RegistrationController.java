package by.application.task.tracker.controllers;


import by.application.task.tracker.data.dto.UserDTO;
import by.application.task.tracker.data.entities.User;
import by.application.task.tracker.service.*;
import by.application.task.tracker.validation.exception.WorkEmailExistsException;
import by.application.task.tracker.validation.exception.LoginExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;
    @Autowired
    private ProjectService projectService;
    @Autowired
    private PositionService positionService;
    @Autowired
    private QualificationService qualificationService;
    @Autowired
    private ProjectRoleService projectRoleService;

    @RequestMapping(path = "/registration", method = RequestMethod.GET)
    public ModelAndView getRegistrationPage() {
        ModelAndView view = new ModelAndView("registration");
        User currentUser = userService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        view.addObject("currentUser", currentUser);
        view.addObject("position",positionService.findPositionById(currentUser.getPosition().getPositionId()));
        view.addObject("project", projectService.findByProjectId(currentUser.getProject().getProjectId()));
        view.addObject("qualification",qualificationService.findQualificationById(currentUser.getQualification()
                .getQualificationId()));

        view.addObject("positions",positionService.getAllPositions());
        view.addObject("projects", projectService.getAllProjects());
        view.addObject("qualifications",qualificationService.getAllQualifications());
        view.addObject("projectRoles", projectRoleService.getAllProjectRoles());
        UserDTO userForCreation = new UserDTO();
        view.addObject("user", userForCreation);
        return view;
    }

    @RequestMapping(path = "/registration", method = RequestMethod.POST)
    public ModelAndView registerUser(@Valid @ModelAttribute("user") UserDTO userDTO, BindingResult result) {
        ModelAndView view = new ModelAndView();
        User currentUser = userService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());

        createUserAccount(userDTO, result);

        if (result.hasErrors()) {
            view.setViewName("registration");
            view.addObject("currentUser", currentUser);
            view.addObject("position",positionService.findPositionById(currentUser.getPosition().getPositionId()));
            view.addObject("qualification",qualificationService.findQualificationById(currentUser.getQualification()
                    .getQualificationId()));
            view.addObject("positions",positionService.getAllPositions());
            view.addObject("projects", projectService.getAllProjects());
            view.addObject("qualifications",qualificationService.getAllQualifications());
            view.addObject("projectRoles", projectRoleService.getAllProjectRoles());
            return view;
        }

        view.setViewName("redirect:/allUsers");
        return view;
    }

    private void createUserAccount(UserDTO userDto, BindingResult result) {
        try {
            userService.createUser(userDto);
        } catch (LoginExistsException e) {
            result.rejectValue("login", "message", "Username already exists");
        } catch (WorkEmailExistsException e) {
            result.rejectValue("workEmail", "message", "Email already exists");
        }
    }
}
