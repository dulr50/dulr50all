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

public class CurtainActivity extends BaseActivity implements OnClickListener {

	@From(R.id.curtain_back)
	ImageView curtain_back;

	@From(R.id.curtain_open)
	ImageView curtain_open;
	@From(R.id.curtain_suspend)
	ImageView curtain_suspend;

	@From(R.id.curtain_close)
	ImageView curtain_close;
	@From(R.id.curtain_close2)
	ImageView curtain_close2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_curtain);

		curtain_back.setOnClickListener(this);

		curtain_open.setOnClickListener(this);
		curtain_suspend.setOnClickListener(this);

		curtain_close.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.curtain_back:
			finish();
			break;
		case R.id.curtain_open:
			send(1, 0);
			// TODO
			break;
		case R.id.curtain_suspend:
			send(1, 1);
			send(2, 1);
			break;
		case R.id.curtain_close:

			send(2, 0);
			break;
		}
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
