package cn.jarlen.houseinspection.http;

import android.content.Context;
import android.text.TextUtils;

import java.io.File;
import java.util.List;

import cn.jarlen.houseinspection.data.ProblemSubmit;
import cn.jarlen.houseinspection.data.User;
import cn.jarlen.httppatch.okhttp.Callback2;
import cn.sharesdk.framework.PlatformDb;
import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
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
        builder.url(HttpConstants.API_URL);
        builder.post(requestBody);
        Request request = builder.build();
        Call mCall = mOkHttpClient.newCall(request);
        mCall.enqueue(callback);
    }

    public void findproblem(int page, Callback2 callback) {

        FormBody.Builder bodyBuilder = new FormBody.Builder();
        bodyBuilder.add("a", "findproblem");
        bodyBuilder.add("page_num", "" + page);
        bodyBuilder.add("page_size", "" + HttpConstants.PAGE_NUMS_ONE_TIME);
        if (User.isUserLogin()) {
            bodyBuilder.add("token", User.getUserCache().getToken());
        }

        RequestBody requestBody = bodyBuilder.build();

        Request.Builder builder = new Request.Builder();
        builder.url(HttpConstants.API_URL);
        builder.post(requestBody);
        Request request = builder.build();
        Call mCall = mOkHttpClient.newCall(request);
        mCall.enqueue(callback);
    }


    public void submit(ProblemSubmit input, Callback2 callback) {

        MultipartBody.Builder builder = new MultipartBody.Builder();
        builder.setType(MultipartBody.FORM);
        builder.addFormDataPart("a","submit");
        builder.addFormDataPart("token",User.getUserCache().getToken());
        builder.addFormDataPart("estate_name",input.getEstateName());

        if(!TextUtils.isEmpty(input.getEstateAddr())){
            builder.addFormDataPart("addr",input.getEstateName());
        }

        builder.addFormDataPart("lat",""+input.getLatitude());
        builder.addFormDataPart("lon",""+input.getLongitude());

        if(!TextUtils.isEmpty(input.getEstatePeriod())){
            builder.addFormDataPart("period",""+input.getEstatePeriod());
        }

        if(!TextUtils.isEmpty(input.getBuildingNo())){
            builder.addFormDataPart("building",""+input.getBuildingNo());
        }

        if(!TextUtils.isEmpty(input.getBuildingUnit())){
            builder.addFormDataPart("unit",""+input.getBuildingUnit());
        }

        if(!TextUtils.isEmpty(input.getRoomNo())){
            builder.addFormDataPart("roomno",""+input.getRoomNo());
        }

        if(!TextUtils.isEmpty(input.getDescribe())){
            builder.addFormDataPart("desc",""+input.getDescribe());
        }

        if(!TextUtils.isEmpty(input.getPhone())){
            builder.addFormDataPart("phone",""+input.getPhone());
        }

        if(!TextUtils.isEmpty(input.getContactor())){
            builder.addFormDataPart("contactor",input.getContactor());
        }

        builder.addFormDataPart("anon",""+(input.getAnon()?1:0));

        List<String> mImgUrls = input.getPics();

        if (mImgUrls != null) {
            int length = mImgUrls.size();
            for (int index = 0; index < length; index++) {
                File file = new File(mImgUrls.get(index));
                if (file.exists()) {
                    builder.addFormDataPart("img" + index, file.getName(), RequestBody.create(MediaType.parse("image/png"), file));
                }
            }
        }

        MultipartBody requestBody = builder.build();
        Request request = new Request.Builder()
                .url(HttpConstants.API_URL)
                .post(requestBody)
                .build();

        Call mCall = mOkHttpClient.newCall(request);
        mCall.enqueue(callback);
    }


}
