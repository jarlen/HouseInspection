package cn.jarlen.houseinspection.ui;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;

import butterknife.BindView;
import cn.jarlen.houseinspection.R;
import cn.jarlen.houseinspection.base.BKBaseActivity;
import cn.jarlen.houseinspection.data.ProblemSubmit;
import cn.jarlen.houseinspection.data.User;
import cn.jarlen.richcommon.utils.ToastUtil;

/**
 * DESCRIBE:
 * Created by hjl on 2016/11/4.
 */

public class ProblemSubActivity extends BKBaseActivity implements View.OnClickListener {

    @BindView(R.id.getlocation)
    ImageView getLocation;

    @BindView(R.id.house_addr)
    EditText houseAddr;

    @BindView(R.id.house_area)
    EditText houseArea;

    @BindView(R.id.house_period)
    EditText housePeriod;

    @BindView(R.id.house_building)
    EditText houseBuilding;

    @BindView(R.id.house_building_unit)
    EditText houseBuildingUnit;

    @BindView(R.id.house_room_no)
    EditText houseRoomNo;

    @BindView(R.id.problem_desc)
    EditText problemDesc;

    @BindView(R.id.submitter_name)
    EditText submitterName;

    @BindView(R.id.submitter_phone)
    EditText submitterPhone;

    @BindView(R.id.anonCB)
    CheckBox anonCB;

    private ProblemSubmit submitData;


    @Override
    protected void onBKBindView() {
        showBackView();
        showTitleView(R.string.problem_submit);
        findViewById(R.id.submit).setOnClickListener(this);
        getLocation.setOnClickListener(this);
        submitterName.setText(User.getUserCache().getUserName());
        houseArea.setText("万科城");
        housePeriod.setText("" + 1);

        Log.e("jarlen", "" + (System.currentTimeMillis() - time));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_problem_sub;
    }

    long time;

    @Override
    protected void preBindView() {
        time = System.currentTimeMillis();
    }


    private boolean preSubmit() {

        if (TextUtils.isEmpty(houseArea.getText())) {
            ToastUtil.makeToast(this).setText("请输入楼盘名称").show();
            return false;
        }

        if (submitData == null) {
            submitData = new ProblemSubmit();
        }
        submitData.setEstateName(houseArea.getText().toString());

        if (TextUtils.isEmpty(housePeriod.getText())) {
            ToastUtil.makeToast(this).setText("请输入楼盘期数").show();
            return false;
        }

        submitData.setEstatePeriod(housePeriod.getText().toString());

        if (TextUtils.isEmpty(problemDesc.getText())) {
            ToastUtil.makeToast(this).setText("请输入问题描述").show();
            return false;
        }

        if (problemDesc.getText().length() < 20) {
            ToastUtil.makeToast(this).setText("问题描述不少于20字").show();
            return false;
        }

        submitData.setContent(problemDesc.getText().toString());


        return true;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.getlocation:
                startActivity(LoactionChooseActivity.class, null);
                break;
            case R.id.submit:
                if (!preSubmit()) {
                    return;
                }

                break;
            default:

                break;
        }
    }
}
