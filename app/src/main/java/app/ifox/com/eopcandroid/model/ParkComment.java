package app.ifox.com.eopcandroid.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by exphuhong on 17-9-13.
 * Start
 */
public class ParkComment implements Serializable{

    private static final long serialVersionUID = -6855907475041180843L;
    private Integer id;
    private String comInformation;
    private String comDate;
    private Integer logId;
    private ParkLog parkLog;

    public ParkComment() {
    }

    public ParkComment(Integer id, String comInformation, String comDate, Integer logId, ParkLog parkLog) {
        this.id = id;
        this.comInformation = comInformation;
        this.comDate = comDate;
        this.logId = logId;
        this.parkLog = parkLog;

    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ParkComment that = (ParkComment) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (comInformation != null ? !comInformation.equals(that.comInformation) : that.comInformation != null)
            return false;
        if (comDate != null ? !comDate.equals(that.comDate) : that.comDate != null) return false;
        if (logId != null ? !logId.equals(that.logId) : that.logId != null) return false;
        if (parkLog != null ? !parkLog.equals(that.parkLog) : that.parkLog != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (comInformation != null ? comInformation.hashCode() : 0);
        result = 31 * result + (comDate != null ? comDate.hashCode() : 0);
        result = 31 * result + (logId != null ? logId.hashCode() : 0);
        result = 31 * result + (parkLog != null ? parkLog.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ParkComment{" +
                "id=" + id +
                ", comInformation='" + comInformation + '\'' +
                ", comDate=" + comDate +
                ", logId=" + logId +
                ", parkLog=" + parkLog +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getComInformation() {
        return comInformation;
    }

    public void setComInformation(String comInformation) {
        this.comInformation = comInformation;
    }

    public String getComDate() {
        return comDate;
    }

    public void setComDate(String comDate) {
        this.comDate = comDate;
    }

    public Integer getLogId() {
        return logId;
    }

    public void setLogId(Integer logId) {
        this.logId = logId;
    }

    public ParkLog getParkLog() {
        return parkLog;
    }

    public void setParkLog(ParkLog parkLog) {
        this.parkLog = parkLog;
    }
}
