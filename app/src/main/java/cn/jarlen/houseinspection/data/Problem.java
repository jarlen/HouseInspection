package cn.jarlen.houseinspection.data;

/**
 * DESCRIBE: 问题详情
 * Created by jarlen on 2016/11/3.
 */

public class Problem {

    /**
     * 问题点id
     */
    private int problemid;

    /**
     * 问题场景照片
     */
    private String pics;

    /**
     * 问题描述
     */
    private String describe;

    /**
     * 楼盘名称
     */
    private String estatename;

    /**
     * 楼盘地址
     */
    private String estateaddr;

    /**
     * 位置-经度
     */
    private String longitude;

    /**
     * 位置-维度
     */
    private String latitude;

    /**
     * 楼盘期数
     */
    private int period;

    /**
     * 作者
     */
    private String author;

    /**
     * 联系方式
     */
    private String phone;

    /**
     * 处理状态
     * 0:  未解决
     * 1:  处理中
     * 2:: 已解决
     */
    private int status;

    /**
     * 点击次数
     */
    private int clicks;

    /**
     * 上传时间
     */
    private long create_at;

    /**
     * "1"=匿名
     */
    private String anon;

    public void setAnon(String anon) {
        this.anon = anon;
    }

    public String getAnon() {
        return anon;
    }

    public void setDescribe(String content) {
        this.describe = content;
    }

    public void setCreateTime(long create_at) {
        this.create_at = create_at;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setEstateAddr(String addr) {
        this.estateaddr = addr;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setEstatename(String estatename) {
        this.estatename = estatename;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public int getPeriod() {
        return period;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setPics(String pics) {
        this.pics = pics;
    }

    public void setProblemid(int problemid) {
        this.problemid = problemid;
    }

    public int getProblemid() {
        return problemid;
    }

    public int getStatus() {
        return status;
    }

    public long getCreateTime() {
        return create_at;
    }

    public String getDescribe() {
        return describe;
    }

    public String getEstateAddr() {
        return estateaddr;
    }

    public String getAuthor() {
        return author;
    }

    public String getEstatename() {
        return estatename;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getPhone() {
        return phone;
    }

    public String getPics() {
        return pics;
    }

    public void setClicks(int clicks) {
        this.clicks = clicks;
    }

    public int getClicks() {
        return clicks;
    }
}
