package cn.jarlen.houseinspection.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import cn.jarlen.houseinspection.R;
import cn.jarlen.houseinspection.base.BKBaseActivity;
import cn.jarlen.richcommon.view.PhotoView;

public class PhotoViewActivity extends BKBaseActivity {

    @BindView(R.id.preview)
    PhotoView photoView;

    @Override
    protected void onBKBindView() {
        showBackView();
        showTitleView("照片查看");
        Intent intent = getIntent();
        if (intent == null) {
            return;
        }

        Bundle bundle = intent.getExtras();
        if (bundle != null) {

            if (bundle.containsKey("image")) {
                String imgePath = (String) bundle.get("image");

                BitmapFactory.Options opts = new BitmapFactory.Options();
                opts.inJustDecodeBounds = true;

                Bitmap bitmap = BitmapFactory.decodeFile(imgePath);
                photoView.setImageBitmap(bitmap);
            } else if (bundle.containsKey("url:image")) {
                String imageUrl = (String) bundle.get("url:image");
                Glide.with(this.getApplicationContext())
                        .load(imageUrl)
                        .placeholder(R.drawable.icon_default)
                        .into(photoView);
            }
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_photo_view;
    }
}
