package com.example.fridgeapp;

import java.util.ArrayList;
import java.util.List;

import com.example.fridgeapp.R;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fridgeapp.db_adapters.DBShopListAdapter;
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
import com.example.fridgeapp.AddItemToShopListActivity;

public class MainActivityShopList extends Fragment {

	TableLayout tableLayout;
	Button add_item_button;
	DBShopListAdapter db;
	List<Product> productsArray;
	protected String dialogTempText;
	View globRootView;

	public MainActivityShopList() {
		productsArray = new ArrayList<Product>();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(
				R.layout.activity_main_fragment_shoplist, container, false);
		globRootView = rootView;
		return rootView;
	}

	@Override
	public void onResume() {
		super.onResume();
		tableLayout = (TableLayout) globRootView
				.findViewById(R.id.activity_main_fragment_shoplist_table);
		add_item_button = (Button) globRootView
				.findViewById(R.id.activity_main_fragment_shoplist_button1);
		add_item_button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getActivity(),
						AddItemToShopListActivity.class);
				startActivity(intent);
			}
		});
		db = new DBShopListAdapter(getActivity());
		renderList();
	}

	public void renderList() {
		createProductsArray();
		sortProductsArray();
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
					ananas.rating = Integer.parseInt(c.getString(2));
					ananas.db_id = Integer.parseInt(c.getString(0));
					productsArray.add(ananas);
				} else if (c.getString(1).equalsIgnoreCase("Apple")) {
					Apple apple = new Apple();
					apple.rating = Integer.parseInt(c.getString(2));
					apple.db_id = Integer.parseInt(c.getString(0));
					productsArray.add(apple);
				} else if (c.getString(1).equalsIgnoreCase("Banana")) {
					Banana banana = new Banana();
					banana.rating = Integer.parseInt(c.getString(2));
					banana.db_id = Integer.parseInt(c.getString(0));
					productsArray.add(banana);
				} else if (c.getString(1).equalsIgnoreCase("Beef")) {
					Beef beef = new Beef();
					beef.rating = Integer.parseInt(c.getString(2));
					beef.db_id = Integer.parseInt(c.getString(0));
					productsArray.add(beef);
				} else if (c.getString(1).equalsIgnoreCase("Bread")) {
					Bread bread = new Bread();
					bread.rating = Integer.parseInt(c.getString(2));
					bread.db_id = Integer.parseInt(c.getString(0));
					productsArray.add(bread);
				} else if (c.getString(1).equalsIgnoreCase("Carassius")) {
					Carassius carassius = new Carassius();
					carassius.rating = Integer.parseInt(c.getString(2));
					carassius.db_id = Integer.parseInt(c.getString(0));
					productsArray.add(carassius);
				} else if (c.getString(1).equalsIgnoreCase("Carrot")) {
					Carrot carrot = new Carrot();
					carrot.rating = Integer.parseInt(c.getString(2));
					carrot.db_id = Integer.parseInt(c.getString(0));
					productsArray.add(carrot);
				} else if (c.getString(1).equalsIgnoreCase("Chicken")) {
					Chicken chicken = new Chicken();
					chicken.rating = Integer.parseInt(c.getString(2));
					chicken.db_id = Integer.parseInt(c.getString(0));
					productsArray.add(chicken);
				} else if (c.getString(1).equalsIgnoreCase("ChickenEggs")) {
					ChickenEggs chickenEggs = new ChickenEggs();
					chickenEggs.rating = Integer.parseInt(c.getString(2));
					chickenEggs.db_id = Integer.parseInt(c.getString(0));
					productsArray.add(chickenEggs);
				} else if (c.getString(1).equalsIgnoreCase("Cola")) {
					Cola cola = new Cola();
					cola.rating = Integer.parseInt(c.getString(2));
					cola.db_id = Integer.parseInt(c.getString(0));
					productsArray.add(cola);
				} else if (c.getString(1).equalsIgnoreCase("Cucumber")) {
					Cucumber cucumber = new Cucumber();
					cucumber.rating = Integer.parseInt(c.getString(2));
					cucumber.db_id = Integer.parseInt(c.getString(0));
					productsArray.add(cucumber);
				} else if (c.getString(1).equalsIgnoreCase("Esox")) {
					Esox esox = new Esox();
					esox.rating = Integer.parseInt(c.getString(2));
					esox.db_id = Integer.parseInt(c.getString(0));
					productsArray.add(esox);
				} else if (c.getString(1).equalsIgnoreCase("Juice")) {
					Juice juice = new Juice();
					juice.rating = Integer.parseInt(c.getString(2));
					juice.db_id = Integer.parseInt(c.getString(0));
					productsArray.add(juice);
				} else if (c.getString(1).equalsIgnoreCase("Ketchup")) {
					Ketchup ketchup = new Ketchup();
					ketchup.rating = Integer.parseInt(c.getString(2));
					ketchup.db_id = Integer.parseInt(c.getString(0));
					productsArray.add(ketchup);
				} else if (c.getString(1).equalsIgnoreCase("Lemon")) {
					Lemon lemon = new Lemon();
					lemon.rating = Integer.parseInt(c.getString(2));
					lemon.db_id = Integer.parseInt(c.getString(0));
					productsArray.add(lemon);
				} else if (c.getString(1).equalsIgnoreCase("Mayonnaise")) {
					Mayonnaise mayonnaise = new Mayonnaise();
					mayonnaise.rating = Integer.parseInt(c.getString(2));
					mayonnaise.db_id = Integer.parseInt(c.getString(0));
					productsArray.add(mayonnaise);
				} else if (c.getString(1).equalsIgnoreCase("Milk")) {
					Milk milk = new Milk();
					milk.rating = Integer.parseInt(c.getString(2));
					milk.db_id = Integer.parseInt(c.getString(0));
					productsArray.add(milk);
				} else if (c.getString(1).equalsIgnoreCase("Mineral")) {
					Mineral mineral = new Mineral();
					mineral.rating = Integer.parseInt(c.getString(2));
					mineral.db_id = Integer.parseInt(c.getString(0));
					productsArray.add(mineral);
				} else if (c.getString(1).equalsIgnoreCase("Mustard")) {
					Mustard mustard = new Mustard();
					mustard.rating = Integer.parseInt(c.getString(2));
					mustard.db_id = Integer.parseInt(c.getString(0));
					productsArray.add(mustard);
				} else if (c.getString(1).equalsIgnoreCase("Orange")) {
					Orange orange = new Orange();
					orange.rating = Integer.parseInt(c.getString(2));
					orange.db_id = Integer.parseInt(c.getString(0));
					productsArray.add(orange);
				} else if (c.getString(1).equalsIgnoreCase("Perch")) {
					Perch perch = new Perch();
					perch.rating = Integer.parseInt(c.getString(2));
					perch.db_id = Integer.parseInt(c.getString(0));
					productsArray.add(perch);
				} else if (c.getString(1).equalsIgnoreCase("Pork")) {
					Pork pork = new Pork();
					pork.rating = Integer.parseInt(c.getString(2));
					pork.db_id = Integer.parseInt(c.getString(0));
					productsArray.add(pork);
				} else if (c.getString(1).equalsIgnoreCase("SourCream")) {
					SourCream sourCream = new SourCream();
					sourCream.rating = Integer.parseInt(c.getString(2));
					sourCream.db_id = Integer.parseInt(c.getString(0));
					productsArray.add(sourCream);
				} else if (c.getString(1).equalsIgnoreCase("Tomato")) {
					Tomato tomato = new Tomato();
					tomato.rating = Integer.parseInt(c.getString(2));
					tomato.db_id = Integer.parseInt(c.getString(0));
					productsArray.add(tomato);
				} else if (c.getString(1).equalsIgnoreCase("Veal")) {
					Veal veal = new Veal();
					veal.rating = Integer.parseInt(c.getString(2));
					veal.db_id = Integer.parseInt(c.getString(0));
					productsArray.add(veal);
				} else if (c.getString(1).equalsIgnoreCase("Yoghurt")) {
					Yoghurt yoghurt = new Yoghurt();
					yoghurt.rating = Integer.parseInt(c.getString(2));
					yoghurt.db_id = Integer.parseInt(c.getString(0));
					productsArray.add(yoghurt);
				}
			} while (c.moveToNext());
		}
		db.close();
	}

	public void sortProductsArray() {
		Product someProduct;
		Boolean sort = true;
		while (sort == true) {
			sort = false;
			for (int i = 1; i < productsArray.size(); i++) {
				if (productsArray.get(i).rating > productsArray.get(i - 1).rating) {
					someProduct = productsArray.get(i);
					productsArray.set(i, productsArray.get(i - 1));
					productsArray.set(i - 1, someProduct);
					sort = true;
				}
			}
		}
	}

	public void printAllProducts() {
		tableLayout.removeAllViews();
		for (Product v : productsArray) {
			addItemToList(v.getNameUA(), Integer.toString(v.rating));
		}
	}

	public void addItemToList(String name, String rating) {
		TableRow tableRow = (TableRow) View.inflate(getActivity(),
				R.layout.table_item_row_for_shoplist, null);
		TextView productName = (TextView) tableRow
				.findViewById(R.id.alarmProductNameTextView);
		TextView productRating = (TextView) tableRow
				.findViewById(R.id.textView2);
		productName.setText(name);
		productRating.setText(rating);
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
						.setNegativeButton("Delete",
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
																db.open();
																long recordId = db
																		.findRecord(Product
																				.getProductNameEN(dialogTempText));
																if (recordId != -1)
																	db.deleteRecord(recordId);
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
						.setNeutralButton("Cancel",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int id) {
									}
								});
				AlertDialog alertDialog = alertDialogBuilder.create();
				alertDialog.show();
			};
		});
	}
}
