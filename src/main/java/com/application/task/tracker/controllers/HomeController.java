package com.application.task.tracker.controllers;

import com.application.task.tracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    @Autowired
    private UserService userService;

@RequestMapping(path = "/", method = RequestMethod.GET)
public ModelAndView getIndexPage(){
    ModelAndView model = new ModelAndView("index");
    model.addObject("user",userService.findById(1L));
    //model.addObject("massage","Bla Bla");
    return model;
}

}
