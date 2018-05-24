package by.application.task.tracker.controllers;

import by.application.task.tracker.Constants;
import by.application.task.tracker.data.dto.UserForTaskInfoDto;
import by.application.task.tracker.data.entities.Dashboard;
import by.application.task.tracker.data.entities.Task;
import by.application.task.tracker.data.entities.User;
import by.application.task.tracker.data.wrapper.UserInfoWrapper;
import by.application.task.tracker.service.*;
import by.application.task.tracker.service.impl.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static by.application.task.tracker.Constants.USER_DELETION_NOTIFICATION;

@Controller
public class UserController {

    private final UserService userService;
    private final PositionService positionService;
    private final QualificationService qualificationService;
    private final ProjectRoleService projectRoleService;
    private final TaskStatusService taskStatusService;
    private final TaskPriorityService taskPriorityService;
    private final ProjectService projectService;
    private final UserContactService userContactService;
    private final TaskService taskService;
    private final EmailService emailService;
    private final DashboardService dashboardService;


    @Autowired
    public UserController(QualificationService qualificationService, UserService userService,
                          PositionService positionService, ProjectRoleService projectRoleService,
                          TaskStatusService taskStatusService, TaskPriorityService taskPriorityService,
                          ProjectService projectService, UserContactService userContactService, TaskService taskService,
                          EmailService emailService, DashboardService dashboardService) {
        this.qualificationService = qualificationService;
        this.userService = userService;
        this.positionService = positionService;
        this.projectRoleService = projectRoleService;
        this.taskStatusService = taskStatusService;
        this.taskPriorityService = taskPriorityService;
        this.projectService = projectService;
        this.userContactService = userContactService;
        this.taskService = taskService;
        this.emailService = emailService;
        this.dashboardService = dashboardService;
    }

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

    @RequestMapping(path = "project/{id}/allUsers", method = RequestMethod.GET)
    public ModelAndView getAllUsers(@PathVariable("id") long projectId) {
        ModelAndView view = new ModelAndView("allUsersPage");
        User currentUser = userService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        view.addObject("currentUser", currentUser);
        view.addObject("position", currentUser.getPosition());
        view.addObject("project", currentUser.getProject());
        view.addObject("qualification", currentUser.getQualification());
        List<User> userList = userService.getAllUsers(projectId);
        view.addObject("userList", userList);
        view.addObject("projectList", projectService.getAllProjects());
        return view;
    }

    @RequestMapping(value = "/profile/{userId}", method = RequestMethod.GET)
    public ModelAndView getUser(@PathVariable("userId") long userId) {
        ModelAndView view = new ModelAndView("profile");
        User currentUser = userService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        view.addObject("currentUser", currentUser);
        view.addObject("position", currentUser.getPosition());
        view.addObject("project", currentUser.getProject());
        view.addObject("qualification", currentUser.getQualification());
        User user = userService.findUserById(userId);
        view.addObject("user", user);
        view.addObject("userPosition", user.getPosition());
        view.addObject("userQualification", user.getQualification());
        view.addObject("projectRole", user.getProjectRole());

        view.addObject("userContact", user.getUserContact());

        return view;
    }

    @RequestMapping(value = "/user-deletion/{userId}", method = RequestMethod.DELETE)
    public ModelAndView deleteUser(@PathVariable("userId") long userId) {
        ModelAndView view = new ModelAndView();
        SimpleMailMessage registrationEmail = new SimpleMailMessage();
        registrationEmail.setTo(userService.findUserById(userId).getUserContact().getWorkEmail());
        registrationEmail.setSubject(USER_DELETION_NOTIFICATION);
        registrationEmail.setText("Dear user, be informed that you was deleted from system:\n"
                + "You don't have permission to log-in anymore");
        registrationEmail.setFrom(Constants.from_email);
        emailService.sendEmail(registrationEmail);

        userService.deleteUser(userId);
        view.setViewName("redirect:/project/{id}/allUsers");
        return view;
    }

    @RequestMapping(value = "/user-deletion/{userId}", method = RequestMethod.GET)
    public ModelAndView getUserDeletion(@PathVariable("userId") long userId) {
        ModelAndView view = new ModelAndView("allUsers");
        /*getCurrentUser(userService, view);*/
        userService.deleteUser(userId);
        view.setViewName("redirect:/project/{id}/allUsers");
        return view;
    }

