package com.wm.dataProcessing;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.InvalidParameterException;

import toolkit.WmLog;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android_serialport_api.SerialPort;
import android_serialport_api.WmSocket;

import com.wm.wulian.Application;
import com.wm.wulian.R;

/******************************************************************/
//错误提示，开辟线程read数据
/*****************************************************************/
public abstract class DataReadAndErrorNotify extends Activity {
	private String tag = "DataReadAndErrorNotify";

	protected Application mApplication;
	protected SerialPort mSerialPort;
	protected WmSocket mSocket;
	protected OutputStream mOutputStream;
	private InputStream mInputStream;
	private ReadThread mReadThread;

	private class ReadThread extends Thread {

		@Override
		public void run() {
			super.run();
			while (!isInterrupted()) {
				int size;
				try {
					byte[] buffer = new byte[64];
					if (mInputStream == null)
						return;
					size = mInputStream.read(buffer);
					if (size > 0) {
						onDataReceived(buffer, size);
					}
				} catch (IOException e) {
					e.printStackTrace();
					return;
				}
			}
		}
	}

	private void DisplayError(int resourceId) {
		AlertDialog.Builder b = new AlertDialog.Builder(this);
		b.setTitle("Error");
		b.setMessage(resourceId);
		b.setPositiveButton("OK", new OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				DataReadAndErrorNotify.this.finish();
			}
		});
		b.show();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mApplication = (Application) getApplication();
		WmLog.d(tag,"开始建立连接！");
		try {
			if (Application.web_selected) {
				WmLog.d(tag,"开始建立连接1！");
				mSocket = mApplication.getWmSocket();// 在全局变量中获取IP和端口
				mOutputStream = mSocket.getOutputStream();// 获取输出流
				mInputStream = mSocket.getInputStream();// 获取输入流
				WmLog.d(tag,"从网络获取数据流");
			} else if (Application.local_selected) {
				WmLog.d(tag,"开始建立连接2！");
				mSerialPort = mApplication.getSerialPort();// 在全局变量中获取串口
				mOutputStream = mSerialPort.getOutputStream();// 获取输出流
				mInputStream = mSerialPort.getInputStream();// 获取输入流
				WmLog.d(tag,"从本地串口获取数据流");
			}
			/* Create a receiving thread */
			mReadThread = new ReadThread();
			mReadThread.start();
		} catch (SecurityException e) {
			DisplayError(R.string.error_security);
		} catch (IOException e) {
			DisplayError(R.string.error_unknown);
		} catch (InvalidParameterException e) {
			DisplayError(R.string.error_configuration);
		}
	}

	protected abstract void onDataReceived(final byte[] buffer, final int size);

	@Override
	protected void onDestroy() {
		if (mReadThread != null)
			mReadThread.interrupt();
		mApplication.closeSerialPort();
		mSerialPort = null;
		super.onDestroy();
	}

	public void OnResume() {
		// TODO Auto-generated method stub
		
	}
}
