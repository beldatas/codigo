package net.sistransito.mobile.timeandime;

import android.content.Context;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class TimeAndIme {
	private Calendar calendar;
	private SimpleDateFormat sDatef;
	private SimpleDateFormat sTimef;


	public TimeAndIme(Context context) {
//		mngr = (TelephonyManager) context
//				.getSystemService(Context.TELEPHONY_SERVICE);
		calendar = Calendar.getInstance();
		sDatef = new SimpleDateFormat("dd/MMMM/yyyy", Locale.getDefault());

		sTimef = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
	}

	public String getTime() {
		return sTimef.format(calendar.getTime());
	}

	public String getDate() {
		return sDatef.format(calendar.getTime());
	}

	public String getUniqueId() {
		return "123456";//mngr.getDeviceId() + String.valueOf(System.currentTimeMillis());
	}

	public String getIME() {
		return "1312312";//'mngr.getDeviceId();
	}
}
