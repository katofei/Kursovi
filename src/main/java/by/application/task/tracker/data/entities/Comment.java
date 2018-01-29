package by.application.task.tracker.data.entities;

import javax.persistence.*;

@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "comment_id", length = 5, nullable = false)
    private Long commentId;

    @ManyToOne
    private User author;

    @Column(name = "created")
    private String created;

    @Column(name = "updated")
    private String updated;

    @Lob
    private String text;

    public Long getCommentId() {return commentId;}
    public void setCommentId(Long commentId) {this.commentId = commentId;}

    public User getAuthor() {return author;}
    public void setAuthor(User author) {this.author = author;}

    public String getCreated() {return created;}
    public void setCreated(String created) {this.created = created;}

    public String getUpdated() {return updated;}
    public void setUpdated(String updated) {this.updated = updated;}

    public String getText() {return text;}
    public void setText(String text) {this.text = text;}
}
