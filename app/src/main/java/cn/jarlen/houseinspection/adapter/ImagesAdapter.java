package cn.jarlen.houseinspection.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import cn.jarlen.houseinspection.R;
import cn.jarlen.houseinspection.utils.ImageUtils;
import cn.jarlen.richcommon.adapter.RvCommonAdapter;
import cn.jarlen.richcommon.adapter.RvViewHolder;

/**
 * DESCRIBE:
 * Created by hjl on 2016/11/21.
 */

public class ImagesAdapter extends RvCommonAdapter<String> {

    private int maxNumber;
    private OnItemClickListener mOnItemClickListener;
    private OnItemRemoveListener mOnItemRemoveListener;


    public ImagesAdapter(Context context, int maxNumber) {
        super(context);
        this.maxNumber = maxNumber;

        if (listData.size() < maxNumber + 1) {
            listData.add("add");
        }
    }

    public void addImages(ArrayList<String> mSelectPath) {
        if (mSelectPath == null) {
            return;
        }

        this.listData = mSelectPath;
        if (listData.size() < maxNumber) {
            listData.add("add");
        }
        notifyDataSetChanged();
    }

    public void removeImage(int position) {
        if (position > listData.size()) {
            return;
        }

        listData.remove(position);
        notifyDataSetChanged();
    }

    public List<String> getData() {
        List<String> temp = new ArrayList<>();
        for (int index = 0; index < listData.size(); index++) {
            if ("add".equals(listData.get(index))) {
                continue;
            }
            temp.add(listData.get(index));
        }
        return temp;
    }

    @Override
    public void onBindView(RvViewHolder viewHolder, String item) {

        ImageView image = viewHolder.getView(R.id.iv_image);
        ImageView delete = viewHolder.getView(R.id.delete_image_item);

        final int position = viewHolder.getLayoutPosition();

        if (listData.size() <= maxNumber) {

            if ("add".equals(listData.get(position))) {
                image.setImageResource(R.drawable.pic_add_selector);
                delete.setVisibility(View.GONE);
            } else {
                Bitmap bitmap = ImageUtils.getBitmapByPath(item);
                image.setImageBitmap(bitmap);
                delete.setVisibility(View.VISIBLE);
            }
        }

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListener.onItemClick(position);
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listData.size() <= maxNumber && !listData.contains("add")) {
                    listData.add("add");
                }
                mOnItemRemoveListener.onItemRemove(position);
            }
        });
    }

    @Override
    public int getLayoutResId(int viewType) {
        return R.layout.image_item;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public interface OnItemRemoveListener {
        void onItemRemove(int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        if (null != onItemClickListener) {
            this.mOnItemClickListener = onItemClickListener;
        }
    }

    public void setmOnItemRemoveListener(OnItemRemoveListener onItemRemoveListener) {
        if (null != onItemRemoveListener) {
            this.mOnItemRemoveListener = onItemRemoveListener;
        }
    }

}
