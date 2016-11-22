package cn.jarlen.houseinspection.adapter;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import cn.jarlen.houseinspection.HomeActivity;
import cn.jarlen.houseinspection.R;
import cn.jarlen.houseinspection.data.Problem;
import cn.jarlen.houseinspection.http.HttpConstants;
import cn.jarlen.houseinspection.ui.PhotoViewActivity;
import cn.jarlen.houseinspection.utils.TimeUtil;
import cn.jarlen.richcommon.adapter.RvCommonAdapter;
import cn.jarlen.richcommon.adapter.RvViewHolder;

/**
 * DESCRIBE:
 * Created by hjl on 2016/11/3.
 */

public class ProblemAdapter extends RvCommonAdapter<Problem> {

    private HomeActivity activity;

    public ProblemAdapter(Context context) {
        super(context);
        activity = (HomeActivity) context;
    }

    @Override
    public void onBindView(RvViewHolder viewHolder, Problem item) {
        TextView problemTitle = viewHolder.getView(R.id.problem_title);
        TextView problemStatusTag = viewHolder.getView(R.id.problem_status_tag);
        TextView author = viewHolder.getView(R.id.author);
        TextView problemPeriod = viewHolder.getView(R.id.problem_period);
        TextView clickTimes = viewHolder.getView(R.id.click_times);

        TextView time = viewHolder.getView(R.id.time);

        ImageView imageOne = viewHolder.getView(R.id.image_one);
        ImageView imageTwo = viewHolder.getView(R.id.image_two);
        ImageView imageThree = viewHolder.getView(R.id.image_three);

        imageOne.setVisibility(View.INVISIBLE);
        imageOne.setOnClickListener(null);
        imageTwo.setVisibility(View.INVISIBLE);
        imageTwo.setOnClickListener(null);
        imageThree.setVisibility(View.INVISIBLE);
        imageThree.setOnClickListener(null);

        problemTitle.setText(item.getDescribe());
        switch (item.getStatus()) {
            case 0:
                problemStatusTag.setBackgroundResource(R.drawable.shape_problem_unsolved);
                problemStatusTag.setText("未解决");
                break;
            case 1:
                problemStatusTag.setBackgroundResource(R.drawable.shape_problem_solving);
                problemStatusTag.setText("解决中");
                break;
            case 2:
                problemStatusTag.setBackgroundResource(R.drawable.shape_problem_solved);
                problemStatusTag.setText("已解决");
                break;
            default:
                problemStatusTag.setBackgroundResource(R.drawable.shape_problem_other);
                problemStatusTag.setText("其他");
                break;
        }

        String pics = item.getThumbs();
        if (!TextUtils.isEmpty(pics)) {
            final String[] imgs = pics.split(";");

            if (imgs.length <= 0) {
                imageOne.setVisibility(View.VISIBLE);
                imageOne.setBackgroundColor(Color.parseColor("0FF0000"));
            }

            if (imgs.length >= 1) {
                imageOne.setVisibility(View.VISIBLE);
                imageOne.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Bundle bundle = new Bundle();
                        bundle.putString("url:image", HttpConstants.IMAGE_URL+imgs[0]);
                        activity.startActivity(PhotoViewActivity.class, bundle);
                    }
                });
                Glide.with(mContext.getApplicationContext())
                        .load(HttpConstants.IMAGE_URL + imgs[0])
                        .placeholder(R.drawable.icon_default)
                        .into(imageOne);
            }

            if (imgs.length >= 2) {
                imageTwo.setVisibility(View.VISIBLE);
                imageTwo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Bundle bundle = new Bundle();
                        bundle.putString("url:image", HttpConstants.IMAGE_URL+imgs[1]);
                        activity.startActivity(PhotoViewActivity.class, bundle);
                    }
                });
                Glide.with(mContext.getApplicationContext())
                        .load(HttpConstants.IMAGE_URL + imgs[1])
                        .placeholder(R.drawable.icon_default)
                        .into(imageTwo);
            }

            if (imgs.length >= 3) {
                imageThree.setVisibility(View.VISIBLE);
                imageThree.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Bundle bundle = new Bundle();
                        bundle.putString("url:image", HttpConstants.IMAGE_URL+imgs[2]);
                        activity.startActivity(PhotoViewActivity.class, bundle);
                    }
                });
                Glide.with(mContext.getApplicationContext())
                        .load(HttpConstants.IMAGE_URL + imgs[2])
                        .placeholder(R.drawable.icon_default)
                        .into(imageThree);
            }
        } else {
            imageOne.setVisibility(View.VISIBLE);
            imageOne.setBackgroundColor(Color.parseColor("0xFF0000"));
        }

        String period = item.getEstatename() + item.getPeriod() + "期";
        problemPeriod.setText(period);
        author.setText(item.getAuthor());
        clickTimes.setText("点击" + item.getClicks() + "次");

        long dateTime = item.getCreateTime();
        time.setText(TimeUtil.getDateByFormat(dateTime * 1000, cn.jarlen.richcommon.utils.TimeUtil.Y_M_D));
    }

    @Override
    public int getLayoutResId() {
        return R.layout.layout_problems_item;
    }
}
