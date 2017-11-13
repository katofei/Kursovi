package by.application.task.tracker.controllers.rest;

import by.application.task.tracker.data.entities.User;
import by.application.task.tracker.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ErrorController {

    @Autowired
    private UserService userService;
    @Autowired
    private PositionService positionService;
    @Autowired
    private QualificationService qualificationService;

    @RequestMapping(value = "/error", method = RequestMethod.GET)
    public ModelAndView getErrorPage(HttpServletRequest httpRequest) {
        ModelAndView errorPage = new ModelAndView("errorPage");
        User currentUser = userService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        errorPage.addObject("currentUser", currentUser);
        errorPage.addObject("position",positionService.findPositionById(currentUser.getPosition().getPositionId()));
        errorPage.addObject("qualification",qualificationService.findQualificationById(currentUser.getQualification()
                 .getQualificationId()));
        String errorMsg = "";
        String advice = "";
        String parag="";
        String warning ="";
        int httpErrorCode = getErrorCode(httpRequest);

        switch (httpErrorCode) {
            case 400: {
                parag = "400 Error Page";
                warning = " Oops! Bad request";
                errorMsg = " You or Your browser sent a request that this server could not understand.\n" +
                        "Meanwhile, you may return to dashboard <a th:href=\"@{starter.html}\"";
                break;
            }
            case 401: {
                parag = "401 Error Page";
                warning = " Oops! Authorization Required";
                errorMsg = " Access is allowed only for authorized users.\n" +
                        "Please visit authorization page.\n" +
                        "<a th:href=\"@{login}\"";
                break;
            }
            case 403: {
                parag = "403 Error Page";
                warning = " Oops! Access denied";
                errorMsg = " Sorry, <a th:text=\"${user.userName}\">\n" +
                        "You don't have permission to view that page\n" +
                        "Please, return to dashboard <a th:href=\"@{starter.html}\"";
                break;
            }
            case 404: {
                parag = "404 Error Page";
                warning = " Oops! Page not found.";
                errorMsg = " We could not find the page you were looking for.\n" +
                        " Meanwhile, you may return to dashboard <a th:href=\"@{starter.html}\"";
                break;
            }
            case 500: {
                parag = "500 Error Page";
                warning = " Oops! Something went wrong.";
                errorMsg = " We will work on fixing that right away.\n" +
                        "Meanwhile, you may return to dashboard <a th:href=\"@{starter.html}\"";
                break;
            }
        }
        errorPage.addObject("errorCode", httpErrorCode);
        errorPage.addObject("errorMsg", errorMsg);
        errorPage.addObject("parag", parag);
        errorPage.addObject("warning", warning);
        errorPage.addObject("advice", advice);
        return errorPage;
    }


    private int getErrorCode(HttpServletRequest httpRequest) {
        return (Integer) httpRequest
                .getAttribute("javax.servlet.error.status_code");
    }
}