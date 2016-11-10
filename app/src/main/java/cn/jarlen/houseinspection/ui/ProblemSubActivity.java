package cn.jarlen.houseinspection.ui;

import android.view.View;
import android.widget.ImageView;

import butterknife.BindView;
import cn.jarlen.houseinspection.R;
import cn.jarlen.houseinspection.base.BKBaseActivity;

/**
 * DESCRIBE:
 * Created by hjl on 2016/11/4.
 */

public class ProblemSubActivity extends BKBaseActivity implements View.OnClickListener {

    @BindView(R.id.getlocation)
    ImageView getLocation;

    @Override
    protected void onBKBindView() {
        showBackView();
        showTitleView(R.string.problem_submit);
        getLocation.setOnClickListener(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_problem_sub;
    }

    @Override
    protected void preBindView() {
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.getlocation:
                startActivity(LoactionChooseActivity.class,null);
                break;
            default:

                break;
        }
    }
}
