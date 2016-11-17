package cn.jarlen.houseinspection.data;

import java.io.File;
import java.util.List;

/**
 * DESCRIBE:
 * Created by hjl on 2016/11/17.
 */

public class ProblemInput {

    public String estateAddr;

    public double longitude;

    public double latitude;

    public String estateName;

    public String estatePeriod;

    public String buildingNo;

    public String buildingUnit;

    public String roomNo;

    public String content;

    public String phone;

    public List<File> pics;

    public void setPics(List<File> pics) {
        this.pics = pics;
    }

    public void setBuildingNo(String buildingNo) {
        this.buildingNo = buildingNo;
    }

    public void setBuildingUnit(String buildingUnit) {
        this.buildingUnit = buildingUnit;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setEstateAddr(String estateAddr) {
        this.estateAddr = estateAddr;
    }

    public void setEstateName(String estateName) {
        this.estateName = estateName;
    }

    public void setEstatePeriod(String estatePeriod) {
        this.estatePeriod = estatePeriod;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public List<File> getPics() {
        return pics;
    }

    public String getBuildingNo() {
        return buildingNo;
    }

    public String getBuildingUnit() {
        return buildingUnit;
    }

    public String getContent() {
        return content;
    }

    public String getEstateAddr() {
        return estateAddr;
    }

    public String getEstateName() {
        return estateName;
    }

    public String getEstatePeriod() {
        return estatePeriod;
    }

    public String getPhone() {
        return phone;
    }

    public String getRoomNo() {
        return roomNo;
    }
}


