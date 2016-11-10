package cn.jarlen.houseinspection.adapter;

import android.content.Context;
import android.widget.TextView;

import cn.jarlen.houseinspection.R;
import cn.jarlen.houseinspection.data.Problem;
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
        problemTitle.setText(item.getTitle());
    }

    @Override
    public int getLayoutResId() {
        return R.layout.layout_problems_item;
    }
}
