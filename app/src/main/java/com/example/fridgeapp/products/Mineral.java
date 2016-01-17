package com.example.fridgeapp.products;

import com.example.fridgeapp.product_classes.Beverage;

public class Mineral extends Beverage {
	public Mineral() {
		carbohydrates = "2";
		proteins = "2";
		calories = "3";
		fats = "4";
	}
	
	@Override
	public String getName() {
		return "Mineral";
	}
	
	@Override
	public String getNameUA() {
		return "Мінералка";
	}
}
