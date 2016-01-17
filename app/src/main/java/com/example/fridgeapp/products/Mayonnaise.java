package com.example.fridgeapp.products;

import com.example.fridgeapp.product_classes.Sauce;

public class Mayonnaise extends Sauce {
	public Mayonnaise() {
		carbohydrates = "2";
		proteins = "2";
		calories = "3";
		fats = "4";
	}
	
	@Override
	public String getName() {
		return "Mayonnaise";
	}
	
	@Override
	public String getNameUA() {
		return "Майонез";
	}
}
