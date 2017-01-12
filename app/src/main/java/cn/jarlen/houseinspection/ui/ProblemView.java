package cn.jarlen.houseinspection.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;

import cn.jarlen.houseinspection.R;
import cn.jarlen.houseinspection.data.Problem;
import cn.jarlen.houseinspection.http.HttpConstants;
import cn.jarlen.houseinspection.utils.TimeUtil;
import cn.jarlen.richcommon.adapter.RvViewHolder;
import cn.jarlen.richcommon.adapter.multiple.BaseRvMultiItemView;

/**
 * DESCRIBE:
 * Created by hjl on 2017/1/12.
 */

public class ProblemView extends BaseRvMultiItemView<JSONObject> {

    public ProblemView(Context context) {
        super(context);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.layout_problems_item;
    }

    @Override
    protected void onBindView(RvViewHolder viewHolder, JSONObject item) {
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

        String describe = "";
        int status = -1;
        String pics = "";
        String period = "";
        String authorName = "";
        int clicks = 0;
        long dateTime = 0;

        try {
            if (item.has("describe")) {
                describe = item.getString("describe");
            }

            if (item.has("status")) {
                status = item.getInt("status");
            }

            if (item.has("thumbs")) {
                pics = item.getString("thumbs");
            }

            if (item.has("estatename")) {
                period += item.getString("estatename");
            }

            if (item.has("period")) {
                period = period + item.getInt("period") + "期";
            }

            if (item.has("author")) {
                authorName = item.getString("author");
            }

            if (item.has("clicks")) {
                clicks = item.getInt("clicks");
            }

            if (item.has("create_at")) {
                dateTime = item.getLong("create_at");
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }

        problemTitle.setText(describe);

        switch (status) {
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
                        bundle.putString("url:image", HttpConstants.IMAGE_URL + imgs[0]);
                        Intent intent = new Intent(mContext, PhotoViewActivity.class);
                        intent.putExtras(bundle);
                        mContext.startActivity(intent);
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
                        bundle.putString("url:image", HttpConstants.IMAGE_URL + imgs[1]);

                        Intent intent = new Intent(mContext, PhotoViewActivity.class);
                        intent.putExtras(bundle);
                        mContext.startActivity(intent);
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
                        bundle.putString("url:image", HttpConstants.IMAGE_URL + imgs[2]);
                        Intent intent = new Intent(mContext, PhotoViewActivity.class);
                        intent.putExtras(bundle);
                        mContext.startActivity(intent);
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

        problemPeriod.setText(period);
        author.setText(authorName);
        clickTimes.setText("点击" + clicks + "次");
        time.setText(TimeUtil.getDateByFormat(dateTime * 1000, cn.jarlen.richcommon.utils.TimeUtil.Y_M_D));
    }

    @Override
    protected boolean isForViewType(@NonNull JSONObject item, int position) {

        if (item.has("type")) {
            try {
                return "problem".equals(item.getString("type"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
