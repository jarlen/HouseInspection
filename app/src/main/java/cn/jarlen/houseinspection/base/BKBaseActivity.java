package cn.jarlen.houseinspection.base;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.jarlen.houseinspection.R;
import cn.jarlen.richcommon.ui.BaseActivity;

/**
 * DESCRIBE:
 * Created by hjl on 2016/11/3.
 */

public abstract class BKBaseActivity extends BaseActivity {

    @BindView(R.id.back)
    ImageView back;

    @BindView(R.id.title)
    TextView title;

    @BindView(R.id.menu_right_one)
    ImageView menuRightOne;

    @Override
    protected void onBindViews() {
        ButterKnife.bind(this);
        onBKBindView();
    }

    protected void showBackView() {
        this.back.setVisibility(View.VISIBLE);
        this.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected void preBindViews() {
        overridePendingTransition(R.anim.anim_right_in,R.anim.open_right_exit);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(0,R.anim.slide_right_out);
    }

    protected void hideBackView() {
        this.back.setVisibility(View.GONE);
        this.back.setOnClickListener(null);
    }


    protected void showTitleView(String title) {
        this.title.setVisibility(View.VISIBLE);
        this.title.setText(title);
    }

    protected void showTitleView(int titleRes) {
        this.title.setVisibility(View.VISIBLE);
        this.title.setText(titleRes);
    }

    protected void hideTitleView() {
        this.title.setVisibility(View.INVISIBLE);
    }

    protected void showRightOne(int resId, View.OnClickListener listener) {
        menuRightOne.setVisibility(View.VISIBLE);
        menuRightOne.setImageResource(resId);
        menuRightOne.setOnClickListener(listener);
    }

    protected void hideRightOne() {
        menuRightOne.setVisibility(View.INVISIBLE);
        menuRightOne.setOnClickListener(null);
    }

    protected void startActivity(Class<?> activity, Bundle args){
        Intent intent = new Intent(this,activity);
        if(args != null){
            intent.putExtras(args);
        }
        startActivity(intent);
    }

    protected void startActivityForResult(Class<?> activity,int requestCode,Bundle args){
        Intent intent = new Intent(this,activity);
        if(args != null){
            intent.putExtras(args);
        }
        startActivityForResult(intent,requestCode,args);
    }

    protected abstract void onBKBindView();
}
