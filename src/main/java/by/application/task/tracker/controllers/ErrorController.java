package by.application.task.tracker.controllers;

import by.application.task.tracker.service.PositionService;
import by.application.task.tracker.service.QualificationService;
import by.application.task.tracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ErrorController implements CurrentUserController{

    private final UserService userService;
    private final PositionService positionService;
    private final QualificationService qualificationService;

    @Autowired
    public ErrorController(UserService userService, PositionService positionService,
                           QualificationService qualificationService) {
        this.userService = userService;
        this.positionService = positionService;
        this.qualificationService = qualificationService;
    }

    @RequestMapping(value = "/error", method = RequestMethod.GET)
    public ModelAndView getErrorPage(HttpServletRequest httpRequest) {
        ModelAndView errorPage = new ModelAndView("errorPage");
        getCurrentUser(userService, errorPage);

        String errorMsg= "", advice ="" , parag = "", warning = "", code ="" , thref = "'";
        int httpErrorCode = getErrorCode(httpRequest);

        switch (httpErrorCode) {
            case 400: {
                code = String.valueOf(httpErrorCode);
                parag = "400 Error Page";
                warning = " Oops! Bad request";
                errorMsg = " You or Your browser sent a request that this server could not understand.\n";
                advice="Meanwhile, you may return to homepage";
                thref = "homePage";
                break;
            }
            case 401: {
                code = String.valueOf(httpErrorCode);
                parag = "401 Error Page";
                warning = " Oops! Authorization Required";
                errorMsg = " Access is allowed only for authorized users.\n";
                advice="Please visit authorization page.\n";
                thref = "login";
                break;
            }
            case 404: {
                code = String.valueOf(httpErrorCode);
                parag = "404 Error Page";
                warning = " Oops! Page not found.";
                errorMsg = " We could not find the page you were looking for.\n" ;
                advice= " Meanwhile, you may return to homepage";
                thref = "homePage";
                break;
            }
            case 500: {
                code = String.valueOf(httpErrorCode);
                parag = "500 Error Page";
                warning = " Oops! Something went wrong.";
                errorMsg = " We will work on fixing that right away.\n" ;
                advice = "Meanwhile, you may return to homepage";
                thref = "homePage";
                break;
            }
        }
        errorPage.addObject("errorCode", code);
        errorPage.addObject("errorMsg", errorMsg);
        errorPage.addObject("parag", parag);
        errorPage.addObject("warning", warning);
        errorPage.addObject("advice", advice);
        errorPage.addObject("thref", thref);
        return errorPage;
    }


    private int getErrorCode(HttpServletRequest httpRequest) {
        return (Integer) httpRequest.getAttribute("javax.servlet.error.status_code");
    }
}