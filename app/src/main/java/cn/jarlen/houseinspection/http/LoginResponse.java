package cn.jarlen.houseinspection.http;

import cn.jarlen.houseinspection.data.User;

/**
 * DESCRIBE:
 * Created by hjl on 2016/11/17.
 */

public class LoginResponse extends BaseResponse {
    private User content;

    public User getContent() {
        return content;
    }
}
