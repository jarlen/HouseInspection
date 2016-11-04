package cn.jarlen.houseinspection.ui;

import cn.jarlen.houseinspection.R;
import cn.jarlen.houseinspection.base.BKBaseActivity;

/**
 * DESCRIBE:
 * Created by hjl on 2016/11/4.
 */

public class ProblemSubActivity extends BKBaseActivity {
    @Override
    protected void onBKBindView() {
        showBackView();
        showTitleView(R.string.problem_submit);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_problem_sub;
    }

    @Override
    protected void preBindViews() {
    }
}
