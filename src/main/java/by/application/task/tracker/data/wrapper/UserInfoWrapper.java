package by.application.task.tracker.data.wrapper;

import by.application.task.tracker.data.entities.User;
import by.application.task.tracker.data.entities.UserContact;

public class UserInfoWrapper {

private User user;
private UserContact userContact;

public UserInfoWrapper(){}

    public UserInfoWrapper(User user, UserContact userContact) {
        this.user = user;
        this.userContact = userContact;
    }

    public User getUser() {return user;}

    public void setUser(User user) {this.user = user;}

    public UserContact getUserContact() {return userContact;}

    public void setUserContact(UserContact userContact) {this.userContact = userContact;}
}
