package cn.jarlen.houseinspection.adapter;

import android.content.Context;
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

public class ProblemAdapter extends SimpleBaseAdapter<Problem> {

    public ProblemAdapter(Context context) {
        super(context);
    }

    @Override
    public int getCount() {
        return 10;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = ViewHolder.getViewHolder(context,parent,convertView,position, R.layout.layout_problems_item);



        return viewHolder.getConvertView();
    }
}
