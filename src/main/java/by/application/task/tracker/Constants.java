package by.application.task.tracker;

public interface Constants {

    /*      User statuses    */
    String USER_ACTIVATED = "Activated";
    String USER_NOT_ACTIVATED = "Not activated";
    String USER_NOT_ASSIGNED = "Not assigned";
    String USER_ASSIGNED = "Assigned";

    /*      User roles    */
    String USER_ROLE = "USER";
    String ADMIN_ROLE = "ADMIN";

    /*      Project roles    */
    String TEAM_LEAD = "Team lead";
    String PROJECT_MANAGER = "Project manager";
    String MANAGER = "Manager";
    String STREAM_LEAD = "Stream lead";


    /*      Dashboard statuses    */
    String ON_HOLD = "On hold";
    String IN_QA = "In qa";
    String OPEN = "Open";
    String IN_BUILD = "In build";
    String IN_DESIGN = "In design";
    String IN_ANALYSIS = "In analysis";
    String REOPENED = "Reopened";
    String CLOSED = "Closed";

    /*      Tsk statuses    */
    String IMPLEMENTED = "Implemented";
    String IN_PROGRESS = "In progress";
    String NEED_INFO = "Additional info required";
    String INFO_RECEIVED = "Info received";
    String READY_FOR_TESTING = "Ready for testing";


    /*  Notification components*/

    String REGISTRATION_CONFIRM_NOTIFICATION = "Registration Confirmation";
    String USER_DELETION_NOTIFICATION = "Deletion notification";

    String USER_ASSIGN_NOTIFICATION = "User assign notification";

    String PROJECT_DEADLINE_NOTIFICATION = "Project deadline notification";

    String TASK_ESTIMATION_NOTIFICATION = "Task estimation notification";
    String TASK_DUEDATE_NOTIFICATION = "Task due date notification";
    String TASK_CREATION_NOTIFICATION = "New task created";
    String TASK_MODIFICATION_NOTIFICATION = "Task modification notification";


    String DASHBOARD_ESTIMATION_NOTIFICATION = "Dashboard deadline notification";
    String DASHBOARD_DUEDATE_NOTIFICATION = "Dashboard due date notification";
    String DASHBOARD_CREATION_NOTIFICATION = "New dashboard created";
    String DASHBOARD_MODIFICATION_NOTIFICATION = "Dashboard modification notification";


    String from_email = "Task Tracker System";

}
