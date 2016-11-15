package cn.jarlen.houseinspection.http;

import android.content.Context;

import okhttp3.OkHttpClient;

/**
 * DESCRIBE:
 * Created by hjl on 2016/11/15.
 */

public class OkHttpPatch {

    private static Context mContext;

    public static void initOkHttpPatch(Context context){
        mContext = context;
    }

    private OkHttpClient mOkHttpClient;

    private static OkHttpPatch okHttpPatch;

    private OkHttpPatch(){
        mOkHttpClient = new OkHttpClient();
    }

    public static OkHttpPatch getOkHttpPatch(){
        if(okHttpPatch == null){
            okHttpPatch = new OkHttpPatch();
        }
        return okHttpPatch;
    }



}
