package com.example.fridgeapp;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.example.fridgeapp.db_adapters.DBFridgeAdapter;
import com.example.fridgeapp.product_classes.Product;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class AlarmSetTimeActivity extends Activity {

	Context context = this;
	String productName;
	String time;
	DBFridgeAdapter db;
	private AlarmManagerBroadcastReceiver alarm;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_alarm_set_time);
		setTitle("Set new time");
		db = new DBFridgeAdapter(context);
		alarm = new AlarmManagerBroadcastReceiver();
		Intent intent = getIntent();
		productName = intent.getStringExtra("productName");
		TextView msgText = (TextView) findViewById(R.id.messageText);
		msgText.setText("Set new time for " + productName);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.alarm_set_time, menu);
		return true;
	}

	public void setTime(View v) {
		TextView hoursText = (TextView) findViewById(R.id.editHours);
		TextView minutesText = (TextView) findViewById(R.id.editMinutes);
		String hours = hoursText.getText().toString();
		String minutes = minutesText.getText().toString();
		time = hours + ":" + minutes;
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
				context);
		alertDialogBuilder.setTitle("Remind today at " + time + " ?");
		alertDialogBuilder
				.setCancelable(true)
				.setNegativeButton("Yes",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								db.open();
								Cursor c = db.getRecord(db
										.findRecord(productName));
								db.updateRecord(
										Integer.parseInt(c.getString(0)),
										c.getString(1), c.getString(2), time,
										c.getString(4),
										Integer.parseInt(c.getString(5)), 1);
								db.close();
								alarm.setExactAlarm(context, getTodayDate(),
										time, productName);
								Intent returnIntent = new Intent();
								setResult(RESULT_OK, returnIntent);
								finish();
							}
						})
				.setNeutralButton("No", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
					}
				});
		AlertDialog alertDialog = alertDialogBuilder.create();
		alertDialog.show();
	}

	public void cancelSetTime(View v) {
		Intent returnIntent = new Intent();
		setResult(RESULT_CANCELED, returnIntent);
		finish();
	}
	
	public String getTodayDate() {
		long date = System.currentTimeMillis();
		SimpleDateFormat sdf= new SimpleDateFormat("dd/MM/yyyy");
		return sdf.format(date);
	}
}
