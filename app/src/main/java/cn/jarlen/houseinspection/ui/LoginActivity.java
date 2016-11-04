package cn.jarlen.houseinspection.ui;

import android.view.View;
import android.widget.TextView;

import cn.jarlen.houseinspection.R;
import cn.jarlen.richcommon.ui.BaseActivity;

/**
 * DESCRIBE:
 * Created by jarlen on 2016/11/3.
 */

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private TextView loginQQ;
    private TextView loginWX;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void onBindViews() {
        loginWX = (TextView) findViewById(R.id.login_wx);
        loginWX.setOnClickListener(this);
        loginQQ = (TextView) findViewById(R.id.login_qq);
        loginQQ.setOnClickListener(this);
    }

    @Override
    protected void preBindViews() {

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.login_qq:

                break;
            case R.id.login_wx:

                break;
            default:

                break;
        }
    }
}
