package com.example.helloapp;

import com.utils.inject.From;
import com.utils.inject.Injector;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.Bundle;
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
		
	}
	


	@Override
	public void onClick(View v) {

		switch(v.getId()) {
		case R.id.home_appliance_control:
			send(1,0);
			break;
		case R.id.curtain_control:
			send(1,1);
			break;
		case R.id.background_misic:
			send(2,0);
			break;
		case R.id.multi_screen_control:
			send(2,1);
			break;
		case R.id.the_curtain_control:
			send(0,0);
			break;
		case R.id.supervisory_control:
			send(0,1);
			break;
		}
//		 tReceived.start();
	}
	
	void send(final int port, final int state) {
//		 new Thread(){  
//	            public void run(){  
////	            	UdpHelper.send("RELAY_CTL=admin,3,0");
//	            	UdpHelper.send("RELAY_CTL=admin,"+port+","+state); 
//	            }  
//	        }.start();  
	}
}
 