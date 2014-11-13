package com.amo.demo;

import java.text.DecimalFormat;
import java.util.Calendar;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.amo.demo.wheelview.DatePickWheelDialog;

public class MainActivity extends Activity {
	private Button btn;
	private DatePickWheelDialog datePickWheelDialog;
	private TextView tv;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		btn = (Button) findViewById(R.id.button1);
		tv = (TextView) findViewById(R.id.text1);
		btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				datePickWheelDialog = new DatePickWheelDialog.Builder(
						MainActivity.this)
						.setPositiveButton("确定", new View.OnClickListener() {

							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								Calendar c = datePickWheelDialog
										.getSetCalendar();
								tv.setText(getFormatTime(c));
								datePickWheelDialog.dismiss();
							}
						}).setTitle("请选择日期与时间").setNegativeButton("取消", null)
						.create();
				datePickWheelDialog.show();
			}
		});
	}

	public static String getFormatTime(Calendar c) {
		String parten = "00";
		DecimalFormat decimal = new DecimalFormat(parten);
		// 设置日期的显示
		Calendar calendar = c;
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		int day = calendar.get(Calendar.DATE);
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int minute = calendar.get(Calendar.MINUTE);
		return year + "-" + decimal.format(month + 1) + "-"
				+ decimal.format(day) + " " + decimal.format(hour) + ":"
				+ decimal.format(minute);

	}

}