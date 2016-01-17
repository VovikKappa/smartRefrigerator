package com.example.fridgeapp.products;

import com.example.fridgeapp.product_classes.Sauce;

public class Ketchup extends Sauce {
	public Ketchup() {
		carbohydrates = "2";
		proteins = "2";
		calories = "3";
		fats = "4";
	}
	
	@Override
	public String getName() {
		return "Ketchup";
	}
	
	@Override
	public String getNameUA() {
		return "Кетчуп";
	}
}
