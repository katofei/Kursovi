package by.application.task.tracker.controllers;

import by.application.task.tracker.data.dto.UserDTO;
import by.application.task.tracker.data.entities.User;
import by.application.task.tracker.service.*;
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
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private PositionService positionService;
    @Autowired
    private QualificationService qualificationService;
    @Autowired
    private ProjectRoleService projectRoleService;
    @Autowired
    private ProjectService projectService;
    @Autowired
    private UserContactService contactService;

    @RequestMapping(path = "/userPage", method = RequestMethod.GET)
    public ModelAndView getUserStartPage() {
        ModelAndView view = new ModelAndView("userStartPage");
        User currentUser = userService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        view.addObject("currentUser", currentUser);
        view.addObject("position", positionService.findPositionById(currentUser.getPosition().getPositionId()));
        view.addObject("project", projectService.findByProjectId(currentUser.getProject().getProjectId()));
        view.addObject("qualification", qualificationService.findQualificationById(currentUser.getQualification()
                .getQualificationId()));
        return view;
    }

    @RequestMapping(path = "/allUsers", method = RequestMethod.GET)
    public ModelAndView getAllUsers() {
        ModelAndView view = new ModelAndView("allUsersPage");
        User currentUser = userService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        view.addObject("currentUser", currentUser);
        view.addObject("position", positionService.findPositionById(currentUser.getPosition().getPositionId()));
        view.addObject("project", projectService.findByProjectId(currentUser.getProject().getProjectId()));
        view.addObject("qualification", qualificationService.findQualificationById(currentUser.getQualification()
                .getQualificationId()));


        List<User> userList = userService.getAllUsers();
        view.addObject("userList", userList);
        return view;
    }

    @RequestMapping(value = "/profile/{id}", method = RequestMethod.GET)
    public ModelAndView getUser(@PathVariable("id") long id) {
        ModelAndView view = new ModelAndView("profile");
        User currentUser = userService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        view.addObject("currentUser", currentUser);
        view.addObject("position", positionService.findPositionById(currentUser.getPosition().getPositionId()));
        view.addObject("project", projectService.findByProjectId(currentUser.getProject().getProjectId()));
        view.addObject("qualification", qualificationService.findQualificationById(currentUser.getQualification()
                .getQualificationId()));

        view.addObject("user", userService.findUserById(id));
        view.addObject("userPosition", positionService.findPositionById(userService.findUserById(id)
                .getPosition().getPositionId()));
        view.addObject("userQualification", qualificationService.findQualificationById(userService.findUserById(id)
                .getQualification().getQualificationId()));
        view.addObject("projectRole", projectRoleService.findProjectRoleById(userService.findUserById(id)
                .getProjectRole().getProjectRoleId()));
        return view;
    }


    @RequestMapping(value = "/user-deletion/{id}", method = RequestMethod.DELETE)
    public ModelAndView deleteUserProfile(@PathVariable("id") long id) {
        ModelAndView view = new ModelAndView("profile");
        User currentUser = userService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        view.addObject("currentUser", currentUser);
        view.addObject("position", positionService.findPositionById(currentUser.getPosition().getPositionId()));
        view.addObject("project", projectService.findByProjectId(currentUser.getProject().getProjectId()));
        view.addObject("qualification", qualificationService.findQualificationById(currentUser.getQualification().getQualificationId()));

        userService.deleteUser(id);
        return new ModelAndView("allUsers");
    }

    @RequestMapping(value = "/user-edition/{id}", method = RequestMethod.GET)
    public ModelAndView getUserEdition(@PathVariable("id") long id) {
        ModelAndView view = new ModelAndView("editUserPage");
        User currentUser = userService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        view.addObject("currentUser", currentUser);
        view.addObject("position", positionService.findPositionById(currentUser.getPosition().getPositionId()));
        view.addObject("project", projectService.findByProjectId(currentUser.getProject().getProjectId()));
        view.addObject("qualification", qualificationService.findQualificationById(currentUser.getQualification().getQualificationId()));

        User user = userService.findUserById(id);
        view.addObject("positions",positionService.getAllPositions());
        view.addObject("projects", projectService.getAllProjects());
        view.addObject("qualifications",qualificationService.getAllQualifications());
        view.addObject("projectRoles", projectRoleService.getAllProjectRoles());
        view.addObject("userContact", contactService.findByContactId(user.getUserContact().getContacId()));
        view.addObject("user", user);
        view.addObject("userDTO", new UserDTO());
        return view;
    }

    @RequestMapping(value = "/user-edition/{id}", method = RequestMethod.POST)
    public ModelAndView editUser(@Valid @ModelAttribute("user") UserDTO userDTO, BindingResult result, @PathVariable("id") long id) {
        ModelAndView view = new ModelAndView();
        if (result.hasErrors()) {
            User currentUser = userService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
            view.setViewName("editUserPage");{
                view.addObject("currentUser", currentUser);
                view.addObject("position", positionService.findPositionById(currentUser.getPosition().getPositionId()));
                view.addObject("project", projectService.findByProjectId(currentUser.getProject().getProjectId()));
                view.addObject("qualification", qualificationService.findQualificationById(currentUser.getQualification()
                        .getQualificationId()));

                view.addObject("positions",positionService.getAllPositions());
                view.addObject("projects", projectService.getAllProjects());
                view.addObject("qualifications",qualificationService.getAllQualifications());
                view.addObject("projectRoles", projectRoleService.getAllProjectRoles());
              //  view.addObject("userContact", contactService.findByContactId(user.getUserContact().getContacId()));
                return view;
            }
        }
        view.setViewName("redirect:/allUsers");
        userService.editUser(userDTO, id);
        return view;
    }
}
