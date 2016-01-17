package com.example.fridgeapp.product_classes;

import java.util.ArrayList;

public class Vegetable extends Product {
	
	@Override
	public String getQuantity() {
		return Integer.toString(quantity) + "kg";
	}
	
	public static ArrayList getProductsUA() {
		ArrayList<String> resultArray = new ArrayList<String>();
		resultArray.add("Огірки");
		resultArray.add("Томати");
		resultArray.add("Морква");
		return resultArray;
	}
	
	public static ArrayList getProducts() {
		ArrayList<String> resultArray = new ArrayList<String>();
		resultArray.add("Cucumber");
		resultArray.add("Tomato");
		resultArray.add("Carrot");
		return resultArray;
	}
}
