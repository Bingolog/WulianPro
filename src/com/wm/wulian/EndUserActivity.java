package com.wm.wulian;

import toolkit.WmLog;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.wm.wulian.devices.CtrlAndDisplayActivity;
import com.wm.wulian.devices.RFID1Activity;
import com.wm.wulian.devices.RFID2Activity;
import com.wm.wulian.devices.RFID3Activity;

public class EndUserActivity extends Activity {
	private String tag = "EndUserActivity";
	
	private Button btnCtrlAndDisplay,btnRfid1,btnRfid2,btnRfid3,btnSetting;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.end_user_device_select);
		btnCtrlAndDisplay = (Button) findViewById(R.id.btn_ctl_and_display);
		btnCtrlAndDisplay.setOnClickListener(mEndUserActivityButtonOnClickListener);
		btnRfid1 = (Button) findViewById(R.id.btn_rf_id1);
		btnRfid1.setOnClickListener(mEndUserActivityButtonOnClickListener);
		btnRfid2 = (Button) findViewById(R.id.btn_rf_id2);
		btnRfid2.setOnClickListener(mEndUserActivityButtonOnClickListener);
		btnRfid3 = (Button) findViewById(R.id.btn_rf_id3);
		btnRfid3.setOnClickListener(mEndUserActivityButtonOnClickListener);		
		WmLog.d(tag, "变量状态分别为：web--"+Application.web_selected+"--local--"+Application.local_selected);//监控全局变量的状态
		
}
	
	OnClickListener mEndUserActivityButtonOnClickListener = new OnClickListener(){

		@Override
		public void onClick(View arg0) {			
			switch (arg0.getId()) {
			case R.id.btn_ctl_and_display:
				startActivity(new Intent(EndUserActivity.this, CtrlAndDisplayActivity.class));
				break;
			case R.id.btn_rf_id1:
				startActivity(new Intent(EndUserActivity.this,RFID1Activity.class));
				break;
			case R.id.btn_rf_id2:
				startActivity(new Intent(EndUserActivity.this,RFID2Activity.class));
				break;
			case R.id.btn_rf_id3:
				startActivity(new Intent(EndUserActivity.this,RFID3Activity.class));
				break;
		
			}
		}
	};
	
}