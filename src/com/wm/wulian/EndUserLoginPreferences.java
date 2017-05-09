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

		// �豸ѡ��
		final ListPreference devices = (ListPreference) findPreference("DEVICE");
		String[] entries = mSerialPortFinder.getAllDevices();
		String[] entryValues = mSerialPortFinder.getAllDevicesPath();
		devices.setEntries(entries);
		devices.setEntryValues(entryValues);
		devices.setSummary(devices.getValue());
		devices.setOnPreferenceChangeListener(new OnPreferenceChangeListener() {
			public boolean onPreferenceChange(Preference preference,
					Object newValue) {
				preference.setSummary((String) newValue); // ��ѡ�е��豸��ʾ�ڱ������µ�ժҪ�У�
				return true;
			}
		});

		// ������ѡ��
		final ListPreference baudrates = (ListPreference) findPreference("BAUDRATE");
		baudrates.setSummary(baudrates.getValue());
		baudrates
				.setOnPreferenceChangeListener(new OnPreferenceChangeListener() {
					public boolean onPreferenceChange(Preference preference,
							Object newValue) {
						preference.setSummary((String) newValue);// ��ѡ�еĲ�������ʾ�ڱ������µ�ժҪ�У�
						return true;
					}
				});
		// �༭���뱾�ؽڵ���
		EditTextPreference editTextPrefNodeNumber = (EditTextPreference) findPreference("LocalNodeNumber");
		editTextPrefNodeNumber.setSummary(editTextPrefNodeNumber.getText());
		editTextPrefNodeNumber
				.setOnPreferenceChangeListener(new OnPreferenceChangeListener() {
					public boolean onPreferenceChange(Preference preference,
							Object newValue) {
						preference.setSummary((String) newValue);// ���༭����Ľڵ�����ʾ����
						return true;
					}
				});
		// �༭����ȡIP
		EditTextPreference editTextPrefIp = (EditTextPreference) findPreference("ServerIpAddress");
		editTextPrefIp.setSummary(editTextPrefIp.getText());
		editTextPrefIp
				.setOnPreferenceChangeListener(new OnPreferenceChangeListener() {
					public boolean onPreferenceChange(Preference preference,
							Object newValue) {
						preference.setSummary((String) newValue);// ���༭�����Զ��IP��ַ��ʾ�ڱ������µ�ժҪ�У�
						return true;
					}
				});

		// �༭����Զ�̶˿ں�
		EditTextPreference editTextPrefPort = (EditTextPreference) findPreference("ServerPort");
		editTextPrefPort.setSummary(editTextPrefPort.getText());
		editTextPrefPort
				.setOnPreferenceChangeListener(new OnPreferenceChangeListener() {
					public boolean onPreferenceChange(Preference preference,
							Object newValue) {
						preference.setSummary((String) newValue);// ���༭�����Զ�̶˿ں���ʾ�ڱ������µ�ժҪ�У�
						return true;
					}
				});
	}
}
