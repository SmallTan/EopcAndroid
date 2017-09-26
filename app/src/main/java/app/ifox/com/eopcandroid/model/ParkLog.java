package app.ifox.com.eopcandroid.model;


import java.io.Serializable;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by exphuhong on 17-9-13.
 * Start
 */
public class ParkLog implements Serializable{

    private static final long serialVersionUID = -5188174034475774679L;
    private Integer id;
    private String logInformation;
    private Date logDate;
    private String imgPath;
    private Integer userId;
    private Integer surnameCount;
    private ParkUser parkUser;
    private List<ParkComment> parkComments = new ArrayList<>();


    public ParkLog() {
    }

    public ParkLog(Integer id, String logInformation, Date logDate, String imgPath, Integer userId, Integer surnameCount) {
        this.id = id;
        this.logInformation = logInformation;
        this.logDate = logDate;
        this.imgPath = imgPath;
        this.userId = userId;
        this.surnameCount = surnameCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ParkLog parkLog = (ParkLog) o;

        if (id != null ? !id.equals(parkLog.id) : parkLog.id != null) return false;
        if (logInformation != null ? !logInformation.equals(parkLog.logInformation) : parkLog.logInformation != null)
            return false;
        if (logDate != null ? !logDate.equals(parkLog.logDate) : parkLog.logDate != null) return false;
        if (imgPath != null ? !imgPath.equals(parkLog.imgPath) : parkLog.imgPath != null) return false;
        if (userId != null ? !userId.equals(parkLog.userId) : parkLog.userId != null) return false;
        if (surnameCount != null ? !surnameCount.equals(parkLog.surnameCount) : parkLog.surnameCount != null)
            return false;
        if (parkUser != null ? !parkUser.equals(parkLog.parkUser) : parkLog.parkUser != null) return false;
        return parkComments != null ? parkComments.equals(parkLog.parkComments) : parkLog.parkComments == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (logInformation != null ? logInformation.hashCode() : 0);
        result = 31 * result + (logDate != null ? logDate.hashCode() : 0);
        result = 31 * result + (imgPath != null ? imgPath.hashCode() : 0);
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (surnameCount != null ? surnameCount.hashCode() : 0);
        result = 31 * result + (parkUser != null ? parkUser.hashCode() : 0);
        result = 31 * result + (parkComments != null ? parkComments.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ParkLog{" +
                "id=" + id +
                ", logInformation='" + logInformation + '\'' +
                ", logDate=" + logDate +
                ", imgPath='" + imgPath + '\'' +
                ", userId=" + userId +
                ", surnameCount=" + surnameCount +
                ", parkUser=" + parkUser +
                ", parkComments=" + parkComments +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogInformation() {
        return logInformation;
    }

    public void setLogInformation(String logInformation) {
        this.logInformation = logInformation;
    }

    public Date getLogDate() {
        return logDate;
    }

    public void setLogDate(Date logDate) {
        this.logDate = logDate;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getSurnameCount() {
        return surnameCount;
    }

    public void setSurnameCount(Integer surnameCount) {
        this.surnameCount = surnameCount;
    }

    public ParkUser getParkUser() {
        return parkUser;
    }

    public void setParkUser(ParkUser parkUser) {
        this.parkUser = parkUser;
    }

    public List<ParkComment> getParkComments() {
        return parkComments;
    }

    public void setParkComments(List<ParkComment> parkComments) {
        this.parkComments = parkComments;
    }
}
