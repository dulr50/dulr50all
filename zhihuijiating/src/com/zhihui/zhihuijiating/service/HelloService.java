package com.zhihui.zhihuijiating.service;

import com.zhihui.zhihuijiating.UdpHelper;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.IBinder;
import android.util.Log;

/**
 * Android Service 示例
 * 
 * @author dev
 * 
 */
public class HelloService extends Service {
	private static final String TAG = "ServiceDemo";
	public static final String ACTION = "com.lql.service.ServiceDemo";

	@Override
	public IBinder onBind(Intent intent) {
		Log.v(TAG, "ServiceDemo onBind");
		return null;
	}

	@Override
	public void onCreate() {
		Log.v(TAG, "ServiceDemo onCreate");
		super.onCreate();
		initUDP();
	}

	public void onDestroy() {
		Log.v(TAG, "ServiceDemo onDestroy");
		super.onDestroy();
		udphelper.closeSocket();

	}

	@Override
	public void onStart(Intent intent, int startId) {
		Log.v(TAG, "ServiceDemo onStart");
		super.onStart(intent, startId);
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.v(TAG, "ServiceDemo onStartCommand");
		return super.onStartCommand(intent, flags, startId);
	}

	private UdpHelper udphelper;
	private Thread tReceived;

	void initUDP() {// 用于创建线程
		WifiManager manager = (WifiManager) this
				.getSystemService(Context.WIFI_SERVICE);
		udphelper = new UdpHelper(manager);

		// 传递WifiManager对象，以便在UDPHelper类里面使用MulticastLock
		// udphelper.addObserver(MsgReceiveService.this);
		tReceived = new Thread(udphelper);

		tReceived.start();
	}

}
