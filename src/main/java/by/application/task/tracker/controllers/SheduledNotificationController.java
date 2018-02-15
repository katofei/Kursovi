package by.application.task.tracker.controllers;

import by.application.task.tracker.data.entities.Dashboard;
import by.application.task.tracker.data.entities.Project;
import by.application.task.tracker.data.entities.Task;
import by.application.task.tracker.data.entities.User;
import by.application.task.tracker.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Period;
import java.util.Date;
import java.util.List;

import static by.application.task.tracker.Constants.*;

@Component
public class SheduledNotificationController {

    @Autowired private EmailService emailService;
    @Autowired private UserService userService;
    @Autowired private ProjectService projectService;
    @Autowired private TaskService taskService;
    @Autowired private DashboardService dashboardService;
    @Autowired private DataConverterService dataConverterService;
    @Autowired private UserStatusService userStatusService;
    @Autowired private DashboardStatusService dashboardStatusService;

    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MMM-dd");


    @Scheduled(cron = "0 0 0 ? * MON-FRI")
    public void sendTaskNotification() {
        List<Task> allTasks = taskService.getAllTasks();
        allTasks.forEach((Task task) -> {
            try {
                Date estimation = dataConverterService.convertStringToDate(task.getEstimation());
                Date today = dataConverterService.generateTodayDateDay();
                //todo : check statement need to be crated for "Not specified"
                //todo : check statement need to be crated for duration
                if(today.before(estimation)){
                    Period period =  Period.between(dataConverterService.convertDateToLocal(today), dataConverterService.convertDateToLocal(estimation));

                }
                else if (today.equals(estimation)){
                SimpleMailMessage notificationEmail = new SimpleMailMessage();
                notificationEmail.setSubject(TASK_ESTIMATION_NOTIFICATION);
                notificationEmail.setFrom(from_email);
                notificationEmail.setSentDate(today);
                notificationEmail.setText("Dear user, please be informed that task " + task.getTaskName() + " estimated");
                notificationEmail.setTo(task.getExecutor().getUserContact().getWorkEmail());
                emailService.sendEmail(notificationEmail);
                    }

            } catch (ParseException e) {
                e.printStackTrace();
            }
        });

    }

    @Scheduled(cron = "0 0 0 ? * MON-FRI")
    public void sendProjectNotification() {
        List<Project> allProjects = projectService.getAllProjects();
        allProjects.forEach(project -> {
            try {
                Date deadLine = dataConverterService.convertStringToDate(project.getDeadLine());
                Date today = dataConverterService.generateTodayDateDay();
                if(deadLine != null){

                }
                //todo WHATAFUCK!?!?!?!?!??!!?
                else if (today.equals(deadLine)){
                SimpleMailMessage notificationEmail = new SimpleMailMessage();
                notificationEmail.setSubject(PROJECT_DEADLINE_NOTIFICATION);
                notificationEmail.setFrom(from_email);
                notificationEmail.setSentDate(today);
                notificationEmail.setText("Dear user, please be informed that project " + project.getProjectName() + " ended");
                userService.getAllUsers(project.getProjectId())
                        .stream().forEach(user -> {
                    if(PROJECT_MANAGER.equals(user.getProjectRole().toString()) || TEAM_LEAD.equals(user.getProjectRole().toString()))
                        notificationEmail.setTo(user.getUserContact().getWorkEmail());
                });
                emailService.sendEmail(notificationEmail);
                }
            } catch (ParseException e) {
                e.printStackTrace();
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
                if(today.before(estimation)){
                    user.setUserStatus(userStatusService.findByStatusName(USER_ASSIGNED));
                }
              else if (today.equals(estimation)){
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
            try {
                Date estimation = dataConverterService.convertStringToDate(dashboard.getEstimation());
                Date today = dataConverterService.generateTodayDateDay();
                if(today.before(estimation)){

                }
                //todo : check statement need to be crated
                SimpleMailMessage notificationEmail = new SimpleMailMessage();
                notificationEmail.setSubject(DASHBOARD_ESTIMATION_NOTIFICATION);
                notificationEmail.setFrom(from_email);
                notificationEmail.setSentDate(today);
                notificationEmail.setText("Dear user, please be informed that dashboard " + dashboard.getDashboardName() + " estimated");
                notificationEmail.setTo(dashboard.getReporter().getUserContact().getWorkEmail());
                emailService.sendEmail(notificationEmail);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        });
    }
}
