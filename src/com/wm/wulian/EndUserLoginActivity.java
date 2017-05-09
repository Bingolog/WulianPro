package com.wm.wulian;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.InvalidParameterException;

import toolkit.WmLog;
import toolkit.WmToast;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android_serialport_api.SerialPort;
import android_serialport_api.WmSocket;

public class EndUserLoginActivity extends Activity {
	private String tag = "EndUserLoginActivity";
	Context mContext = this;
	private Button btnEndUserSettings, btnLogin, btnReg;
	private RadioGroup mRadioGroup;
	private EditText edtUserName, edtPwd;
	public RadioButton btnLocalBased, btnWebBased;
	public static String mName, mPassword, ip;
	public static int port;
	public boolean flag = true;
	
	protected Application mApplication;
	protected SerialPort mSerialPort;
	protected WmSocket mSocket;
	public OutputStream mOutputStream;
	public InputStream mInputStream;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.end_user_login);
		mApplication = (Application) getApplication();

		btnEndUserSettings = (Button) findViewById(R.id.btn_end_user_login_settings);
		btnEndUserSettings
				.setOnClickListener(mEndUserActivityButtonOnClickListener);
		mRadioGroup = (RadioGroup) findViewById(R.id.radioGroup);
		mRadioGroup.setOnCheckedChangeListener(mOncheckChangeListener);
		btnLocalBased = (RadioButton) findViewById(R.id.btn_end_user_local_based);
		btnWebBased = (RadioButton) findViewById(R.id.btn__end_user_web_based);
		edtUserName = (EditText) findViewById(R.id.et_username);
		edtPwd = (EditText) findViewById(R.id.et_pwd);
		btnLogin = (Button) findViewById(R.id.btn_end_user_login);
		btnLogin.setOnClickListener(mEndUserActivityButtonOnClickListener);
		btnReg = (Button) findViewById(R.id.btn_end_user_reg);
		btnReg.setOnClickListener(mEndUserActivityButtonOnClickListener);

	}
//RadioButtonѡ���¼��ļ���
	OnCheckedChangeListener mOncheckChangeListener = new OnCheckedChangeListener() {
		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			// TODO Auto-generated method stub
			if (R.id.btn_end_user_local_based == checkedId) {
				edtUserName.setEnabled(false);
				edtPwd.setEnabled(false);
				btnReg.setEnabled(false);
			} else if (R.id.btn__end_user_web_based == checkedId) {
				edtUserName.setEnabled(true);
				edtPwd.setEnabled(true);
				btnReg.setEnabled(true);
			}
		}
	};
//Button����¼��ļ���
	OnClickListener mEndUserActivityButtonOnClickListener = new OnClickListener() {

		@Override
		public void onClick(View arg0) {
			switch (arg0.getId()) {
			case R.id.btn_end_user_login_settings:
				startActivity(new Intent(EndUserLoginActivity.this,
						EndUserLoginPreferences.class));
				break;
			case R.id.btn_end_user_login:
				if (btnLocalBased.isChecked() || btnWebBased.isChecked()) {
					tryLogin();
				} else {
					WmToast.show_prompt_msg(mContext, "��ѡ�񡰱��ز鿴�����ߡ�����鿴����");
				}
				break;
			case R.id.btn_end_user_reg:
				WmToast.show_prompt_msg(mContext, "��ע�ᣡ");
				break;

			}
		}
	};

	public void tryLogin() {	
		WmLog.d(tag, "ѡ����Ǳ��ز鿴��"+btnLocalBased.isChecked()+"~~ѡ�����web�鿴��"+btnWebBased.isChecked());		
		if (btnWebBased.isChecked()) {
			//���ӷ������������������������
			flag = true;
		}
		if (btnLocalBased.isChecked()) {
				try {
					WmLog.d(tag, "��ȡ��������...��");
						mSerialPort = mApplication.getSerialPort();// ��ȫ�ֱ����л�ȡ����
						WmLog.d(tag, "��ȡ���ڳɹ���");
						mApplication.mOutputStream = mSerialPort.getOutputStream();// ��ȡ�����
						mApplication.mInputStream = mSerialPort.getInputStream();// ��ȡ������
					//	mOutputStream = mSerialPort.getOutputStream();// ��ȡ�����
					//	mInputStream = mSerialPort.getInputStream();// ��ȡ������
						WmLog.d(tag,"�ӱ��ش��ڻ�ȡ������");				
					
				} catch (SecurityException e) {
					DisplayError(R.string.error_security);
					flag = false;
				} catch (IOException e) {
					DisplayError(R.string.error_unknown);
					flag = false;
				}catch (InvalidParameterException e) {
					DisplayError(R.string.error_configuration);
					 flag = false;
				}	
				
			}
		if(flag)
			startActivity(new Intent(EndUserLoginActivity.this,
					EndUserActivity.class));
	}
	
	
	private void DisplayError(int resourceId) {
		AlertDialog.Builder b = new AlertDialog.Builder(this);
		b.setTitle("Error");
		b.setMessage(resourceId);
		b.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				//EndUserLoginActivity.this.finish();
			}
		});
		b.show();
	}
}