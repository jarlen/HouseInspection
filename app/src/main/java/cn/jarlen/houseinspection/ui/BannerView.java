package cn.jarlen.houseinspection.ui;

import android.content.Context;
import android.support.annotation.NonNull;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cn.jarlen.houseinspection.data.Problem;
import cn.jarlen.richcommon.adapter.RvViewHolder;
import cn.jarlen.richcommon.adapter.multiple.BaseRvMultiItemView;

/**
 * DESCRIBE:
 * Created by hjl on 2017/1/12.
 */

public class BannerView extends BaseRvMultiItemView<JSONObject> {

    public BannerView(Context context) {
        super(context);
    }

    @Override
    protected int getLayoutResId() {
        return 0;
    }

    @Override
    protected void onBindView(RvViewHolder viewHolder, JSONObject item) {

    }

    @Override
    protected boolean isForViewType(@NonNull JSONObject item, int position) {
        if (item.has("type")) {
            try {
                return "ad".equals(item.getString("type"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
