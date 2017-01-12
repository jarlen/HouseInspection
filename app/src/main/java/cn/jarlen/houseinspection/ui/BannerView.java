package cn.jarlen.houseinspection.ui;

import android.content.Context;
import android.support.annotation.NonNull;

import cn.jarlen.houseinspection.data.Problem;
import cn.jarlen.richcommon.adapter.RvViewHolder;
import cn.jarlen.richcommon.adapter.multiple.BaseRvMultiItemView;

/**
 * DESCRIBE:
 * Created by hjl on 2017/1/12.
 */

public class BannerView extends BaseRvMultiItemView<Problem> {

    public BannerView(Context context) {
        super(context);
    }

    @Override
    protected int getLayoutResId() {
        return 0;
    }

    @Override
    protected void onBindView(RvViewHolder viewHolder, Problem item) {

    }

    @Override
    protected boolean isForViewType(@NonNull Problem item, int position) {
        return false;
    }
}
