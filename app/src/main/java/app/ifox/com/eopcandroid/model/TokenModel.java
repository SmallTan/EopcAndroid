package app.ifox.com.eopcandroid.model;



/**
 * Created by exphuhong on 17-9-10.
 * Start
 */
public class TokenModel {
    private String startTime;
    private  String email;
    private  String uuid;
    private long time;

    public TokenModel() {
    }

    public TokenModel(String startTime, String email, String uuid, long time) {
        this.startTime = startTime;
        this.email = email;
        this.uuid = uuid;
        this.time = time;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "TokenModel{" +
                "startTime=" + startTime +
                ", email='" + email + '\'' +
                ", uuid=" + uuid +
                ", time=" + time +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TokenModel that = (TokenModel) o;

        if (time != that.time) return false;
        if (startTime != null ? !startTime.equals(that.startTime) : that.startTime != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        return uuid != null ? uuid.equals(that.uuid) : that.uuid == null;

    }

    @Override
    public int hashCode() {
        int result = startTime != null ? startTime.hashCode() : 0;
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (uuid != null ? uuid.hashCode() : 0);
        result = 31 * result + (int) (time ^ (time >>> 32));
        return result;
    }
}
