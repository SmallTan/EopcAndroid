package app.ifox.com.eopcandroid.model;

import java.io.Serializable;

/**
 * Created by exphuhong on 17-9-13.
 * Start
 */
public class ParkMap implements Serializable{
    private static final long serialVersionUID = 4906709623797027148L;
    private Integer id;
    private float mapLongitude; //经度
    private float mapLatitude;//纬度
    private String mapTitle;
    private String mapContent;

    public ParkMap() {
    }

    public ParkMap(Integer id, float mapLongitude, float mapLatitude, String mapTitle, String mapContent) {
        this.id = id;
        this.mapLongitude = mapLongitude;
        this.mapLatitude = mapLatitude;
        this.mapTitle = mapTitle;
        this.mapContent = mapContent;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ParkMap parkMap = (ParkMap) o;

        if (Float.compare(parkMap.mapLongitude, mapLongitude) != 0) return false;
        if (Float.compare(parkMap.mapLatitude, mapLatitude) != 0) return false;
        if (id != null ? !id.equals(parkMap.id) : parkMap.id != null) return false;
        if (mapTitle != null ? !mapTitle.equals(parkMap.mapTitle) : parkMap.mapTitle != null) return false;
        return mapContent != null ? mapContent.equals(parkMap.mapContent) : parkMap.mapContent == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (mapLongitude != +0.0f ? Float.floatToIntBits(mapLongitude) : 0);
        result = 31 * result + (mapLatitude != +0.0f ? Float.floatToIntBits(mapLatitude) : 0);
        result = 31 * result + (mapTitle != null ? mapTitle.hashCode() : 0);
        result = 31 * result + (mapContent != null ? mapContent.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ParkMap{" +
                "id=" + id +
                ", mapLongitude=" + mapLongitude +
                ", mapLatitude=" + mapLatitude +
                ", mapTitle='" + mapTitle + '\'' +
                ", mapContent='" + mapContent + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public float getMapLongitude() {
        return mapLongitude;
    }

    public void setMapLongitude(float mapLongitude) {
        this.mapLongitude = mapLongitude;
    }

    public float getMapLatitude() {
        return mapLatitude;
    }

    public void setMapLatitude(float mapLatitude) {
        this.mapLatitude = mapLatitude;
    }

    public String getMapTitle() {
        return mapTitle;
    }

    public void setMapTitle(String mapTitle) {
        this.mapTitle = mapTitle;
    }

    public String getMapContent() {
        return mapContent;
    }

    public void setMapContent(String mapContent) {
        this.mapContent = mapContent;
    }
}
