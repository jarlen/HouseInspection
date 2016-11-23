package cn.jarlen.houseinspection.ui;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.jarlen.houseinspection.R;
import cn.jarlen.houseinspection.adapter.ImagesAdapter;
import cn.jarlen.houseinspection.base.BKBaseActivity;
import cn.jarlen.houseinspection.data.ProblemSubmit;
import cn.jarlen.houseinspection.data.User;
import cn.jarlen.houseinspection.http.BaseResponse;
import cn.jarlen.houseinspection.http.OkHttpPatch;
import cn.jarlen.httppatch.okhttp.Callback2;
import cn.jarlen.richcommon.utils.ToastUtil;
import me.nereo.multi_image_selector.MultiImageSelector;

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

    @BindView(R.id.recycleView)
    RecyclerView imageRecycleView;

    private ProblemSubmit submitData;
    private boolean isLoading = false;

    private Gson gson = new Gson();

    private final int maxNumber = 3;
    private ImagesAdapter imagesAdapter;
    protected static final int REQUEST_STORAGE_READ_ACCESS_PERMISSION = 101;
    private static final int REQUEST_IMAGE = 2;


    @Override
    protected void onBKBindView() {
        showBackView();
        showTitleView(R.string.problem_submit);
        findViewById(R.id.submit).setOnClickListener(this);
        getLocation.setOnClickListener(this);

        houseArea.setText("万科城");
        houseArea.setSelection("万科城".length());
        housePeriod.setText("" + 1);

        imageRecycleView.setLayoutManager(new GridLayoutManager(this, maxNumber));
        imagesAdapter = new ImagesAdapter(this, maxNumber);
        imageRecycleView.setAdapter(imagesAdapter);
        imagesAdapter.setOnItemClickListener(new ImagesAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                if (position == imagesAdapter.getItemCount() - 1) {
                    pickImage();
                } else {
                    Bundle bundle = new Bundle();
                    bundle.putString("image",imagesAdapter.getData().get(position));
                    startActivity(PhotoViewActivity.class,bundle);
                }
            }
        });
        imagesAdapter.setmOnItemRemoveListener(new ImagesAdapter.OnItemRemoveListener() {
            @Override
            public void onItemRemove(int position) {
                imagesAdapter.removeImage(position);
            }
        });
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

        submitData.setDescribe(problemDesc.getText().toString());
        List<String> tempPics = imagesAdapter.getData();

        submitData.setPics(tempPics);

        if (!TextUtils.isEmpty(houseBuilding.getText())) {
            submitData.setBuildingNo(houseBuilding.getText().toString());
        }

        if (!TextUtils.isEmpty(houseBuildingUnit.getText())) {
            submitData.setBuildingUnit(houseBuildingUnit.getText().toString());
        }

        if (!TextUtils.isEmpty(houseRoomNo.getText())) {
            submitData.setRoomNo(houseRoomNo.getText().toString());
        }

        if (!TextUtils.isEmpty(submitterName.getText())) {
            submitData.setContactor(submitterName.getText().toString());
        }

        if (!TextUtils.isEmpty(submitterPhone.getText())) {
            submitData.setPhone(submitterPhone.getText().toString());
        }

        submitData.setAnon(anonCB.isChecked());
        return true;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.getlocation:
                startActivity(LoactionChooseActivity.class, null);
                break;
            case R.id.submit:

                if (!User.isUserLogin()) {
                    LoginActivity.startLogin(this);
                    return;
                }

                if (isLoading) {
                    ToastUtil.makeToast(this).setText("正在提交中...").show();
                    return;
                }

                if (!preSubmit()) {
                    return;
                }
                isLoading = true;
                OkHttpPatch.getOkHttpPatch().submit(submitData, callback2);
                break;
            default:

                break;
        }
    }

    private Callback2 callback2 = new Callback2() {
        @Override
        public void onResponse(String body) {
            isLoading = false;

            BaseResponse baseResponse = gson.fromJson(body, BaseResponse.class);
            if (baseResponse.getStatus() == BaseResponse.RESPONSE_OPT_SUCCESS) {
                ToastUtil.makeToast(ProblemSubActivity.this).setText("提交成功").show();

            } else {
                ToastUtil.makeToast(ProblemSubActivity.this).setText(baseResponse.getMessage()).show();
            }
        }

        @Override
        public void onFailure(Exception e) {
            isLoading = false;
            ToastUtil.makeToast(ProblemSubActivity.this).setText(e.getMessage()).show();
        }
    };

    private void pickImage() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN // Permission was added in APIConstants Level 16
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermission(Manifest.permission.READ_EXTERNAL_STORAGE,
                    getString(R.string.permission_rationale),
                    REQUEST_STORAGE_READ_ACCESS_PERMISSION);
        } else {

            ArrayList<String> data = (ArrayList<String>) imagesAdapter.getData();

            MultiImageSelector selector = MultiImageSelector.create(ProblemSubActivity.this);
            selector.showCamera(true);
            selector.count(maxNumber);
            selector.multi();
            selector.origin(data);
            selector.start(ProblemSubActivity.this, REQUEST_IMAGE);
        }
    }

    private void requestPermission(final String permission, String rationale, final int requestCode) {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, permission)) {
            new AlertDialog.Builder(this)
                    .setTitle(R.string.permission_dialog_title)
                    .setMessage(rationale)
                    .setPositiveButton(R.string.permission_dialog_ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(ProblemSubActivity.this, new String[]{permission}, requestCode);
                        }
                    })
                    .setNegativeButton(R.string.permission_dialog_cancel, null)
                    .create().show();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{permission}, requestCode);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_OK) {
            return;
        }

        if (requestCode == REQUEST_IMAGE) {
            ArrayList<String> temp = data.getStringArrayListExtra(MultiImageSelector.EXTRA_RESULT);
            imagesAdapter.addImages(temp);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onResume() {
        super.onResume();
        User user = User.getUserCache();
        if(user != null && !TextUtils.isEmpty(user.getUserName())){
            submitterName.setText(user.getUserName());
        }
    }
}
