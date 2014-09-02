package com.why.photoaibum;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.why.photoaibum.adapter.PhotoAdappter;
import com.why.photoaibum.entities.PhotoAibum;
import com.why.photoaibum.entities.PhotoItem;

public class PhotoActivity extends Activity  {
	private GridView gv;
	private PhotoAibum aibum;
	private PhotoAdappter adapter;
	private TextView tv;
	private int chooseNum = 0;
	private Button btn_sure;
	private LayoutInflater inflater;
	
	private ArrayList<PhotoItem> gl_arr=new ArrayList<PhotoItem>();

    private static final String[] STORE_IMAGES = {
            MediaStore.Images.Media.DISPLAY_NAME, // 显示的名
            MediaStore.Images.Media.DATA,
            MediaStore.Images.Media.LONGITUDE, // 经度
            MediaStore.Images.Media._ID, // id
            MediaStore.Images.Media.BUCKET_ID, // dir id 目录
            MediaStore.Images.Media.BUCKET_DISPLAY_NAME // dir name 目录名字

    };
    private void getPhotoAlbum() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Cursor cursor = MediaStore.Images.Media.query(getContentResolver(),
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI, STORE_IMAGES);
                while (cursor.moveToNext()) {
                    String path=cursor.getString(1);
                    String id = cursor.getString(3);
                    String dir_id = cursor.getString(4);
                    String dir = cursor.getString(5);
                    Log.e("info", "id==="+id+"==dir_id=="+dir_id+"==dir=="+dir+"==path="+path);

                    aibum.setName(dir);
                    aibum.setBitmap(Integer.parseInt(id));
                    aibum.setCount(aibum.getCount() + 1);
                    aibum.getBitList().add(new PhotoItem(Integer.valueOf(id),path));
                    handler.sendEmptyMessage(0);
                }
                cursor.close();
            }
        }).start();
    }

    Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            adapter.notifyDataSetChanged();
        }
    };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_photoalbum_gridview);
		btn_sure=(Button)findViewById(R.id.btn_sure);
        aibum =  new PhotoAibum();
		/**获取已经选择的图片**/
//		for (int i = 0; i < aibum.getBitList().size(); i++) {
//			if(aibum.getBitList().get(i).isSelect()){
//				chooseNum++;
//			}
//		}
		gv =(GridView)findViewById(R.id.photo_gridview);
		adapter = new PhotoAdappter(this,aibum);
		gv.setAdapter(adapter);
		gv.setOnItemClickListener(gvItemClickListener);
		btn_sure.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Toast.makeText(PhotoActivity.this, paths.toString(), Toast.LENGTH_SHORT).show();
				Log.e("info", paths.toString());
			}
		});
        getPhotoAlbum();
	}
	private void inite(PhotoItem str,boolean isSeclect){//初始化被选中的图片的方法  将图片添加或者删除
		
		if (isSeclect) {
			btn_sure.setText("确定("+gl_arr.size()+")");
		}
	   else{
		btn_sure.setText("确定("+gl_arr.size()+")");
	}
	inflater = (LayoutInflater) this
			.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	DisplayMetrics dm = new DisplayMetrics();
	getWindowManager().getDefaultDisplay().getMetrics(dm);

}
	private ArrayList<String> paths=new ArrayList<String>();
	private ArrayList<String> ids=new ArrayList<String>();
	private OnItemClickListener gvItemClickListener = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			PhotoItem gridItem=aibum.getBitList().get(position);
			if( aibum.getBitList().get(position).isSelect()){
				aibum.getBitList().get(position).setSelect(false);
				paths.remove(aibum.getBitList().get(position).getPath());
				ids.remove(aibum.getBitList().get(position).getPhotoID()+"");
				gl_arr.remove(aibum.getBitList().get(position));
				chooseNum--;
				inite(aibum.getBitList().get(position), aibum.getBitList().get(position).isSelect());
			}else if(chooseNum < 4){
				aibum.getBitList().get(position).setSelect(true);
				ids.add(aibum.getBitList().get(position).getPhotoID()+"");
				paths.add(aibum.getBitList().get(position).getPath());
				gl_arr.add(aibum.getBitList().get(position));
				chooseNum++;
				inite(aibum.getBitList().get(position), aibum.getBitList().get(position).isSelect());
			}
            aibum.checkCount = chooseNum;
			adapter.notifyDataSetChanged();
		}
	};
}
