package com.example.fridgeapp.products;

import com.example.fridgeapp.product_classes.Vegetable;

public class Cucumber extends Vegetable {
	public Cucumber() {
		carbohydrates = "2";
		proteins = "2";
		calories = "3";
		fats = "4";
	}
	
	@Override
	public String getName() {
		return "Cucumber";
	}
	
	@Override
	public String getNameUA() {
		return "ќг≥рки";
	}
}
