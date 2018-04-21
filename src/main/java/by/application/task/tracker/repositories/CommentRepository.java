package by.application.task.tracker.repositories;

import by.application.task.tracker.data.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Comment findByTaskId(long taskId);
    Comment findBycommentId(long commentId);
}
