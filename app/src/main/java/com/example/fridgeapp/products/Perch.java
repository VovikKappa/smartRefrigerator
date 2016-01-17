package com.example.fridgeapp.products;

import com.example.fridgeapp.product_classes.FishProduct;

public class Perch extends FishProduct {
	public Perch() {
		carbohydrates = "2";
		proteins = "2";
		calories = "3";
		fats = "4";
	}
	
	@Override
	public String getName() {
		return "Perch";
	}
	
	@Override
	public String getNameUA() {
		return "Окунь";
	}
}
