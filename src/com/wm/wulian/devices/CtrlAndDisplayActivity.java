package com.wm.wulian.devices;

import java.io.IOException;

import toolkit.WmLog;
import android.app.Activity;
import android.os.Bundle;

import com.wm.wulian.Application;
import com.wm.wulian.R;

public class CtrlAndDisplayActivity extends Activity{
	private String tag = "CtrlAndDisplayActivity";
	protected Application mApplication;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.hello_world);
		mApplication = (Application) getApplication();
		WmLog.d(tag,"��ѡ������ݻ�ȡ��ʽweb��ȡΪ��"+Application.web_selected);
		try {
			mApplication.mOutputStream.write('1');
		} catch (IOException e) {
			// TODO Auto-generated catch block
			WmLog.d(tag, "Error here!");
			e.printStackTrace();
		}
		WmLog.d(tag,"��ѡ������ݻ�ȡ��ʽweb��ȡΪ��"+Application.web_selected);
		WmLog.d(tag,"��ѡ������ݻ�ȡ��ʽlocal��ȡΪ��"+Application.local_selected);
		
	}

	
}
