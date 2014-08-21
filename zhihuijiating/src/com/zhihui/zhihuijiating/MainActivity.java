package com.zhihui.zhihuijiating;

import com.utils.inject.From;
import com.utils.inject.Injector;
import com.zhihui.zhihuijiating.service.HelloService;

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

public class MainActivity extends BaseActivity implements OnClickListener {

	@From(R.id.home_back)
	ImageView home_back;

	@From(R.id.home_appliance_control)
	ImageView home_appliance_control;
	@From(R.id.curtain_control)
	ImageView curtain_control;

	@From(R.id.background_misic)
	ImageView background_misic;
	@From(R.id.multi_screen_control)
	ImageView multi_screen_control;

	@From(R.id.the_curtain_control)
	ImageView the_curtain_control;
	@From(R.id.supervisory_control)
	ImageView supervisory_control;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);

		home_appliance_control.setOnClickListener(this);
		curtain_control.setOnClickListener(this);

		background_misic.setOnClickListener(this);
		multi_screen_control.setOnClickListener(this);

		the_curtain_control.setOnClickListener(this);
		supervisory_control.setOnClickListener(this);

		Intent intent = new Intent(this, HelloService.class);
		startService(intent);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
//		Intent intent = new Intent(this, HelloService.class);
//		stopService(intent);
		
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.home_appliance_control:
			startApp("com.broadlink.rmt",
					"com.broadlink.rmt.activity.LoadingActivity");
			break;
		case R.id.curtain_control: {
			Intent intent = new Intent(this, CurtainActivity.class);
			startActivity(intent);}
			break;
		case R.id.background_misic:
			startApp("com.kugou.android",
					"com.kugou.android.app.splash.SplashActivity");
			// startApp("unc.android.umusic",
			// "unc.android.umusic.LoadingActivity");
			break;
		case R.id.multi_screen_control:
//			startApp("com.bubblesoft.android.bubbleupnp",
//					"com.bubblesoft.android.bubbleupnp.MainActivity");
			// startApp("com.pv.twonkybeam", "com.pv.twonkybeam.BeamLauncher");
			{
			Intent intent = new Intent(this, MultiScreenActivity.class);
			startActivity(intent);
			}
			break;
		case R.id.the_curtain_control:
			// startApp("x.p2p.cam", "x1.Studio.Ali.Start");
			// TODO
			break;
		case R.id.supervisory_control:
			startApp("x.p2p.cam", "x1.Studio.Ali.Start");
			break;
		}
		// tReceived.start();
	}

	void startApp(final String pkg, final String cls) {

		Log.d("startApp", pkg + "/" + cls);
		ComponentName componet = new ComponentName(pkg, cls);

		Intent i = new Intent();
		i.setComponent(componet);
		startActivity(i);
	}

}
