package com.example.fridgeapp.product_classes;

import java.util.ArrayList;

public class MilkProduct extends Product {
	
	@Override
	public String getQuantity() {
		return Integer.toString(quantity) + "l";
	}
	
	public static ArrayList getProductsUA() {
		ArrayList<String> resultArray = new ArrayList<String>();
		resultArray.add("������");
		resultArray.add("������");
		resultArray.add("�������");
		return resultArray;
	}
	
	public static ArrayList getProducts() {
		ArrayList<String> resultArray = new ArrayList<String>();
		resultArray.add("Milk");
		resultArray.add("Yoghurt");
		resultArray.add("SourCream");
		return resultArray;
	}
}
