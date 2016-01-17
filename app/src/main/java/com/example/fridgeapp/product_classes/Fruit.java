package com.example.fridgeapp.product_classes;

import java.util.ArrayList;

public class Fruit extends Product {
	
	@Override
	public String getQuantity() {
		return Integer.toString(quantity) + "kg";
	}
	
	public static ArrayList getProductsUA() {
		ArrayList<String> resultArray = new ArrayList<String>();
		resultArray.add("�������");
		resultArray.add("������");
		resultArray.add("������");
		resultArray.add("���������");
		resultArray.add("�����");
		return resultArray;
	}
	
	public static ArrayList getProducts() {
		ArrayList<String> resultArray = new ArrayList<String>();
		resultArray.add("Ananas");
		resultArray.add("Apple");
		resultArray.add("Banana");
		resultArray.add("Orange");
		resultArray.add("Lemon");
		return resultArray;
	}
}
