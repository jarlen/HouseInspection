package cn.jarlen.houseinspection;

import android.app.Application;

import com.baidu.mapapi.SDKInitializer;

/**
 * DESCRIBE:
 * Created by hjl on 2016/11/7.
 */

public class UIApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        SDKInitializer.initialize(getApplicationContext());
    }
}
