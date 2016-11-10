package cn.jarlen.houseinspection.data;

/**
 * DESCRIBE: 问题时间
 * Created by jarlen on 2016/11/3.
 */

public class Problem {

    /**
     * 问题点id
     */
    private int problemid;

    /**
     * 问题标题
     */
    private String title;

    /**
     * 问题场景照片
     */
    private String img;

    /**
     * 问题描述
     */
    private String content;

    /**
     * 处理状态
     * 0:  未解决
     * 1:  处理中
     * 2:: 已解决
     */
    private int status;

    /**
     * 上传时间
     */
    private long create_at;

    public void setContent(String content) {
        this.content = content;
    }

    public void setCreate_at(long create_at) {
        this.create_at = create_at;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void setProblemid(int problemid) {
        this.problemid = problemid;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getProblemid() {
        return problemid;
    }

    public int getStatus() {
        return status;
    }

    public long getCreate_at() {
        return create_at;
    }

    public String getContent() {
        return content;
    }

    public String getImg() {
        return img;
    }

    public String getTitle() {
        return title;
    }
}
