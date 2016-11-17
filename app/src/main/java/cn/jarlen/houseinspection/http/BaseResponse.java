package cn.jarlen.houseinspection.http;

/**
 * DESCRIBE:
 * Created by hjl on 2016/11/17.
 */

public class BaseResponse {

    /**
     * 业务操作成功
     */
    public static final int RESPONSE_OPT_SUCCESS = 200;

    /**
     * 业务操作失败
     */
    public static final int RESPONSE_OPT_FAIL = 400;

    /**
     * 参数失败
     */
    public static final int RESPONSE_ERROR_PARAM = 401;

    /**
     * 账户验证失败
     */
    public static final int RESPONSE_ERROR_ACCOUNT = 402;

    private int status;
    private long time;
    private String message;

    public int getStatus() {
        return status;
    }

    public long getTime() {
        return time;
    }

    public String getMessage() {
        return message;
    }
}
