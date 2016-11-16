package cn.jarlen.houseinspection.data;

import android.content.Context;
import android.text.TextUtils;

import cn.jarlen.houseinspection.http.OkHttpPatch;
import cn.jarlen.houseinspection.utils.Constants;
import cn.jarlen.richcommon.utils.PreferenceUtils;

/**
 * DESCRIBE: 授权用户数据
 * <p/>
 * Created by jarlen on 2016/11/11.
 */

public class User {

    private static User cache;


    public static void setUserCache(User user) {
        cache = user;
        PreferenceUtils preferenceUtils = PreferenceUtils.newInstance(OkHttpPatch.getContext(), Constants.KEY_USER);
        preferenceUtils.addMess("user_id", user.getUserId());
        preferenceUtils.addMess("token", user.getToken());
        preferenceUtils.addMess("platfrom", user.getPlatform());
        preferenceUtils.addMess("user_name", user.getUserName());
        preferenceUtils.addMess("avatar", user.getAvatar());
        preferenceUtils.addMess("expires_in", user.getExpiresIn());
        preferenceUtils.addMess("expires_time", user.getExpiresTime());
    }

    public static void initUserCache(Context context) {
        PreferenceUtils preferenceUtils = PreferenceUtils.newInstance(context, Constants.KEY_USER);

        String userId = preferenceUtils.getMessString("user_id", "");
        String token = preferenceUtils.getMessString("token", "");
        String platfrom = preferenceUtils.getMessString("platfrom", "");

        if (TextUtils.isEmpty(userId) || TextUtils.isEmpty(token) || TextUtils.isEmpty(platfrom)) {
            cache = null;
            return;
        }

        String userName = preferenceUtils.getMessString("user_name");
        String avatar = preferenceUtils.getMessString("avatar");
        long expiresIn = preferenceUtils.getMessLong("expires_in", 0);
        long expiresTime = preferenceUtils.getMessLong("expires_time", 0);

        User user = new User();
        user.setAvatar(avatar);
        user.setExpiresIn(expiresIn);
        user.setExpiresTime(expiresTime);
        user.setPlatform(platfrom);
        user.setToken(token);
        user.setUserId(userId);
        user.setUserName(userName);
        cache = user;
    }

    public static User getUserCache() {
        return cache;
    }


    public static void clearCache() {
        cache = null;
        PreferenceUtils preferenceUtils = PreferenceUtils.newInstance(OkHttpPatch.getContext(), Constants.KEY_USER);
        preferenceUtils.deteletMess();
    }

    public static boolean isUserLogin(){
        if(cache == null){
            return false;
        }

        if(TextUtils.isEmpty(cache.getToken())){
            return false;
        }
        return true;
    }

    /**
     * 账户来源
     * QQ,新浪微博
     */
    private String platform;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 用户名称
     */
    private String userName;

    /**
     * 用户头像
     */
    private String avatar;

    /**
     * 授权token
     */
    private String token;

    /**
     * 授权token过期时间
     */
    private long expiresTime;

    /**
     * 授权token凭证有效时间
     */
    private long expiresIn;

    public void setToken(String token) {
        this.token = token;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setExpiresIn(long expiresIn) {
        this.expiresIn = expiresIn;
    }

    public void setExpiresTime(long expiresTime) {
        this.expiresTime = expiresTime;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getToken() {
        return token;
    }

    public String getPlatform() {
        return platform;
    }

    public String getAvatar() {
        return avatar;
    }

    public long getExpiresIn() {
        return expiresIn;
    }

    public long getExpiresTime() {
        return expiresTime;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }


}
