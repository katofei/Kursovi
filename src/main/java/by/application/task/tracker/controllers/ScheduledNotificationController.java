package by.application.task.tracker.controllers;

import by.application.task.tracker.data.entities.Dashboard;
import by.application.task.tracker.data.entities.Project;
import by.application.task.tracker.data.entities.Task;
import by.application.task.tracker.data.entities.User;
import by.application.task.tracker.service.*;
import by.application.task.tracker.service.impl.DataConverterService;
import by.application.task.tracker.service.impl.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.time.Period;
import java.util.Date;
import java.util.List;

import static by.application.task.tracker.Constants.*;

@Component("scheduledNotifications")
public class ScheduledNotificationController {

    private final EmailService emailService;
    private final UserService userService;
    private final ProjectService projectService;
    private final TaskService taskService;
    private final DashboardService dashboardService;
    private final DataConverterService dataConverterService;
    private final UserStatusService userStatusService;

    @Autowired
    public ScheduledNotificationController(EmailService emailService, UserService userService,
                                           ProjectService projectService, TaskService taskService,
                                           DashboardService dashboardService, DataConverterService dataConverterService,
                                           UserStatusService userStatusService) {
        this.emailService = emailService;
        this.userService = userService;
        this.projectService = projectService;
        this.taskService = taskService;
        this.dashboardService = dashboardService;
        this.dataConverterService = dataConverterService;
        this.userStatusService = userStatusService;
    }