    @RequestMapping(value = "/user-edition/{userId}", method = RequestMethod.GET)
    public ModelAndView getUserEdition(@PathVariable("userId") long userId) {
        ModelAndView view = new ModelAndView("editUserPage");
        User currentUser = userService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        view.addObject("currentUser", currentUser);
        view.addObject("position", currentUser.getPosition());
        view.addObject("project", currentUser.getProject());
        view.addObject("qualification", currentUser.getQualification());

        User user = userService.findUserById(userId);
        UserInfoWrapper userInfoWrapper = new UserInfoWrapper(user, user.getUserContact());
        view.addObject("userInfoWrapper", userInfoWrapper);

        view.addObject("positions", positionService.getAllPositions());
        view.addObject("projects", projectService.getAllProjects());
        view.addObject("qualifications", qualificationService.getAllQualifications());
        view.addObject("projectRoles", projectRoleService.getAllProjectRoles());
        return view;
    }

    @RequestMapping(value = "/user-edition/{userId}", method = RequestMethod.POST)
    public String editUser(@Valid @ModelAttribute("userInfoWrapper") UserInfoWrapper userInfoWrapper,
                           BindingResult result, @PathVariable("userId") long userId) {
        ModelAndView view = new ModelAndView();
        User user = userService.findUserById(userId);
        if (result.hasErrors()) {
            User currentUser = userService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
            view.addObject("currentUser", currentUser);
            view.addObject("position", currentUser.getPosition());
            view.addObject("project", currentUser.getProject());
            view.addObject("qualification", currentUser.getQualification());
            view.addObject("positions", positionService.getAllPositions());
            view.addObject("projects", projectService.getAllProjects());
            view.addObject("qualifications", qualificationService.getAllQualifications());
            view.addObject("projectRoles", projectRoleService.getAllProjectRoles());

            view.addObject("userContact", user.getUserContact());
            return "redirect:/user-edition/" + userId;
        }
        editUser(userInfoWrapper);
        return "redirect:/allUsers";
    }

    @RequestMapping(value = "profile/{userId}/my-tasks", method = RequestMethod.GET)
    public ModelAndView getMyTasks(@PathVariable("userId") long userId) {
        ModelAndView view = new ModelAndView("myTasks");
        User currentUser = userService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        view.addObject("currentUser", currentUser);
        view.addObject("position", currentUser.getPosition());
        view.addObject("project", currentUser.getProject());
        view.addObject("qualification", currentUser.getQualification());
        view.addObject("taskPriorities", taskPriorityService.getAllTaskPriorities());
        view.addObject("taskStatuses", taskStatusService.getAllTaskStatuses());

        List<Task> allUserTasks = taskService.getAllUserTasks(userId);
        view.addObject("allUserTasks", allUserTasks);
        return view;
    }

    @RequestMapping(value = "profile/{userId}/my-team", method = RequestMethod.GET)
    public ModelAndView getMyTeamPage(@PathVariable("userId") long userId) {
        ModelAndView view = new ModelAndView("myTeam");
        User currentUser = userService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        view.addObject("currentUser", currentUser);
        view.addObject("position", currentUser.getPosition());
        view.addObject("project", currentUser.getProject());
        view.addObject("qualification", currentUser.getQualification());
        List<User> userList = userService.getAllUsers().stream().filter(user -> currentUser.getProject() ==
                user.getProject()).collect(Collectors.toList());
        view.addObject("userList", userList);

        return view;
    }

