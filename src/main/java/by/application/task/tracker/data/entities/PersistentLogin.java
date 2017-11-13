package by.application.task.tracker.data.entities;



@Entity
@Table(name = "persistent_logins")
public class PersistentLogin {

    @Id
    @Column(name = "series")
    private String series;

    @Column(name = "userName")
    private String username;

    @Column(name = "token")
    private String token;

    @Temporal(TemporalType.TIMESTAMP)
    private Date last_used;

    public String getSeries() {return series;}

    public void setSeries(String series) {this.series = series;}

    public String getUsername() {return username;}

    public void setUsername(String username) {this.username = username;}

    public String getToken() {return token;}

    public void setToken(String token) {this.token = token;}

    public Date getLast_used() {return last_used;}

    public void setLast_used(Date last_used) {this.last_used = last_used;}
}