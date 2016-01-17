package com.example.fridgeapp;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.example.fridgeapp.db_adapters.DBFridgeAdapter;
import com.example.fridgeapp.db_adapters.DBSettingsAdapter;
import com.example.fridgeapp.product_classes.Product;
import com.example.fridgeapp.products.Ananas;
import com.example.fridgeapp.products.Apple;
import com.example.fridgeapp.products.Banana;
import com.example.fridgeapp.products.Beef;
import com.example.fridgeapp.products.Bread;
import com.example.fridgeapp.products.Carassius;
import com.example.fridgeapp.products.Carrot;
import com.example.fridgeapp.products.Chicken;
import com.example.fridgeapp.products.ChickenEggs;
import com.example.fridgeapp.products.Cola;
import com.example.fridgeapp.products.Cucumber;
import com.example.fridgeapp.products.Esox;
import com.example.fridgeapp.products.Juice;
import com.example.fridgeapp.products.Ketchup;
import com.example.fridgeapp.products.Lemon;
import com.example.fridgeapp.products.Mayonnaise;
import com.example.fridgeapp.products.Milk;
import com.example.fridgeapp.products.Mineral;
import com.example.fridgeapp.products.Mustard;
import com.example.fridgeapp.products.Orange;
import com.example.fridgeapp.products.Perch;
import com.example.fridgeapp.products.Pork;
import com.example.fridgeapp.products.SourCream;
import com.example.fridgeapp.products.Tomato;
import com.example.fridgeapp.products.Veal;
import com.example.fridgeapp.products.Yoghurt;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivityFridge extends Fragment {

	TableLayout tableLayout;
	Button add_item_button;
	DBFridgeAdapter db;
	List<Product> productsArray;
	protected String dialogTempText;
	private AlarmManagerBroadcastReceiver alarm;

	public MainActivityFridge() {
		productsArray = new ArrayList<Product>();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(
				R.layout.activity_main_fragment_fridge, container, false);
		alarm = new AlarmManagerBroadcastReceiver();
		tableLayout = (TableLayout) rootView
				.findViewById(R.id.activity_main_fragment_fridge_table);
		add_item_button = (Button) rootView
				.findViewById(R.id.activity_main_fragment_fridge_button1);
		add_item_button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getActivity(), AddItemToFridgeActivity.class);
				startActivity(intent);
			}
		});
		db = new DBFridgeAdapter(getActivity());
		renderList();
		return rootView;
	}
	
	public void renderList() {
		createProductsArray();
		printAllProducts();
	}

	public void createProductsArray() {
		productsArray.clear();
		db.open();
		Cursor c = db.getAllRecords();
		if (c.moveToFirst()) {
			do {
				if (c.getString(1).equalsIgnoreCase("Ananas")) {
					Ananas ananas = new Ananas();
					ananas.db_id = Integer.parseInt(c.getString(0));
					ananas.duedate = c.getString(2);
					ananas.quantity = Integer.parseInt(c.getString(5));
					productsArray.add(ananas);
				} else if (c.getString(1).equalsIgnoreCase("Apple")) {
					Apple apple = new Apple();
					apple.db_id = Integer.parseInt(c.getString(0));
					apple.duedate = c.getString(2);
					apple.quantity = Integer.parseInt(c.getString(5));
					productsArray.add(apple);
				} else if (c.getString(1).equalsIgnoreCase("Banana")) {
					Banana banana = new Banana();
					banana.db_id = Integer.parseInt(c.getString(0));
					banana.duedate = c.getString(2);
					banana.quantity = Integer.parseInt(c.getString(5));
					productsArray.add(banana);
				} else if (c.getString(1).equalsIgnoreCase("Beef")) {
					Beef beef = new Beef();
					beef.db_id = Integer.parseInt(c.getString(0));
					beef.duedate = c.getString(2);
					beef.quantity = Integer.parseInt(c.getString(5));
					productsArray.add(beef);
				} else if (c.getString(1).equalsIgnoreCase("Bread")) {
					Bread bread = new Bread();
					bread.db_id = Integer.parseInt(c.getString(0));
					bread.duedate = c.getString(2);
					bread.quantity = Integer.parseInt(c.getString(5));
					productsArray.add(bread);
				} else if (c.getString(1).equalsIgnoreCase("Carassius")) {
					Carassius carassius = new Carassius();
					carassius.db_id = Integer.parseInt(c.getString(0));
					carassius.duedate = c.getString(2);
					carassius.quantity = Integer.parseInt(c.getString(5));
					productsArray.add(carassius);
				} else if (c.getString(1).equalsIgnoreCase("Carrot")) {
					Carrot carrot = new Carrot();
					carrot.db_id = Integer.parseInt(c.getString(0));
					carrot.duedate = c.getString(2);
					carrot.quantity = Integer.parseInt(c.getString(5));
					productsArray.add(carrot);
				} else if (c.getString(1).equalsIgnoreCase("Chicken")) {
					Chicken chicken = new Chicken();
					chicken.db_id = Integer.parseInt(c.getString(0));
					chicken.duedate = c.getString(2);
					chicken.quantity = Integer.parseInt(c.getString(5));
					productsArray.add(chicken);
				} else if (c.getString(1).equalsIgnoreCase("ChickenEggs")) {
					ChickenEggs chickenEggs = new ChickenEggs();
					chickenEggs.db_id = Integer.parseInt(c.getString(0));
					chickenEggs.duedate = c.getString(2);
					chickenEggs.quantity = Integer.parseInt(c.getString(5));
					productsArray.add(chickenEggs);
				} else if (c.getString(1).equalsIgnoreCase("Cola")) {
					Cola cola = new Cola();
					cola.db_id = Integer.parseInt(c.getString(0));
					cola.duedate = c.getString(2);
					cola.quantity = Integer.parseInt(c.getString(5));
					productsArray.add(cola);
				} else if (c.getString(1).equalsIgnoreCase("Cucumber")) {
					Cucumber cucumber = new Cucumber();
					cucumber.db_id = Integer.parseInt(c.getString(0));
					cucumber.duedate = c.getString(2);
					cucumber.quantity = Integer.parseInt(c.getString(5));
					productsArray.add(cucumber);
				} else if (c.getString(1).equalsIgnoreCase("Esox")) {
					Esox esox = new Esox();
					esox.db_id = Integer.parseInt(c.getString(0));
					esox.duedate = c.getString(2);
					esox.quantity = Integer.parseInt(c.getString(5));
					productsArray.add(esox);
				} else if (c.getString(1).equalsIgnoreCase("Juice")) {
					Juice juice = new Juice();
					juice.db_id = Integer.parseInt(c.getString(0));
					juice.duedate = c.getString(2);
					juice.quantity = Integer.parseInt(c.getString(5));
					productsArray.add(juice);
				} else if (c.getString(1).equalsIgnoreCase("Ketchup")) {
					Ketchup ketchup = new Ketchup();
					ketchup.db_id = Integer.parseInt(c.getString(0));
					ketchup.duedate = c.getString(2);
					ketchup.quantity = Integer.parseInt(c.getString(5));
					productsArray.add(ketchup);
				} else if (c.getString(1).equalsIgnoreCase("Lemon")) {
					Lemon lemon = new Lemon();
					lemon.db_id = Integer.parseInt(c.getString(0));
					lemon.duedate = c.getString(2);
					lemon.quantity = Integer.parseInt(c.getString(5));
					productsArray.add(lemon);
				} else if (c.getString(1).equalsIgnoreCase("Mayonnaise")) {
					Mayonnaise mayonnaise = new Mayonnaise();
					mayonnaise.db_id = Integer.parseInt(c.getString(0));
					mayonnaise.duedate = c.getString(2);
					mayonnaise.quantity = Integer.parseInt(c.getString(5));
					productsArray.add(mayonnaise);
				} else if (c.getString(1).equalsIgnoreCase("Milk")) {
					Milk milk = new Milk();
					milk.db_id = Integer.parseInt(c.getString(0));
					milk.duedate = c.getString(2);
					milk.quantity = Integer.parseInt(c.getString(5));
					productsArray.add(milk);
				} else if (c.getString(1).equalsIgnoreCase("Mineral")) {
					Mineral mineral = new Mineral();
					mineral.db_id = Integer.parseInt(c.getString(0));
					mineral.duedate = c.getString(2);
					mineral.quantity = Integer.parseInt(c.getString(5));
					productsArray.add(mineral);
				} else if (c.getString(1).equalsIgnoreCase("Mustard")) {
					Mustard mustard = new Mustard();
					mustard.db_id = Integer.parseInt(c.getString(0));
					mustard.duedate = c.getString(2);
					mustard.quantity = Integer.parseInt(c.getString(5));
					productsArray.add(mustard);
				} else if (c.getString(1).equalsIgnoreCase("Orange")) {
					Orange orange = new Orange();
					orange.db_id = Integer.parseInt(c.getString(0));
					orange.duedate = c.getString(2);
					orange.quantity = Integer.parseInt(c.getString(5));
					productsArray.add(orange);
				} else if (c.getString(1).equalsIgnoreCase("Perch")) {
					Perch perch = new Perch();
					perch.db_id = Integer.parseInt(c.getString(0));
					perch.duedate = c.getString(2);
					perch.quantity = Integer.parseInt(c.getString(5));
					productsArray.add(perch);
				} else if (c.getString(1).equalsIgnoreCase("Pork")) {
					Pork pork = new Pork();
					pork.db_id = Integer.parseInt(c.getString(0));
					pork.duedate = c.getString(2);
					pork.quantity = Integer.parseInt(c.getString(5));
					productsArray.add(pork);
				} else if (c.getString(1).equalsIgnoreCase("SourCream")) {
					SourCream sourCream = new SourCream();
					sourCream.db_id = Integer.parseInt(c.getString(0));
					sourCream.duedate = c.getString(2);
					sourCream.quantity = Integer.parseInt(c.getString(5));
					productsArray.add(sourCream);
				} else if (c.getString(1).equalsIgnoreCase("Tomato")) {
					Tomato tomato = new Tomato();
					tomato.db_id = Integer.parseInt(c.getString(0));
					tomato.duedate = c.getString(2);
					tomato.quantity = Integer.parseInt(c.getString(5));
					productsArray.add(tomato);
				} else if (c.getString(1).equalsIgnoreCase("Veal")) {
					Veal veal = new Veal();
					veal.db_id = Integer.parseInt(c.getString(0));
					veal.duedate = c.getString(2);
					veal.quantity = Integer.parseInt(c.getString(5));
					productsArray.add(veal);
				} else if (c.getString(1).equalsIgnoreCase("Yoghurt")) {
					Yoghurt yoghurt = new Yoghurt();
					yoghurt.db_id = Integer.parseInt(c.getString(0));
					yoghurt.duedate = c.getString(2);
					yoghurt.quantity = Integer.parseInt(c.getString(5));
					productsArray.add(yoghurt);
				}
			} while (c.moveToNext());
		}
		db.close();
	}

	public void printAllProducts() {
		tableLayout.removeAllViews();
		for (Product v : productsArray) {
			addItemToList(v.getNameUA(), v.getQuantity(), v.getDaysLeft());
		}
	}

	public void addItemToList(String name, String quantity, String time) {
		TableRow tableRow = (TableRow) View.inflate(getActivity(),
				R.layout.table_item_row_for_fridge, null);
		TextView productName = (TextView) tableRow.findViewById(R.id.alarmProductNameTextView);
		TextView productQuantity = (TextView) tableRow
				.findViewById(R.id.textView2);
		TextView productTimeLeft = (TextView) tableRow
				.findViewById(R.id.textView3);
		productName.setText(name);
		productQuantity.setText(quantity);
		productTimeLeft.setText(time);
		tableLayout.addView(tableRow);
		tableRow.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				TableRow tableRow = (TableRow) v;
				TextView name = (TextView) tableRow
						.findViewById(R.id.alarmProductNameTextView);
				dialogTempText = (String) name.getText();
				AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
						getActivity());
				alertDialogBuilder.setTitle(dialogTempText);
				alertDialogBuilder
						.setMessage("Choose option:")
						.setCancelable(true)
						.setNeutralButton("Delete",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int id) {
										AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
												getActivity());
										alertDialogBuilder.setTitle("Are you sure you want to delete "
												+ Product
														.getProductNameEN(dialogTempText)
												+ "?");
										alertDialogBuilder
												.setMessage("Choose option:")
												.setCancelable(true)
												.setNegativeButton(
														"Yes",
														new DialogInterface.OnClickListener() {
															public void onClick(
																	DialogInterface dialog,
																	int id) {
																String productName = Product.getProductNameEN(dialogTempText); 
																db.open();
																long recordId = db.findRecord(productName);
																if (recordId != -1) {
																	db.deleteRecord(recordId);
																	alarm.cancelAlarm(getActivity(), productName);
																}
																db.close();
																renderList();
															}
														})
												.setNeutralButton(
														"No",
														new DialogInterface.OnClickListener() {
															public void onClick(
																	DialogInterface dialog,
																	int id) {
															}
														});
										AlertDialog alertDialog = alertDialogBuilder
												.create();
										alertDialog.show();
									}
								})
						.setPositiveButton("Cancel",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int id) {
									}
								})
						.setNegativeButton("Info",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int id) {
										Intent intent = new Intent(getActivity(), ViewProductPropActivity.class);
										intent.putExtra("productName",dialogTempText);
										startActivity(intent);
									}
								});
				AlertDialog alertDialog = alertDialogBuilder.create();
				alertDialog.show();
			};
		});
	}
}
