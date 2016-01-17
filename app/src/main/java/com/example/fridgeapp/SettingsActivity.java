package com.example.fridgeapp;

import java.util.ArrayList;

import com.example.fridgeapp.db_adapters.DBFridgeAdapter;
import com.example.fridgeapp.db_adapters.DBSettingsAdapter;
import com.example.fridgeapp.product_classes.Product;
import com.example.fridgeapp.AlarmManagerBroadcastReceiver;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

public class SettingsActivity extends Activity {

	DBSettingsAdapter dbSettings;
	DBFridgeAdapter dbFridge;
	Context context = this;
	RadioButton signalRadio;
	Spinner spinnerReminder;
	Button saveButton;
	Button cancelButton;
	EditText hours;
	EditText minutes;
	private AlarmManagerBroadcastReceiver alarm;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
		setTitle("Settings");
		// init broadcast receiver
		alarm = new AlarmManagerBroadcastReceiver();
		//init SettingsDB and FridgeDB
		dbSettings = new DBSettingsAdapter(this);
		dbFridge = new DBFridgeAdapter(this);
		// init reminder spinner
		ArrayList<String> reminderArray = new ArrayList<String>();
		reminderArray.add("That day");
		reminderArray.add("1 day before");
		reminderArray.add("2 days before");
		reminderArray.add("3 days before");
		spinnerReminder = (Spinner) findViewById(R.id.spinnerReminder);
		ArrayAdapter<String> reminderAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_dropdown_item, reminderArray);
		spinnerReminder.setAdapter(reminderAdapter);
		// init radiobuttons
		signalRadio = (RadioButton) findViewById(R.id.signalRadioButton);
		RadioButton vibroRadio = (RadioButton) findViewById(R.id.vibroRadioButton);
		// init time fields
		hours = (EditText) findViewById(R.id.editHours);
		minutes = (EditText) findViewById(R.id.editMinutes);
		// init buttons
		saveButton = (Button) findViewById(R.id.saveSettingsButton);
		cancelButton = (Button) findViewById(R.id.cancelButton);
		// get data from SettingsDB and attach to the fields
		dbSettings.open();
		Cursor data = dbSettings.getSettings();
		dbSettings.close();
		if (data.getString(1).equalsIgnoreCase("s")) {
			signalRadio.setChecked(true);
		} else vibroRadio.setChecked(true);
		spinnerReminder.setSelection(Integer.parseInt(data.getString(2))*(-1));
		hours.setText(data.getString(3).substring(0,2));
		minutes.setText(data.getString(3).substring(3,5));
		// set onclick listeners
		saveButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// time validations
				if (Integer.parseInt(hours.getText().toString()) > 23 ||
						Integer.parseInt(hours.getText().toString()) < 0 ||
						Integer.parseInt(minutes.getText().toString()) > 59 ||
						Integer.parseInt(minutes.getText().toString()) < 0) {
					DisplayToast("Time is incorrect!");
					return;
				}
				saveButton.setEnabled(false);
				cancelButton.setEnabled(false);
				// set final setting values
				String finalSignalType;
				if (signalRadio.isChecked()) finalSignalType = "s";
				else finalSignalType = "v";
				int finalReminder = spinnerReminder.getSelectedItemPosition()*(-1);
				String finalTime = hours.getText().toString() + ":" + minutes.getText().toString();
				// save final settings values to SettingsDB
				dbSettings.open();
				dbSettings.updateRecord(finalSignalType, Integer.toString(finalReminder), finalTime);
				dbSettings.close();
				// reindex all alarms
				reindexAllAlarms(finalReminder, finalTime);
				Intent intent = new Intent(context, MainActivity.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				startActivity(intent);
			}
		});
		cancelButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				saveButton.setEnabled(false);
				cancelButton.setEnabled(false);
				Intent intent = new Intent(context, MainActivity.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				startActivity(intent);
			}
		});
	}
	
	private void reindexAllAlarms(int reminder, String time) {
        dbFridge.open();
        Cursor c = dbFridge.getAllRecords();
        if (c.moveToFirst())
        {
            do {          
                if (c.getString(6).equalsIgnoreCase("1") && c.getString(3).equalsIgnoreCase("0")) {
                	if(alarm != null){
                		alarm.setAlarm(context, c.getString(2), time, reminder, c.getString(1));
                	}else{
                		Toast.makeText(context, "Alarm is null", Toast.LENGTH_SHORT).show();
                	}
                }
            	
            } while (c.moveToNext());
        }
        dbFridge.close();
	}
	
	public void DisplayToast(String text) {
		Toast.makeText(context, text, 2).show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.settings, menu);
		return true;
	}

}
