package com.example.fridgeapp;

import java.util.ArrayList;

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

import android.R.array;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class AddItemToShopListActivity extends Activity {

	Button add_item_button;
	DBShopListAdapter db;
	Spinner spinnerProductType;
	Spinner spinnerProduct;
	Spinner spinnerProductRating;
	ArrayAdapter<String> productAdapter;
	ArrayList<String> productArray;
	Context context = this;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_item_to_shop_list);
		setTitle("Add item to shoplist");
		db = new DBShopListAdapter(this);
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
		ArrayList<String> productRatingArray = new ArrayList<String>() {
			{
				add("Select");
				for (int i = 10; i > 0; i--) {
					add(Integer.toString(i));
				}
			}
		};
		spinnerProductRating = (Spinner) findViewById(R.id.products_rating_spinner);
		ArrayAdapter<String> productRatingAdapter = new ArrayAdapter<String>(
				this, android.R.layout.simple_spinner_dropdown_item,
				productRatingArray);
		spinnerProductRating.setAdapter(productRatingAdapter);
		// binding click listener
		add_item_button = (Button) this
				.findViewById(R.id.activity_add_item_to_shop_list_button);
		add_item_button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (spinnerProductType.getSelectedItem().toString() == "Select" ||
					spinnerProduct.getSelectedItem().toString() == "Select" ||
					spinnerProductRating.getSelectedItem().toString() == "Select") {
					DisplayToast("Not all items are selected!");
					return;
				}
				String name = (String) spinnerProduct.getSelectedItem();
				name = Product.getProductNameEN(name);
				int rating = Integer.parseInt(spinnerProductRating.getSelectedItem().toString());
				db.open();
				long id = db.createRecord(name, rating);
				db.close();
				Intent intent = new Intent(context, MainActivity.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				intent.putExtra("changeTab","2");
				startActivity(intent);
			}
		});
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
		getMenuInflater().inflate(R.menu.add_item_to_shop_list, menu);
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
