package com.example.helloapp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Observable;
import android.net.wifi.WifiManager;
import android.util.Log;

/**
 * 
 * UdpHelper帮助类
 * 
 * @author 陈喆榕
 * 
 */

public class UdpHelper implements Runnable {
	public Boolean IsThreadDisable = false;// 指示监听线程是否终止
	private static WifiManager.MulticastLock lock;
	InetAddress mInetAddress;

	static final int UDP_PORT = 9880;
	public UdpHelper(WifiManager manager) {
		this.lock = manager.createMulticastLock("UDPwifi");
	}

	public void StartListen() {
		// UDP服务器监听的端口
	    // http://www.binkery.com/post/266.html
	    // 原因呢，是端口号不能低于1024，据说是Linux的问题。这个待考证吧。
	    // Either root your phone, modify the firmware, 
	    // or don't bind to ports lower than 1024. 
	    // That's a Linux thing more than an Android thing.
		Integer port = UDP_PORT;
		// 接收的字节大小，客户端发送的数据不能超过这个大小
		byte[] message = new byte[100];
		try {
			// 建立Socket连接
			DatagramSocket datagramSocket = new DatagramSocket(port);
			datagramSocket.setBroadcast(true);
			DatagramPacket datagramPacket = new DatagramPacket(message,
					message.length);
			try {
				while (!IsThreadDisable) {
					// 准备接收数据
					Log.d("UDP Demo", "准备接受");
//					this.lock.release();
					this.lock.acquire();

					datagramSocket.receive(datagramPacket);
					String strMsg = new String(datagramPacket.getData()).trim();
					Log.d("UDP Demo", datagramPacket.getAddress()
							.getHostAddress().toString()
							+ ":" + strMsg);
					this.lock.release();
				}
			} catch (IOException e) {// IOException
				e.printStackTrace();
			}
		} catch (SocketException e) {
			e.printStackTrace();
		}

	}

/**
 * 
 * @param message
 */
	public static void send(String message) {
		message = (message == null ? "Hello IdeasAndroid!" : message);
		int server_port = UDP_PORT;
		Log.d("UDP Demo", "UDP发送数据:" + message);
		DatagramSocket s = null;
		try {
			s = new DatagramSocket();
		} catch (SocketException e) {
			e.printStackTrace();
		}
		InetAddress local = null;
		try {
			local = InetAddress.getByName("255.255.255.255");
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		int msg_length = message.length();
		byte[] messageByte = message.getBytes();
		DatagramPacket p = new DatagramPacket(messageByte, msg_length, local,
				server_port);
		try {

			s.send(p);
			s.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	@Override
	public void run() {
		StartListen();
	}
}