package com.wm.wulian;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.InvalidParameterException;

import toolkit.WmLog;

import android.content.SharedPreferences;
import android_serialport_api.SerialPort;
import android_serialport_api.WmSerialPortFinder;
import android_serialport_api.WmSocket;


public class Application extends android.app.Application {
	private String tag = "ApplicationTag";
	public static boolean local_selected = false;
	public static boolean web_selected = false;
	private SerialPort mSerialPort = null;
	private WmSocket mSocket = null;
	public WmSerialPortFinder mSerialPortFinder = new WmSerialPortFinder();
	public InputStream mInputStream= null;
	public OutputStream mOutputStream= null;

	public SerialPort getSerialPort() throws SecurityException,IOException, InvalidParameterException {	
		if (mSerialPort == null) {
			WmLog.d(tag, "查看preferences!");
			/* Read serial port parameters */
			SharedPreferences sp = getSharedPreferences(
					"com.wm.wulian_preferences", MODE_PRIVATE);
			String path = sp.getString("DEVICE", "");
			int baudrate = Integer.decode(sp.getString("BAUDRATE", "-1"));
			WmLog.d(tag, "路径为：+"+path);
			WmLog.d(tag, "波特率为：+" + baudrate);

			/* Check parameters */
			if ((path.length() == 0) || (baudrate == -1)) {
				throw new InvalidParameterException();
			}

			/* Open the serial port */
			mSerialPort = new SerialPort(new File(path), baudrate, 0);
		}
		return mSerialPort;
	}

	public WmSocket getWmSocket() throws SecurityException, IOException,
			InvalidParameterException {
		if (mSocket == null) {
			/* Read webServer port parameters */
			SharedPreferences sp = getSharedPreferences("com.wm.wulian_preferences", MODE_PRIVATE);
			String ip = sp.getString("ServerIpAddress", "");
			int port = Integer.decode(sp.getString("ServerPort", "-1"));

			/* Check parameters */
			if ((ip.length() == 0) || (port == -1)) {
				throw new InvalidParameterException();
			}
			WmLog.d(tag, ip);
			WmLog.d(tag, "0+" + port);
			/* connected to Server */
			mSocket = new WmSocket(ip, port);
		}
		return mSocket;
	}

	public void closeSerialPort() {
		if (mSerialPort != null) {
			mSerialPort.close();
			mSerialPort = null;
		}
	}

	public void closeSocket() {
		if (mSocket != null) {
			mSocket.close();
			mSocket = null;
		}
	}

	/** 读取串口的配置信息，创建串口对象 **/
	// 从com.wm.wulian_preferences.xml中读取串口配置信息
	// new WmSerialPort(new File(path), baudrate);
	// new mySocket(string ip,int port);
	// close SerialPort();
	// close mySocket();

}
