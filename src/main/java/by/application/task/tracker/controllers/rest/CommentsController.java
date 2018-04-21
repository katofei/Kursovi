package by.application.task.tracker.controllers.rest;

import by.application.task.tracker.data.entities.Comment;
import by.application.task.tracker.data.entities.Task;
import by.application.task.tracker.repositories.CommentRepository;
import by.application.task.tracker.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class CommentsController {

    private final CommentRepository commentRepository;
    private final TaskService taskService;

    @Autowired
    public CommentsController(CommentRepository commentRepository, TaskService taskService) {
        this.commentRepository = commentRepository;
        this.taskService = taskService;
    }


    @PostMapping("/task/{taskId}/addComment")
    public Comment createComment(@PathVariable(value = "taskId") long taskId,
                                 @Valid @RequestBody Comment comment) {
        Task taskById = taskService.findTaskById(taskId);
        comment.setTask(taskById);
        return commentRepository.save(comment);
    }

    @PutMapping("/task/{taskId}/comments/{commentId}")
    public Comment updateComment(@PathVariable(value = "taskId") long taskId,
                                 @PathVariable(value = "commentId") long commentId,
                                 @Valid @RequestBody Comment commentRequest) {

        Comment bycommentId = commentRepository.findBycommentId(commentId);
        bycommentId.setText(commentRequest.getText());
        return commentRepository.save(bycommentId);
    }

    @DeleteMapping("/task/{taskId}/comments/{commentId}")
    public void deleteComment(@PathVariable(value = "taskId") long taskId,
                              @PathVariable(value = "commentId") long commentId) {
        commentRepository.delete(commentId);
    }
}
