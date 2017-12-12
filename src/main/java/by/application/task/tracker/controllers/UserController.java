package by.application.task.tracker.controllers;

import by.application.task.tracker.data.entities.Task;
import by.application.task.tracker.data.entities.User;
import by.application.task.tracker.data.wrapper.UserInfoWrapper;
import by.application.task.tracker.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

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
    private TaskStatusService taskStatusService;
    @Autowired
    private TaskPriorityService taskPriorityService;
    @Autowired
    private ProjectService projectService;
    @Autowired
    private UserContactService userContactService;
    /*@Autowired
    private UserStatusService userStatusService;
    @Autowired
    private ProjectContactService projectContactService;*/
    @Autowired
    private TaskService taskService;

    @RequestMapping(path = "/userPage", method = RequestMethod.GET)
    public ModelAndView getUserStartPage() {
        ModelAndView view = new ModelAndView("userStartPage");
        User currentUser = userService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        view.addObject("currentUser", currentUser);
        view.addObject("position", currentUser.getPosition());
        view.addObject("project", currentUser.getProject());
        view.addObject("qualification", currentUser.getQualification());
        return view;
    }

    @RequestMapping(path = "/allUsers", method = RequestMethod.GET)
    public ModelAndView getAllUsers() {
        ModelAndView view = new ModelAndView("allUsersPage");
        User currentUser = userService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        view.addObject("currentUser", currentUser);
        view.addObject("position", currentUser.getPosition());
        view.addObject("project", currentUser.getProject());
        view.addObject("qualification", currentUser.getQualification());

        List<User> userList = userService.getAllUsers();
        view.addObject("userList", userList);
        return view;
    }

    @RequestMapping(value = "/profile/{id}", method = RequestMethod.GET)
    public ModelAndView getUser(@PathVariable("id") long id) {
        ModelAndView view = new ModelAndView("profile");
        User currentUser = userService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        view.addObject("currentUser", currentUser);
        view.addObject("position", currentUser.getPosition());
        view.addObject("project", currentUser.getProject());
        view.addObject("qualification", currentUser.getQualification());

        User user = userService.findUserById(id);
        view.addObject("user", user);
        view.addObject("userPosition", user.getPosition());
        view.addObject("userQualification", user.getQualification());
        view.addObject("projectRole", user.getProjectRole());

        view.addObject("userContact", user.getUserContact());

        return view;
    }


    @RequestMapping(value = "/user-deletion/{id}", method = RequestMethod.DELETE)
    public ModelAndView deleteUser(@PathVariable("id") long id) {
        ModelAndView view = new ModelAndView();
        User currentUser = userService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        view.addObject("currentUser", currentUser);
        view.addObject("position", currentUser.getPosition());
        view.addObject("project", currentUser.getProject());
        view.addObject("qualification", currentUser.getQualification());

        userService.deleteUser(id);
        view.setViewName("redirect:/allUsers");
        return view;
    }

    @RequestMapping(value = "/user-deletion/{id}", method = RequestMethod.GET)
    public ModelAndView getUserDeletion(@PathVariable("id") long id) {
        ModelAndView view = new ModelAndView("allUsers");
        User currentUser = userService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        view.addObject("currentUser", currentUser);
        view.addObject("position", currentUser.getPosition());
        view.addObject("project", currentUser.getProject());
        view.addObject("qualification", currentUser.getQualification());

        userService.deleteUser(id);
        view.setViewName("redirect:/allUsers");
        return view;
    }

    @RequestMapping(value = "/user-edition/{id}", method = RequestMethod.GET)
    public ModelAndView getUserEdition(@PathVariable("id") long id) {
        ModelAndView view = new ModelAndView("editUserPage");
        User currentUser = userService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        view.addObject("currentUser", currentUser);
        view.addObject("position", currentUser.getPosition());
        view.addObject("project", currentUser.getProject());
        view.addObject("qualification", currentUser.getQualification());

        User user = userService.findUserById(id);
        UserInfoWrapper userInfoWrapper = new UserInfoWrapper(user, user.getUserContact());
        view.addObject("user", userInfoWrapper);

        view.addObject("positions", positionService.getAllPositions());
        view.addObject("projects", projectService.getAllProjects());
        view.addObject("qualifications", qualificationService.getAllQualifications());
        view.addObject("projectRoles", projectRoleService.getAllProjectRoles());
        return view;
    }

    @RequestMapping(value = "/user-edition/{id}", method = RequestMethod.POST)
    public ModelAndView editUser(@Valid @ModelAttribute("user") UserInfoWrapper userInfoWrapper, BindingResult result, @PathVariable("id") long id) {
        ModelAndView view = new ModelAndView();
        User user = userService.findUserById(id);
        if (result.hasErrors()) {
            User currentUser = userService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
            view.setViewName("editUserPage");
            view.addObject("currentUser", currentUser);
            view.addObject("qualification", currentUser.getQualification());
            view.addObject("position", currentUser.getPosition());

            view.addObject("positions", positionService.getAllPositions());
            view.addObject("projects", projectService.getAllProjects());
            view.addObject("qualifications", qualificationService.getAllQualifications());
            view.addObject("projectRoles", projectRoleService.getAllProjectRoles());

            view.addObject("userContact", user.getUserContact());
            return view;
        }
        view.setViewName("redirect:/allUsers");
        editUser(userInfoWrapper);
        return view;
    }


    @RequestMapping(value = "/profile/{id}/my-tasks", method = RequestMethod.GET)
    public ModelAndView getMyTasks(@PathVariable("id") long id) {
        ModelAndView view = new ModelAndView("myTasks");
        User currentUser = userService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        view.addObject("currentUser", currentUser);
        view.addObject("position", currentUser.getPosition());
        view.addObject("project", currentUser.getProject());
        view.addObject("qualification", currentUser.getQualification());

        view.addObject("taskPriorities", taskPriorityService.getAllTaskPriorities());
        view.addObject("taskStatuses", taskStatusService.getAllTaskStatuses());
        List<Task> taskList = taskService.getAllTasks();
        view.addObject("taskList", taskList);
        return view;
    }

    @RequestMapping(value = "/profile/{id}/my-team", method = RequestMethod.GET)
    public ModelAndView getMyTeamPage(@PathVariable("id") long id) {
        ModelAndView view = new ModelAndView("allUsersPage");
        User currentUser = userService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        view.addObject("currentUser", currentUser);
        view.addObject("position", currentUser.getPosition());
        view.addObject("project", currentUser.getProject());
        view.addObject("qualification", currentUser.getQualification());

        List<User> userList= userService.getAllUsers().stream().filter(user -> currentUser.getProject() == user.getProject()).collect(Collectors.toList());
        view.addObject("userList", userList);

        return view;
    }


    private void editUser(UserInfoWrapper userInfoWrapper) {
        userContactService.editContact(userInfoWrapper);
        userService.editUser(userInfoWrapper);
    }

    //TODO need to add function for mappings :
    //  /profile/${currentUser.userId}/team - use allUsersPage, + filter by project

    //  /profile/${currentUser.userId}/my-tasks - use "myTasks" page, + filter by user ( as creator and as executor)
    //  this stuff started

    //  /profile/${currentUser.userId}/team-statistics - use page with name "userStatistics"
    //  /profile/${currentUser.userId}/team-statistics - use page with name "teamStatistics"

}
