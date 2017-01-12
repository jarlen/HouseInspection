package cn.jarlen.houseinspection.adapter;

import android.content.Context;

import org.json.JSONObject;

import cn.jarlen.houseinspection.ui.BannerView;
import cn.jarlen.houseinspection.ui.ProblemView;
import cn.jarlen.richcommon.adapter.multiple.RvMultiAdapter;
import cn.jarlen.richcommon.adapter.multiple.RvMultiItemManager;

/**
 * DESCRIBE:
 * Created by hjl on 2016/11/3.
 */

public class ProblemAdapter extends RvMultiAdapter<JSONObject> {

    public ProblemAdapter(Context context) {
        super(context);
    }

    @Override
    protected void preMultiItemView(RvMultiItemManager itemManager) {
        itemManager.addMultiItemView(new ProblemView(mContext));
        itemManager.addMultiItemView(new BannerView(mContext));
    }
}
