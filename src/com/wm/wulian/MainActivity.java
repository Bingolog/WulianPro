package com.wm.wulian;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
	private Button btnEndUser,btnNode,btnServer,btnEndUserLogin;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btnEndUser = (Button) findViewById(R.id.btn_end_user);
		btnEndUser.setOnClickListener(mMainActivityButtonOnClickListener);
		btnEndUserLogin = (Button) findViewById(R.id.btn_end_user_login);
		btnEndUserLogin.setOnClickListener(mMainActivityButtonOnClickListener);
		btnNode = (Button) findViewById(R.id.btn_node);
		btnNode.setOnClickListener(mMainActivityButtonOnClickListener);
		btnServer = (Button) findViewById(R.id.btn_server);
		btnServer.setOnClickListener(mMainActivityButtonOnClickListener);
	}
	/*°´¼ü¼àÌý²¢Ìø×ª*/
	OnClickListener mMainActivityButtonOnClickListener = new OnClickListener() {

		@Override
		public void onClick(View arg0) {			
			switch (arg0.getId()) {
			case R.id.btn_end_user:
				startActivity(new Intent(MainActivity.this, EndUserActivity.class));	
				break;
			case R.id.btn_end_user_login:
				startActivity(new Intent(MainActivity.this, EndUserLoginActivity.class));	
				break;
			case R.id.btn_node:	
				startActivity(new Intent(MainActivity.this, NodeActivity.class));				
				break;
			case R.id.btn_server:
				startActivity(new Intent(MainActivity.this, ServerActivity.class));	
				break;
			}
		}
	};
	
}
