package com.why.photoaibum.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.provider.MediaStore.Images.Thumbnails;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView.LayoutParams;
import android.widget.BaseAdapter;

import com.why.photoaibum.PhotoGridItem;
import com.why.photoaibum.entities.PhotoAibum;
import com.why.photoaibum.entities.PhotoItem;

public class PhotoAdappter extends BaseAdapter {
    private Context context;
    private PhotoAibum aibum;

    public PhotoAdappter(Context context, PhotoAibum aibum) {
        this.context = context;
        this.aibum = aibum;
    }

    @Override
    public int getCount() {
        return aibum.getBitList().size();
    }

    @Override
    public PhotoItem getItem(int position) {
        return aibum.getBitList().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        PhotoGridItem item;
        if (convertView == null) {
            item = new PhotoGridItem(context);
            item.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
                    LayoutParams.MATCH_PARENT));
        } else {
            item = (PhotoGridItem) convertView;
        }
        // 通过ID 加载缩略图

        Bitmap bitmap = MediaStore.Images.Thumbnails.getThumbnail(context.getContentResolver(),
                aibum.getBitList().get(position).getPhotoID(), Thumbnails.MICRO_KIND, null);
        item.SetBitmap(bitmap);
        boolean flag = aibum.getBitList().get(position).isSelect();
        item.setChecked(flag);
        if(aibum.checkCount >= 4 && !item.isChecked()) {
            item.setCheckVisiable(false);
        } else {
            item.setCheckVisiable(true);
        }

        return item;
    }
}
