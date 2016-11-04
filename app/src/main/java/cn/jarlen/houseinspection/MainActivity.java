package cn.jarlen.houseinspection;

import android.view.View;

import cn.jarlen.houseinspection.base.BKBaseActivity;
import cn.jarlen.houseinspection.ui.ProblemSubActivity;

public class MainActivity extends BKBaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void preBindViews() {

    }

    @Override
    protected void onBKBindView() {
        showRightOne(R.drawable.add, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(ProblemSubActivity.class, null);
            }
        });
    }
}
