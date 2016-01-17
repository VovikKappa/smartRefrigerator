package com.example.fridgeapp.product_classes;

import java.util.ArrayList;

public class MeatProduct extends Product{
	
	@Override
	public String getQuantity() {
		return Integer.toString(quantity) + "kg";
	}
	
	public static ArrayList getProductsUA() {
		ArrayList<String> resultArray = new ArrayList<String>();
		resultArray.add("���������");
		resultArray.add("�������");
		resultArray.add("��������");
		resultArray.add("��������");
		return resultArray;
	}
	
	public static ArrayList getProducts() {
		ArrayList<String> resultArray = new ArrayList<String>();
		resultArray.add("Beef");
		resultArray.add("Pork");
		resultArray.add("Veal");
		resultArray.add("Chicken");
		return resultArray;
	}
}
