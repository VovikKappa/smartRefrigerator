package com.example.fridgeapp.product_classes;

import java.util.ArrayList;

public class BreadProduct extends Product {

	public static ArrayList getProducts() {
		ArrayList<String> resultArray = new ArrayList<String>();
		resultArray.add("Bread");
		return resultArray;
	}
	
	public static ArrayList getProductsUA() {
		ArrayList<String> resultArray = new ArrayList<String>();
		resultArray.add("’Î≥·");
		return resultArray;
	}
}