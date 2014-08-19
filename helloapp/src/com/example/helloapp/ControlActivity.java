package com.example.helloapp;

import com.utils.inject.From;
import com.utils.inject.Injector;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ControlActivity extends Activity implements OnClickListener {
	
	
	@From(R.id.button1Open)
	Button button1Open;
	@From(R.id.button1Close)
	Button button1Close;
	
	@From(R.id.button2Open)
	Button button2Open;
	@From(R.id.button2Close)
	Button button2Close;
	
	@From(R.id.button3Open)
	Button button3Open;
	@From(R.id.button3Close)
	Button button3Close;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_control);
		Injector.inject(this);
		
		button1Open.setOnClickListener(this);
		button1Close.setOnClickListener(this);
		
		button2Open.setOnClickListener(this);
		button2Close.setOnClickListener(this);
		
		button3Open.setOnClickListener(this);
		button3Close.setOnClickListener(this);
	}
	
	private UdpHelper udphelper;
	private Thread tReceived;
	void initUDP(){//用于创建线程
        WifiManager manager = (WifiManager) this
                .getSystemService(Context.WIFI_SERVICE);
        udphelper = new UdpHelper(manager);
        
        //传递WifiManager对象，以便在UDPHelper类里面使用MulticastLock
//        udphelper.addObserver(MsgReceiveService.this);
        tReceived = new Thread(udphelper);
        tReceived.start();
    }

	@Override
	public void onClick(View v) {

		switch(v.getId()) {
		case R.id.button1Open:
			send(1,1);
			break;
		case R.id.button1Close:
			send(1,0);
			break;
		case R.id.button2Open:
			send(2,1);
			break;
		case R.id.button2Close:
			send(2,0);
			break;
		case R.id.button3Open:
			send(3,1);
			break;
		case R.id.button3Close:
			send(3,0);
			break;
		}
	}
	
	void send(final int port, final int state) {
		 new Thread(){  
	            public void run(){  
//	            	UdpHelper.send("RELAY_CTL=admin,3,0");
	            	UdpHelper.send("RELAY_CTL=admin,"+port+","+state); 
	            }  
	        }.start();  
	}
}
 