package by.application.task.tracker.controllers;

import by.application.task.tracker.data.entities.User;
import by.application.task.tracker.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.ModelAndView;

public interface CurrentUserController {

     default void getCurrentUser(UserService userService, ModelAndView view ) {
         User currentUser = userService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
         view.addObject("currentUser", currentUser);
        /* view.addObject("position", currentUser.getPosition());
         view.addObject("project", currentUser.getProject());
         view.addObject("qualification", currentUser.getQualification());*/
     }
}
