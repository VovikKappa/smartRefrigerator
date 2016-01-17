package com.example.fridgeapp;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;


import com.example.fridgeapp.AddItemToShopListActivity.productTypeSelectListener;
import com.example.fridgeapp.db_adapters.DBFridgeAdapter;
import com.example.fridgeapp.db_adapters.DBSettingsAdapter;
import com.example.fridgeapp.db_adapters.DBShopListAdapter;
import com.example.fridgeapp.product_classes.Beverage;
import com.example.fridgeapp.product_classes.BreadProduct;
import com.example.fridgeapp.product_classes.Eggs;
import com.example.fridgeapp.product_classes.FishProduct;
import com.example.fridgeapp.product_classes.Fruit;
import com.example.fridgeapp.product_classes.MeatProduct;
import com.example.fridgeapp.product_classes.MilkProduct;
import com.example.fridgeapp.product_classes.Product;
import com.example.fridgeapp.product_classes.Sauce;
import com.example.fridgeapp.product_classes.Vegetable;

import android.os.Bundle;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.TextView;
import android.widget.ToggleButton;

public class AddItemToFridgeActivity extends Activity {

	DBFridgeAdapter db;
	DBSettingsAdapter dbSettings;
	private AlarmManagerBroadcastReceiver alarm;
	Calendar myCalendar = Calendar.getInstance();
	TextView editDate;
	Button add_item_button;
	Spinner spinnerProductType;
	Spinner spinnerProduct;
	Spinner spinnerProductQuantity;
	ArrayAdapter<String> productAdapter;
	ArrayList<String> productArray;
	DatePickerDialog.OnDateSetListener date;
	Context context = this;
	ToggleButton alarmToggler;
	int alarmTogglerSwitch;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_item_to_fridge);
		setTitle("Add item to fridge");
		db = new DBFridgeAdapter(this);
		dbSettings = new DBSettingsAdapter(this);
		alarm = new AlarmManagerBroadcastReceiver();
		alarmTogglerSwitch = 0;
		// setting adapter for products type spinner
		ArrayList<String> productTypeArray = Product.getProductTypesUA();
		productTypeArray.add(0, "Select");
		spinnerProductType = (Spinner) findViewById(R.id.products_type_spinner);
		ArrayAdapter<String> productTypeAdapter = new ArrayAdapter<String>(
				this, android.R.layout.simple_spinner_dropdown_item,
				productTypeArray);
		spinnerProductType.setAdapter(productTypeAdapter);
		// setting adapter for products spinner
		productArray = new ArrayList<String>();
		productArray.add("Select");
		spinnerProduct = (Spinner) findViewById(R.id.products_spinner);
		productAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_dropdown_item, productArray);
		spinnerProduct.setAdapter(productAdapter);
		// setting adapter for products rating spinner
		ArrayList<String> productQuantityArray = new ArrayList<String>() {
			{
				add("Select");
				for (int i = 1; i < 21; i++) {
					add(Integer.toString(i));
				}
			}
		};
		spinnerProductQuantity = (Spinner) findViewById(R.id.products_rating_spinner);
		ArrayAdapter<String> productRatingAdapter = new ArrayAdapter<String>(
				this, android.R.layout.simple_spinner_dropdown_item,
				productQuantityArray);
		spinnerProductQuantity.setAdapter(productRatingAdapter);
		// setting up datepicker
		date = new DatePickerDialog.OnDateSetListener() {
			@Override
			public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
				// TODO Auto-generated method stub
		        myCalendar.set(Calendar.YEAR, year);
		        myCalendar.set(Calendar.MONTH, monthOfYear);
		        myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
		        updateLabel();				
			}
		};
		editDate = (TextView) findViewById(R.id.dateLabel);
		editDate.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
			        new DatePickerDialog(context, date, myCalendar
			                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
			                myCalendar.get(Calendar.DAY_OF_MONTH)).show();
			};
		});
		// setting the toogler
		alarmToggler = (ToggleButton) findViewById(R.id.toggleButton1);
		// setting the button
		add_item_button = (Button) this
				.findViewById(R.id.activity_add_item_to_fridge_button);
		add_item_button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (spinnerProductType.getSelectedItem().toString() == "Select" ||
					spinnerProduct.getSelectedItem().toString() == "Select" ||
					spinnerProductQuantity.getSelectedItem().toString() == "Select" ||
					editDate.getText().equals("Set Date")) {
					DisplayToast("Not all items are selected!");
					return;
				}
				String name = Product.getProductNameEN(spinnerProduct.getSelectedItem().toString());
				String duedate = (String) editDate.getText();
				String alarmtime = "0";
				String surrogates = "";
				int quantity = Integer.parseInt(spinnerProductQuantity.getSelectedItem().toString());
				setAlarmTogglerSwitch();
				db.open();
				long id = db.createRecord(name, duedate, alarmtime, surrogates, quantity, alarmTogglerSwitch);
				db.close();
				// return if item already exists
				if (id == -1) {
					DisplayToast(name+" is already in the fridge!");
					return;
				}
				// set the alarm
				dbSettings.open();
				Cursor settings = dbSettings.getSettings();
				dbSettings.close();
				if (alarmTogglerSwitch == 1)
					alarm.setAlarm(context, duedate, settings.getString(3), Integer.parseInt(settings.getString(2)), name);
				Intent intent = new Intent(context, MainActivity.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				startActivity(intent);
			}
		});
	}
	
	private void updateLabel() {
	    String myFormat = "dd/MM/yyyy";
	    SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
	    editDate.setText(sdf.format(myCalendar.getTime()));
	}
	
	private void setAlarmTogglerSwitch() {
		if (alarmToggler.isChecked()) {
			alarmTogglerSwitch = 1;
		} else alarmTogglerSwitch = 0;
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		// setting onSelect listener
		spinnerProductType
				.setOnItemSelectedListener(new productTypeSelectListener());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_item_to_fridge, menu);
		return true;
	}
	
	public class productTypeSelectListener implements OnItemSelectedListener {
		@Override
		public void onItemSelected(AdapterView<?> parentView,
				View selectedItemView, int position, long id) {
			int itemId = (int) spinnerProductType.getSelectedItemId();
			switch (itemId) {
			case 0:
				productArray.clear();
				productArray.add("Select");
				productAdapter.notifyDataSetChanged();
				break;
			case 1:
				productArray.clear();
				productArray.add("Select");
				productArray.addAll(MeatProduct.getProductsUA());
				productAdapter.notifyDataSetChanged();
				break;
			case 2:
				productArray.clear();
				productArray.add("Select");
				productArray.addAll(Beverage.getProductsUA());
				productAdapter.notifyDataSetChanged();
				break;
			case 3:
				productArray.clear();
				productArray.add("Select");
				productArray.addAll(BreadProduct.getProductsUA());
				productAdapter.notifyDataSetChanged();
				break;
			case 4:
				productArray.clear();
				productArray.add("Select");
				productArray.addAll(Eggs.getProductsUA());
				productAdapter.notifyDataSetChanged();
				break;
			case 5:
				productArray.clear();
				productArray.add("Select");
				productArray.addAll(FishProduct.getProductsUA());
				productAdapter.notifyDataSetChanged();
				break;
			case 6:
				productArray.clear();
				productArray.add("Select");
				productArray.addAll(Fruit.getProductsUA());
				productAdapter.notifyDataSetChanged();
				break;
			case 7:
				productArray.clear();
				productArray.add("Select");
				productArray.addAll(MilkProduct.getProductsUA());
				productAdapter.notifyDataSetChanged();
				break;
			case 8:
				productArray.clear();
				productArray.add("Select");
				productArray.addAll(Sauce.getProductsUA());
				productAdapter.notifyDataSetChanged();
				break;
			case 9:
				productArray.clear();
				productArray.add("Select");
				productArray.addAll(Vegetable.getProductsUA());
				productAdapter.notifyDataSetChanged();
				break;
			}
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub
		}
	}

	public void DisplayToast(String msg) {
		Toast.makeText( this, msg, 2).show();
	}
}
