package by.application.task.tracker.validation.exception;

public class LoginExistsException extends Throwable{
    public LoginExistsException(final String message) {
        super(message);
    }
}
