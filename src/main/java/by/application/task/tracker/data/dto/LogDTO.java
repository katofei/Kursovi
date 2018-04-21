package by.application.task.tracker.data.dto;


public class LogDTO {

    private long executor;
    private long task;
    private double percentage;
    private double timeSpent;

    public LogDTO() {}

    public long getExecutor() { return executor; }
    public void setExecutor(long executor) { this.executor = executor; }

    public long getTask() { return task; }
    public void setTask(long task) { this.task = task; }

    public double getPercentage() { return percentage; }
    public void setPercentage(double percentage) { this.percentage = percentage; }

    public double getTimeSpent() { return timeSpent; }
    public void setTimeSpent(double timeSpent) { this.timeSpent = timeSpent; }
}
