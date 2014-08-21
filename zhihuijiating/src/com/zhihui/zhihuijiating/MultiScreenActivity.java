package com.zhihui.zhihuijiating;

import com.example.helloapp.R;
import com.utils.inject.From;
import com.utils.inject.Injector;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class MultiScreenActivity extends BaseActivity implements OnClickListener {

	@From(R.id.curtain_back)
	ImageView curtain_back;

	@From(R.id.video_control)
	ImageView video_control;
	@From(R.id.office_documents)
	ImageView office_documents;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_multiscreen);

		curtain_back.setOnClickListener(this);

		video_control.setOnClickListener(this);
		office_documents.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.curtain_back:
			finish();
			break;
		case R.id.video_control:
			startApp("com.bubblesoft.android.bubbleupnp",
					"com.bubblesoft.android.bubbleupnp.MainActivity");
			break;
		case R.id.office_documents:
//			send(1, 1);
//			send(2, 1);
			break;
	
		}
	}

	void startApp(final String pkg, final String cls) {

		Log.d("startApp", pkg + "/" + cls);
		ComponentName componet = new ComponentName(pkg, cls);

		Intent i = new Intent();
		i.setComponent(componet);
		startActivity(i);
	}

	/**
	 * 
	 * @param port
	 *            0,1,2 jidianqi
	 * @param state
	 *            open 0, close 1.
	 */
	void send(final int port, final int state) {
		new Thread() {
			public void run() {
				// UdpHelper.send("RELAY_CTL=admin,3,0");
				
				UdpHelper.send("RELAY_CTL=admin," + port + "," + state);
				
			}
		}.start();
	}
}
