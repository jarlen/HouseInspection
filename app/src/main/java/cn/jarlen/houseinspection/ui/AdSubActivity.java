package cn.jarlen.houseinspection.ui;

import cn.jarlen.houseinspection.R;
import cn.jarlen.houseinspection.base.BKBaseActivity;
import cn.jarlen.houseinspection.data.User;

public class AdSubActivity extends BKBaseActivity {

    @Override
    protected void onBKBindView() {

        if (!User.isUserLogin() || User.getUserCache().getFlag() != 1) {
            finish();
            return;
        }
        showBackView();
        showTitleView("录入广告");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_ad_sub;
    }
}
