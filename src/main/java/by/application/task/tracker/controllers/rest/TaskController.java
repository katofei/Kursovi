package by.application.task.tracker.controllers.rest;

import by.application.task.tracker.data.entities.Task;
import by.application.task.tracker.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class TaskController {

    @Autowired
    private TaskService taskService;

    @RequestMapping(path = "/task{id}", method = RequestMethod.GET)
    public ModelAndView getTaskPage(@PathVariable("id") long id) {
        Task task = taskService.findTaskById(id);
        ModelAndView view = new ModelAndView("task");
        view.addObject("task", task);
        return view;
    }
}
