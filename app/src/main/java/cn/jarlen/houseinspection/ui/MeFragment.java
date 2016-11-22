package cn.jarlen.houseinspection.ui;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import cn.jarlen.houseinspection.R;
import cn.jarlen.houseinspection.base.BKBaseFragment;
import cn.jarlen.houseinspection.data.User;
import cn.jarlen.houseinspection.utils.GlideCircleTransform;

/**
 * Created by jarlen on 2016/11/22.
 */
public class MeFragment extends BKBaseFragment {

    @BindView(R.id.user_icon)
    ImageView userIcon;

    @BindView(R.id.user_name)
    TextView userName;

    @BindView(R.id.version_name)
    TextView versionName;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_me;
    }

    @Override
    protected void onBindView() {
        User user = User.getUserCache();

        if (user == null || TextUtils.isEmpty(user.getUserName())) {
            userName.setText("路人甲");
        } else {
            userName.setText(user.getUserName());
        }

        if (user != null && !TextUtils.isEmpty(user.getAvatar())) {
            Glide.with(getActivity())
                    .load(user.getAvatar())
                    .transform(new GlideCircleTransform(getActivity()))
                    .placeholder(R.drawable.icon_default)
                    .into(userIcon);
        }
        else{
            Glide.with(getActivity())
                    .load("https://avatars1.githubusercontent.com/u/7521263?v=3&u=5a728c9a9a3011798133748292a80f8544f09005&s=400")
                    .transform(new GlideCircleTransform(getActivity()))
                    .placeholder(R.drawable.icon_default)
                    .into(userIcon);
        }

        try {
            PackageInfo packageInfo = getActivity().getPackageManager().getPackageInfo(getActivity().getPackageName(), 0);
            versionName.setText("当前版本:" + packageInfo.versionName);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        User user = User.getUserCache();
        if (user == null || TextUtils.isEmpty(user.getUserName())) {
            userName.setText("路人甲");
        } else {
            userName.setText(user.getUserName());
        }
    }
}
