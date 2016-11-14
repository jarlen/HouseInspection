package cn.jarlen.houseinspection;

import android.app.Application;

import com.baidu.mapapi.SDKInitializer;
import com.tencent.bugly.Bugly;

import cn.sharesdk.framework.ShareSDK;

/**
 * DESCRIBE:
 * Created by hjl on 2016/11/7.
 */

public class UIApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        SDKInitializer.initialize(getApplicationContext());
        ShareSDK.initSDK(getApplicationContext());
        Bugly.init(getApplicationContext(),"177cf5e319",false);
    }
}
