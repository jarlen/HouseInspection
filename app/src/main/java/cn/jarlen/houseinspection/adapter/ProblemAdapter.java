package cn.jarlen.houseinspection.adapter;

import android.content.Context;

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

    }

    @Override
    public int getLayoutResId() {
        return 0;
    }
}
