package com.wm.wulian;

import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.PreferenceActivity;
import android_serialport_api.WmSerialPortFinder;

public class EndUserLoginPreferences extends PreferenceActivity {
	private Application mApplication;
	private WmSerialPortFinder mSerialPortFinder;

	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		mApplication = (Application) getApplication();
		mSerialPortFinder = mApplication.mSerialPortFinder;

		addPreferencesFromResource(R.xml.end_user_settings_preferences);

		// 设备选择
		final ListPreference devices = (ListPreference) findPreference("DEVICE");
		String[] entries = mSerialPortFinder.getAllDevices();
		String[] entryValues = mSerialPortFinder.getAllDevicesPath();
		devices.setEntries(entries);
		devices.setEntryValues(entryValues);
		devices.setSummary(devices.getValue());
		devices.setOnPreferenceChangeListener(new OnPreferenceChangeListener() {
			public boolean onPreferenceChange(Preference preference,
					Object newValue) {
				preference.setSummary((String) newValue); // 将选中的设备显示在标题栏下的摘要中；
				return true;
			}
		});

		// 波特率选择
		final ListPreference baudrates = (ListPreference) findPreference("BAUDRATE");
		baudrates.setSummary(baudrates.getValue());
		baudrates
				.setOnPreferenceChangeListener(new OnPreferenceChangeListener() {
					public boolean onPreferenceChange(Preference preference,
							Object newValue) {
						preference.setSummary((String) newValue);// 将选中的波特率显示在标题栏下的摘要中；
						return true;
					}
				});
		// 编辑输入本地节点编号
		EditTextPreference editTextPrefNodeNumber = (EditTextPreference) findPreference("LocalNodeNumber");
		editTextPrefNodeNumber.setSummary(editTextPrefNodeNumber.getText());
		editTextPrefNodeNumber
				.setOnPreferenceChangeListener(new OnPreferenceChangeListener() {
					public boolean onPreferenceChange(Preference preference,
							Object newValue) {
						preference.setSummary((String) newValue);// 将编辑输入的节点编号显示出来
						return true;
					}
				});
		// 编辑并获取IP
		EditTextPreference editTextPrefIp = (EditTextPreference) findPreference("ServerIpAddress");
		editTextPrefIp.setSummary(editTextPrefIp.getText());
		editTextPrefIp
				.setOnPreferenceChangeListener(new OnPreferenceChangeListener() {
					public boolean onPreferenceChange(Preference preference,
							Object newValue) {
						preference.setSummary((String) newValue);// 将编辑输入的远程IP地址显示在标题栏下的摘要中；
						return true;
					}
				});

		// 编辑输入远程端口号
		EditTextPreference editTextPrefPort = (EditTextPreference) findPreference("ServerPort");
		editTextPrefPort.setSummary(editTextPrefPort.getText());
		editTextPrefPort
				.setOnPreferenceChangeListener(new OnPreferenceChangeListener() {
					public boolean onPreferenceChange(Preference preference,
							Object newValue) {
						preference.setSummary((String) newValue);// 将编辑输入的远程端口号显示在标题栏下的摘要中；
						return true;
					}
				});
	}
}
