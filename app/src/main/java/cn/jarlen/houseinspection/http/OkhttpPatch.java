package cn.jarlen.houseinspection.http;

import android.content.Context;

import cn.jarlen.houseinspection.data.User;
import cn.jarlen.httppatch.okhttp.Callback2;
import cn.sharesdk.framework.PlatformDb;
import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * DESCRIBE:
 * Created by hjl on 2016/11/15.
 */

public class OkHttpPatch {

    private static Context mContext;

    public static Context getContext() {
        return mContext;
    }

    public static void initOkHttpPatch(Context context) {
        mContext = context;
    }

    private OkHttpClient mOkHttpClient;

    private static OkHttpPatch okHttpPatch;

    private OkHttpPatch() {
        mOkHttpClient = new OkHttpClient();
    }

    public static OkHttpPatch getOkHttpPatch() {
        if (okHttpPatch == null) {
            okHttpPatch = new OkHttpPatch();
        }
        return okHttpPatch;
    }

    public void login(PlatformDb platform, Callback2 callback) {

        RequestBody requestBody = new FormBody.Builder()
                .add("a", "login")
                .add("platform", platform.getPlatformNname())
                .add("user_id", platform.getUserId())
                .add("user_name", platform.getUserName())
                .add("avatar", platform.getUserIcon())
                .add("token", platform.getToken())
                .add("expires_in", "" + platform.getExpiresIn())
                .add("expires_time", "" + platform.getExpiresTime())
                .build();
        Request.Builder builder = new Request.Builder();
        builder.url(HttpConstants.BASE_URL);
        builder.post(requestBody);
        Request request = builder.build();
        Call mCall = mOkHttpClient.newCall(request);
        mCall.enqueue(callback);
    }

    public void findproblem(int page, Callback2 callback) {
        RequestBody requestBody = new FormBody.Builder()
                .add("a", "findproblem")
                .add("page_num", "" + page)
                .add("token", User.getUserCache().getToken())
                .add("page_size", "" + HttpConstants.PAGE_NUMS_ONE_TIME)
                .build();

        Request.Builder builder = new Request.Builder();
        builder.url(HttpConstants.BASE_URL);
        builder.post(requestBody);
        Request request = builder.build();
        Call mCall = mOkHttpClient.newCall(request);
        mCall.enqueue(callback);
    }


}