    @Scheduled(cron = "0 0 0 ? * MON-FRI")
    public void sendTaskNotification() {
        List<Task> allTasks = taskService.getAllTasks();
        allTasks.forEach((Task task) -> {
            if (!task.getDueDate().equals("Not specified")) {
                try {
                    Date estimation = dataConverterService.convertStringToDate(task.getDueDate());
                    Date today = dataConverterService.generateTodayDateDay();
                    if (today.before(estimation)) {
                        Period period = Period.between(dataConverterService.convertDateToLocal(today),
                                dataConverterService.convertDateToLocal(estimation));
                        if (period.getDays() < 5) {
                            SimpleMailMessage notificationEmail = new SimpleMailMessage();
                            notificationEmail.setSubject(TASK_DUEDATE_NOTIFICATION);
                            notificationEmail.setFrom(from_email);
                            notificationEmail.setSentDate(today);
                            notificationEmail.setText("Dear user, please be informed that task " + task.getTaskName()
                                    + " due date is to come in " + period.getDays() + " days");
                            notificationEmail.setTo(task.getExecutor().getUserContact().getWorkEmail());
                            emailService.sendEmail(notificationEmail);
                        }
                    } else if (today.equals(estimation)) {
                        SimpleMailMessage notificationEmail = new SimpleMailMessage();
                        notificationEmail.setSubject(TASK_ESTIMATION_NOTIFICATION);
                        notificationEmail.setFrom(from_email);
                        notificationEmail.setSentDate(today);
                        notificationEmail.setText("Dear user, please be informed that task " + task.getTaskName()
                                + " due date estimated");
                        notificationEmail.setTo(task.getExecutor().getUserContact().getWorkEmail());
                        emailService.sendEmail(notificationEmail);
                    }

                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Scheduled(cron = "0 0 0 ? * MON-FRI")
    public void sendProjectNotification() {
        List<Project> allProjects = projectService.getAllProjects();
        allProjects.forEach(project -> {
            if (!project.getDeadLine().equals("Not specified")) {
                try {
                    Date deadLine = dataConverterService.convertStringToDate(project.getDeadLine());
                    Date today = dataConverterService.generateTodayDateDay();
                    if (today.before(deadLine)) {
                        Period period = Period.between(dataConverterService.convertDateToLocal(today),
                                dataConverterService.convertDateToLocal(deadLine));
                        if (period.getDays() < 5) {
                            SimpleMailMessage notificationEmail = new SimpleMailMessage();
                            notificationEmail.setSubject(PROJECT_DEADLINE_NOTIFICATION);
                            notificationEmail.setFrom(from_email);
                            notificationEmail.setSentDate(today);
                            notificationEmail.setText("Dear user, please be informed that project "
                                    + project.getProjectName() + " is to end in " + period.getDays() + " days");
                            userService.getAllUsers(project.getProjectId()).forEach(user -> {
                                if (PROJECT_MANAGER.equals(user.getProjectRole().getProjectRoleName())
                                        || TEAM_LEAD.equals(user.getProjectRole().getProjectRoleName()))
                                    notificationEmail.setTo(user.getUserContact().getWorkEmail());
                            });
                            emailService.sendEmail(notificationEmail);
                        }
                    } else if (today.equals(deadLine)) {
                        SimpleMailMessage notificationEmail = new SimpleMailMessage();
                        notificationEmail.setSubject(PROJECT_DEADLINE_NOTIFICATION);
                        notificationEmail.setFrom(from_email);
                        notificationEmail.setSentDate(today);
                        notificationEmail.setText("Dear user, please be informed that project " + project.getProjectName() + " ended");
                        userService.getAllUsers(project.getProjectId()).forEach(user -> {
                            if (PROJECT_MANAGER.equals(user.getProjectRole().getProjectRoleName())
                                    || TEAM_LEAD.equals(user.getProjectRole().getProjectRoleName()))
                                notificationEmail.setTo(user.getUserContact().getWorkEmail());
                        });
                        emailService.sendEmail(notificationEmail);
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Scheduled(cron = "0 0 0 ? * MON-FRI")
    public void sendUserNotification() {
        List<User> allUsers = userService.getAllUsers();
        allUsers.forEach(user -> {
            try {
                Date estimation = dataConverterService.convertStringToDate(user.getEstimation());
                Date today = dataConverterService.generateTodayDateDay();
                if (today.before(estimation)) {
                    Period period = Period.between(dataConverterService.convertDateToLocal(today),
                            dataConverterService.convertDateToLocal(estimation));
                    if (period.getDays() < 5) {
                        SimpleMailMessage notificationEmail = new SimpleMailMessage();
                        notificationEmail.setSubject(USER_ASSIGN_NOTIFICATION);
                        notificationEmail.setFrom(from_email);
                        notificationEmail.setSentDate(today);
                        notificationEmail.setText("Dear user, please be informed that your project assigned is to end in "
                                + period.getDays()+ " days");
                        notificationEmail.setTo(user.getUserContact().getWorkEmail());
                        emailService.sendEmail(notificationEmail);
                    }
                } else if (today.equals(estimation)) {
                    user.setUserStatus(userStatusService.findByStatusName(USER_NOT_ASSIGNED));
                    SimpleMailMessage notificationEmail = new SimpleMailMessage();
                    notificationEmail.setSubject(USER_ASSIGN_NOTIFICATION);
                    notificationEmail.setFrom(from_email);
                    notificationEmail.setSentDate(today);
                    notificationEmail.setText("Dear user, please be informed that your project assigned ended");
                    notificationEmail.setTo(user.getUserContact().getWorkEmail());
                    emailService.sendEmail(notificationEmail);
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        });
    }

    @Scheduled(cron = "0 0 0 ? * MON-FRI")
    public void sendDashboardNotification() {
        List<Dashboard> allDashboards = dashboardService.getAllDashboards();
        allDashboards.forEach(dashboard -> {
            if (!dashboard.getDueDate().equals("Not specified")) {
                try {
                    Date estimation = dataConverterService.convertStringToDate(dashboard.getDueDate());
                    Date today = dataConverterService.generateTodayDateDay();
                    if (today.before(estimation)) {
                        Period period = Period.between(dataConverterService.convertDateToLocal(today),
                                dataConverterService.convertDateToLocal(estimation));
                        if (period.getDays() < 5) {
                            SimpleMailMessage notificationEmail = new SimpleMailMessage();
                            notificationEmail.setSubject(DASHBOARD_DUEDATE_NOTIFICATION);
                            notificationEmail.setFrom(from_email);
                            notificationEmail.setSentDate(today);
                            notificationEmail.setText("Dear user, please be informed that dashboard "
                                    + dashboard.getDashboardName() + " due date is to come in " + period.getDays() +" days");
                            notificationEmail.setTo(dashboard.getReporter().getUserContact().getWorkEmail());
                            emailService.sendEmail(notificationEmail);
                        }
                    } else if (today.equals(estimation)) {
                        SimpleMailMessage notificationEmail = new SimpleMailMessage();
                        notificationEmail.setSubject(DASHBOARD_ESTIMATION_NOTIFICATION);
                        notificationEmail.setFrom(from_email);
                        notificationEmail.setSentDate(today);
                        notificationEmail.setText("Dear user, please be informed that dashboard "
                                + dashboard.getDashboardName() + " due date estimated");
                        notificationEmail.setTo(dashboard.getReporter().getUserContact().getWorkEmail());
                        emailService.sendEmail(notificationEmail);
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
