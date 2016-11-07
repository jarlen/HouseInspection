package cn.jarlen.houseinspection.data;

import java.io.Serializable;

/**
 * DESCRIBE:
 * Created by hjl on 2016/11/7.
 */

public class LocationInfo implements Serializable {

    private double latitude;

    private double longitude;

    private String locationDesc;

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getLocationDesc() {
        return locationDesc;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLocationDesc(String locationDesc) {
        this.locationDesc = locationDesc;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
