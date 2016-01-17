package com.example.fridgeapp.product_classes;

import java.util.ArrayList;

public class FishProduct extends Product {
	
	@Override
	public String getQuantity() {
		return Integer.toString(quantity) + "kg";
	}
	
	public static ArrayList getProducts() {
		ArrayList<String> resultArray = new ArrayList<String>();
		resultArray.add("Carassius");
		resultArray.add("Esox");
		resultArray.add("Perch");
		return resultArray;
	}
	
	public static ArrayList getProductsUA() {
		ArrayList<String> resultArray = new ArrayList<String>();
		resultArray.add("Карась");
		resultArray.add("Щука");
		resultArray.add("Окунь");
		return resultArray;
	}
}
