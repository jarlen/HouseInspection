package cn.jarlen.houseinspection.adapter;

import android.content.Context;
import android.widget.TextView;

import cn.jarlen.houseinspection.R;
import cn.jarlen.houseinspection.data.Problem;
import cn.jarlen.houseinspection.utils.TimeUtil;
import cn.jarlen.richcommon.adapter.RvCommonAdapter;
import cn.jarlen.richcommon.adapter.RvViewHolder;

/**
 * DESCRIBE:
 * Created by hjl on 2016/11/3.
 */

public class ProblemAdapter extends RvCommonAdapter<Problem> {

    public ProblemAdapter(Context context) {
        super(context);
    }

    @Override
    public void onBindView(RvViewHolder viewHolder, Problem item) {
        TextView problemTitle = viewHolder.getView(R.id.problem_title);
        TextView problemStatusTag = viewHolder.getView(R.id.problem_status_tag);
        TextView author = viewHolder.getView(R.id.author);
        TextView problemPeriod = viewHolder.getView(R.id.problem_period);
        TextView clickTimes = viewHolder.getView(R.id.click_times);

        TextView time = viewHolder.getView(R.id.time);

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
        String period = item.getEstatename() + item.getPeriod() + "期";
        problemPeriod.setText(period);
        author.setText(item.getAuthor());
        clickTimes.setText("点击"+item.getClicks()+"次");

        long dateTime = item.getCreateTime();
        time.setText(TimeUtil.getDateByFormat(dateTime*1000, cn.jarlen.richcommon.utils.TimeUtil.Y_M_D));
    }

    @Override
    public int getLayoutResId() {
        return R.layout.layout_problems_item;
    }
}
