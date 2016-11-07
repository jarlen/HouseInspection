package cn.jarlen.houseinspection.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import cn.jarlen.houseinspection.R;
import cn.jarlen.houseinspection.data.Problem;
import cn.jarlen.richcommon.adapter.SimpleBaseAdapter;
import cn.jarlen.richcommon.adapter.ViewHolder;

/**
 * DESCRIBE:
 * Created by hjl on 2016/11/3.
 */

public class ProblemAdapter extends RecyclerView.Adapter {

    public ProblemAdapter(Context context) {
        super(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