    @RequestMapping(value = "profile/{userId}/team-statistics", method = RequestMethod.GET)
    public ModelAndView getTeamStatisticsPage(@PathVariable("userId") long userId) {
        ModelAndView view = new ModelAndView("teamStatistics");
        User currentUser = userService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        view.addObject("currentUser", currentUser);
        view.addObject("position", currentUser.getPosition());
        view.addObject("project", currentUser.getProject());
        view.addObject("qualification", currentUser.getQualification());

        List<User> userList = userService.getAllUsers().stream().filter(user -> currentUser.getProject() ==
                user.getProject()).collect(Collectors.toList());
        List<UserForTaskInfoDto> userForTaskInfoDtos = userList.stream().map(user -> {
            UserForTaskInfoDto userForTaskInfoDto = new UserForTaskInfoDto();

            List<Dashboard> dashboardList = dashboardService.getAllUserDashboards(user.getUserId());
            List<Task> allUserTasks = taskService.getAllUserTasks(user.getUserId());

            userForTaskInfoDto.setUserName(user.getUserName());
            userForTaskInfoDto.setUserSurname(user.getUserSurname());
            userForTaskInfoDto.setImplementedCount(allUserTasks.stream().
                    filter(task -> task.getTaskStatus().getStatusName().equals(Constants.IMPLEMENTED)).count());
            userForTaskInfoDto.setAddInfoCount(allUserTasks.stream().
                    filter(task -> task.getTaskStatus().getStatusName().equals(Constants.NEED_INFO)).count());
            userForTaskInfoDto.setReadyCount(allUserTasks.stream().
                    filter(task -> task.getTaskStatus().getStatusName().equals(Constants.READY_FOR_TESTING)).count());
            userForTaskInfoDto.setReopen(allUserTasks.stream().
                    filter(task -> task.getTaskStatus().getStatusName().equals(Constants.REOPENED)).count());
            userForTaskInfoDto.setClosed(allUserTasks.stream().
                    filter(task -> task.getTaskStatus().getStatusName().equals(Constants.CLOSED)).count());
            userForTaskInfoDto.setOpen(allUserTasks.stream().
                    filter(task -> task.getTaskStatus().getStatusName().equals(Constants.OPEN)).count());



            userForTaskInfoDto.setDonHoldCount(dashboardList.stream().
                    filter(dashboard -> dashboard.getStatus().getStatusName().equals(Constants.ON_HOLD)).count());
            userForTaskInfoDto.setDinAnalysesCount(dashboardList.stream().
                    filter(dashboard -> dashboard.getStatus().getStatusName().equals(Constants.IN_ANALYSIS)).count());
            userForTaskInfoDto.setDinQACount(dashboardList.stream().
                    filter(dashboard -> dashboard.getStatus().getStatusName().equals(Constants.IN_QA)).count());
            userForTaskInfoDto.setDinBuildCount(dashboardList.stream().
                    filter(dashboard -> dashboard.getStatus().getStatusName().equals(Constants.IN_BUILD)).count());
            userForTaskInfoDto.setDinDesign(dashboardList.stream().
                    filter(dashboard -> dashboard.getStatus().getStatusName().equals(Constants.IN_DESIGN)).count());
            userForTaskInfoDto.setDopen(dashboardList.stream().
                    filter(dashboard -> dashboard.getStatus().getStatusName().equals(Constants.OPEN)).count());
            userForTaskInfoDto.setDimplementedCount(dashboardList.stream().
                    filter(dashboard -> dashboard.getStatus().getStatusName().equals(Constants.IMPLEMENTED)).count());
            userForTaskInfoDto.setDreopen(dashboardList.stream().
                    filter(dashboard -> dashboard.getStatus().getStatusName().equals(Constants.REOPENED)).count());
            return userForTaskInfoDto;
        }).collect(Collectors.toList());

        view.addObject("userForTaskInfoDtos",userForTaskInfoDtos);
        return view;
    }

    @RequestMapping(value = "profile/{userId}/user-statistics", method = RequestMethod.GET)
    public ModelAndView getUserStatisticsPage(@PathVariable("userId") long userId) {
        ModelAndView view = new ModelAndView("myStatistics");
        User currentUser = userService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        view.addObject("currentUser", currentUser);
        view.addObject("position", currentUser.getPosition());
        view.addObject("project", currentUser.getProject());
        view.addObject("qualification", currentUser.getQualification());

        List<Task> allUserTasks = taskService.getAllUserTasks(userId);
        long implementedCount = allUserTasks.stream().
                filter(task -> task.getTaskStatus().getStatusName().equals(Constants.IMPLEMENTED)).count();
        long addInfoCount = allUserTasks.stream().
                filter(task -> task.getTaskStatus().getStatusName().equals(Constants.NEED_INFO)).count();
        long readyCount = allUserTasks.stream().
                filter(task -> task.getTaskStatus().getStatusName().equals(Constants.READY_FOR_TESTING)).count();
        long reopen = allUserTasks.stream().
                filter(task -> task.getTaskStatus().getStatusName().equals(Constants.REOPENED)).count();
        long closed = allUserTasks.stream().
                filter(task -> task.getTaskStatus().getStatusName().equals(Constants.CLOSED)).count();
        long open = allUserTasks.stream().
                filter(task -> task.getTaskStatus().getStatusName().equals(Constants.OPEN)).count();
        Map<String, Long> tasksCountMap = new HashMap<>();
        tasksCountMap.put("implementedCount", implementedCount);
        tasksCountMap.put("addInfoCount", addInfoCount);
        tasksCountMap.put("readyCount", readyCount);
        tasksCountMap.put("reopen", reopen);
        tasksCountMap.put("closed", closed);
        tasksCountMap.put("open", open);

        //todo  draw statistics by diagramm..see userStatistics.html
        view.addAllObjects(tasksCountMap);
        return view;
    }

    private void editUser(UserInfoWrapper userInfoWrapper) {
        userContactService.editContact(userInfoWrapper);
        userService.editUser(userInfoWrapper);
    }
}
