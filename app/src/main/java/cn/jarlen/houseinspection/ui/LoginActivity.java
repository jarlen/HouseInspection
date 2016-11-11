package cn.jarlen.houseinspection.ui;

import android.view.View;
import android.widget.TextView;

import java.util.HashMap;

import cn.jarlen.houseinspection.R;
import cn.jarlen.houseinspection.data.User;
import cn.jarlen.richcommon.ui.BaseActivity;
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

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void onBindView() {
        loginSina = (TextView) findViewById(R.id.login_sina);
        loginSina.setOnClickListener(this);
        loginQQ = (TextView) findViewById(R.id.login_qq);
        loginQQ.setOnClickListener(this);
    }

    @Override
    protected void preBindView() {

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

        User.platform = db.getPlatformNname();
        User.userID = db.getUserId();
        User.nickName = db.getUserName();
        User.icon = db.getUserIcon();

        User.token = db.getToken();
        User.expiresIn = db.getExpiresIn();
        User.expiresTime = db.getExpiresTime();
    }

    @Override
    public void onError(Platform platform, int i, Throwable throwable) {

    }

    @Override
    public void onCancel(Platform platform, int i) {

    }
}
