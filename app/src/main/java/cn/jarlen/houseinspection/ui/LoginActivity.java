package cn.jarlen.houseinspection.ui;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.HashMap;

import cn.jarlen.houseinspection.R;
import cn.jarlen.houseinspection.data.User;
import cn.jarlen.houseinspection.http.BaseResponse;
import cn.jarlen.houseinspection.http.LoginResponse;
import cn.jarlen.houseinspection.http.OkHttpPatch;
import cn.jarlen.httppatch.okhttp.Callback2;
import cn.jarlen.richcommon.ui.BaseActivity;
import cn.jarlen.richcommon.utils.ToastUtil;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.PlatformDb;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;

/**
 * DESCRIBE:
 * Created by jarlen on 2016/11/3.
 */

public class LoginActivity extends BaseActivity implements View.OnClickListener, PlatformActionListener {

    private TextView loginQQ;
    private TextView loginSina;

    private Gson gson = new Gson();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void onBindView() {
        findViewById(R.id.bg).setOnClickListener(this);
        loginSina = (TextView) findViewById(R.id.login_sina);
        loginSina.setOnClickListener(this);
        loginQQ = (TextView) findViewById(R.id.login_qq);
        loginQQ.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
        overridePendingTransition(R.anim.zoom_in,R.anim.zoom_out);
        super.onBackPressed();
    }

    @Override
    protected void preBindView() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        overridePendingTransition(R.anim.zoom_in,R.anim.zoom_out);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.login_qq:
                Platform qq = ShareSDK.getPlatform(QQ.NAME);
                qq.setPlatformActionListener(this);
                qq.showUser(null);
                break;
            case R.id.login_sina:
                Platform sina = ShareSDK.getPlatform(SinaWeibo.NAME);
                sina.setPlatformActionListener(this);
                sina.showUser(null);
                break;
            case R.id.bg:
                    finish();
                break;
            default:

                break;
        }
    }

    @Override
    public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
        PlatformDb db = platform.getDb();
        if (db == null) {
            return;
        }

        OkHttpPatch.getOkHttpPatch().login(db, new Callback2() {
            @Override
            public void onResponse(String body) {

                LoginResponse loginResponse = gson.fromJson(body, LoginResponse.class);

                if (loginResponse.getStatus() == BaseResponse.RESPONSE_OPT_SUCCESS) {
                    User.setUserCache(loginResponse.getContent());
                    finish();
                } else if (loginResponse.getStatus() == BaseResponse.RESPONSE_ERROR_ACCOUNT) {
                    User.clearCache();
                    ToastUtil.makeToast(LoginActivity.this).setText(loginResponse.getMessage()).show();
                    LoginActivity.startLogin(LoginActivity.this);
                } else if (loginResponse.getStatus() == BaseResponse.RESPONSE_ERROR_PARAM) {
                    ToastUtil.makeToast(LoginActivity.this).setText(loginResponse.getMessage()).show();
                } else if (loginResponse.getStatus() == BaseResponse.RESPONSE_OPT_FAIL) {
                    ToastUtil.makeToast(LoginActivity.this).setText(loginResponse.getMessage()).show();
                }
            }

            @Override
            public void onFailure(Exception e) {
                ToastUtil.makeToast(LoginActivity.this).setText(e.toString()).show();
            }
        });

    }

    @Override
    public void onError(Platform platform, int i, Throwable throwable) {

    }

    @Override
    public void onCancel(Platform platform, int i) {

    }

    public static void startLogin(Context context) {
        Intent loginIntent = new Intent(context, LoginActivity.class);
        context.startActivity(loginIntent);
    }
}
