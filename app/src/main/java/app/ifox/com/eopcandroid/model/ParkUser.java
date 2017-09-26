package app.ifox.com.eopcandroid.model;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by exphuhong on 17-9-13.
 * Start
 */
public class ParkUser implements Serializable {
    private static final long serialVersionUID = -4188697887466612154L;
    private Integer id;
    private String userName;
    private String email;
    private String password;
    private String school;
    private String imgPath;
    private String individualResume;

    private List<ParkLog> logs = new ArrayList<>();


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ParkUser parkUser = (ParkUser) o;

        if (id != null ? !id.equals(parkUser.id) : parkUser.id != null) return false;
        if (userName != null ? !userName.equals(parkUser.userName) : parkUser.userName != null) return false;
        if (email != null ? !email.equals(parkUser.email) : parkUser.email != null) return false;
        if (password != null ? !password.equals(parkUser.password) : parkUser.password != null) return false;
        if (school != null ? !school.equals(parkUser.school) : parkUser.school != null) return false;
        if (imgPath != null ? !imgPath.equals(parkUser.imgPath) : parkUser.imgPath != null) return false;
        if (individualResume != null ? !individualResume.equals(parkUser.individualResume) : parkUser.individualResume != null)
            return false;
        return logs != null ? logs.equals(parkUser.logs) : parkUser.logs == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (school != null ? school.hashCode() : 0);
        result = 31 * result + (imgPath != null ? imgPath.hashCode() : 0);
        result = 31 * result + (individualResume != null ? individualResume.hashCode() : 0);
        result = 31 * result + (logs != null ? logs.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ParkUser{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", school='" + school + '\'' +
                ", imgPath='" + imgPath + '\'' +
                ", individualResume='" + individualResume + '\'' +
                ", logs=" + logs +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public String getIndividualResume() {
        return individualResume;
    }

    public void setIndividualResume(String individualResume) {
        this.individualResume = individualResume;
    }

    public List<ParkLog> getLogs() {
        return logs;
    }

    public void setLogs(List<ParkLog> logs) {
        this.logs = logs;
    }

    public ParkUser() {
    }

    public ParkUser(Integer id, String userName, String email, String password, String school, String imgPath, String individualResume) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.school = school;
        this.imgPath = imgPath;
        this.individualResume = individualResume;
    }
}
