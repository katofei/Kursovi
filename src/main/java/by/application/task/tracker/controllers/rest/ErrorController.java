package by.application.task.tracker.controllers.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ErrorController {

    private ModelAndView errorPage;

    @RequestMapping(value = "/error", method = RequestMethod.GET)
    public ModelAndView renderErrorPage(HttpServletRequest httpRequest) {
        int httpErrorCode = (Integer) httpRequest.getAttribute("javax.servlet.error.status_code");
        switch (httpErrorCode) {
            case 400: {
             errorPage = new ModelAndView("badRequest");
                break;
            }
            case 401: {
                errorPage =new ModelAndView("unauthorized");
                break;
            }
            case 403: {
                errorPage = new ModelAndView("accessDenied");
                break;
            }
            case 404: {
                errorPage = new ModelAndView("notFound");
                break;
            }
            case 500: {
                errorPage = new ModelAndView("internalServerError");
                break;
            }
        }
        return this.errorPage;
    }
}