package com.example.fridgeapp.product_classes;

import java.util.ArrayList;

public class Sauce extends Product {

	public static ArrayList getProductsUA() {
		ArrayList<String> resultArray = new ArrayList<String>();
		resultArray.add("Кетчуп");
		resultArray.add("Гірчиця");
		resultArray.add("Майонез");
		return resultArray;
	}
	
	public static ArrayList getProducts() {
		ArrayList<String> resultArray = new ArrayList<String>();
		resultArray.add("Ketchup");
		resultArray.add("Mustard");
		resultArray.add("Mayonnaise");
		return resultArray;
	}
}
