package com.example.fridgeapp.product_classes;

import java.util.ArrayList;

public class Beverage extends Product {
	
	public static ArrayList getProductsUA() {
		ArrayList<String> resultArray = new ArrayList<String>();
		resultArray.add("Мінералка");
		resultArray.add("Сік");
		resultArray.add("Кола");
		return resultArray;
	}
	
	public static ArrayList getProducts() {
		ArrayList<String> resultArray = new ArrayList<String>();
		resultArray.add("Mineral");
		resultArray.add("Juice");
		resultArray.add("Cola");
		return resultArray;
	}
	
	@Override
	public String getCaloriesTitle() {
		return "Calories/100ml:";
	}
	
	@Override
	public String getCarbohydratesTitle() {
		return "Carbohydrates/100ml:";
	}
	
	@Override
	public String getProteinsTitle() {
		return "Proteins/100ml:";
	}
	
	@Override
	public String getFatsTitle() {
		return "Fats/100ml:";
	}
}