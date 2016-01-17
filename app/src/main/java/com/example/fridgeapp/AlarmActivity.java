package com.example.fridgeapp;

import com.example.fridgeapp.db_adapters.DBFridgeAdapter;
import com.example.fridgeapp.db_adapters.DBSettingsAdapter;
import com.example.fridgeapp.db_adapters.DBShopListAdapter;

import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class AlarmActivity extends Activity {

	DBFridgeAdapter fridgeDB;
	DBShopListAdapter shoplistDB;
	DBSettingsAdapter settingsDB;
	Context context = this;
	Ringtone ringtone;
	Vibrator vibrator;
	Boolean itemOnTheShopList = false;
	AlertDialog.Builder builder;
	String productName;
	final CharSequence[] ratings = { "10", "9", "8", "7", "6", "5", "4", "3",
			"2", "1" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_alarm);
		setTitle("ALARM!");
		TextView productNameText = (TextView) findViewById(R.id.alarmProductNameTextView);
		TextView productDateText = (TextView) findViewById(R.id.alarmProductDateTextView);
		fridgeDB = new DBFridgeAdapter(this);
		shoplistDB = new DBShopListAdapter(this);
		settingsDB = new DBSettingsAdapter(this);
		Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
		ringtone = RingtoneManager.getRingtone(getApplicationContext(), notification);
		vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
		Intent intent = getIntent();
		productName = intent.getStringExtra("name");
		fridgeDB.open();
		long productId = fridgeDB.findRecord(productName);
		fridgeDB.switchOffAlarm(productId);
		Cursor productData = fridgeDB.getRecord(productId);
		fridgeDB.close();
		productNameText.setText(productData.getString(1));
		productDateText.setText(productData.getString(2));
		shoplistDB.open();
		if (shoplistDB.findRecord(productName) != -1) {
			itemOnTheShopList = true;
			Button button = (Button) findViewById(R.id.alarmAddToShopListButton);
			button.setEnabled(false);
		}
		shoplistDB.close();
		// building dialog for add item to shoplist rating
		builder = new AlertDialog.Builder(this);
		builder.setTitle("Choose rating:");
		builder.setItems(ratings, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int item) {
				int rating = 10 - item;
				shoplistDB.open();
				long id = shoplistDB.createRecord(productName, rating);
				shoplistDB.close();
				Button addToShopListButton = (Button) findViewById(R.id.alarmAddToShopListButton);
				addToShopListButton.setText("Added to shoplist");
				addToShopListButton.setEnabled(false);
				addToShopListButton.setTextSize(23);
			}
		});
		// turning on vibration and alarm ringtone
		settingsDB.open();
		Cursor settings = settingsDB.getSettings();
		if (settings.getString(1).equalsIgnoreCase("s"))
			ringtone.play();
		settingsDB.close();
		vibrator.vibrate(500);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.alarm, menu);
		return true;
	}

	public void showMainButtons(View v) {
		Button thisButton = (Button) findViewById(R.id.turnOffAlarmButton);
		Button addToShopListButton = (Button) findViewById(R.id.alarmAddToShopListButton);
		Button delayButton = (Button) findViewById(R.id.alarmDelayButton);
		TextView currentlyOnTheShopList = (TextView) findViewById(R.id.currentlyOnTheShopList);
		thisButton.setVisibility(4);
		addToShopListButton.setVisibility(0);
		delayButton.setVisibility(0);
		if (itemOnTheShopList)
			currentlyOnTheShopList.setVisibility(0);
		ringtone.stop();
		vibrator.cancel();
	}

	public void delayProduct(View v) {
		Intent intent = new Intent(context, AlarmSetTimeActivity.class);
		intent.putExtra("productName", productName);
		startActivityForResult(intent, 1);
		// finish();
	}

	public void addProductToShopList(View v) {
		AlertDialog alert = builder.create();
		alert.show();
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == 1) {
			if (resultCode == RESULT_OK) {
				finish();
			}
			if (resultCode == RESULT_CANCELED) {
			}
		}
	}

}
