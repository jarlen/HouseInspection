package cn.jarlen.houseinspection.data;

/**
 * DESCRIBE: 授权用户数据
 * <p>
 * Created by jarlen on 2016/11/11.
 */

public class User {

    /**
     * 账户来源
     * QQ,新浪微博
     */
    public static String platform;

    /**
     * 用户ID
     */
    public static String userID;

    /**
     * 用户昵称
     */
    public static String nickName;

    /**
     * 用户头像
     */
    public static String icon;

    /**
     * 授权token
     */
    public static String token;

    /**
     * 授权token过期时间
     */
    public static long expiresTime;

    /**
     * 授权token凭证有效时间
     */
    public static long expiresIn;
}
