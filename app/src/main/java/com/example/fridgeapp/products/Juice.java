package com.example.fridgeapp.products;

import com.example.fridgeapp.product_classes.Beverage;

public class Juice extends Beverage {
	public Juice() {
		carbohydrates = "2";
		proteins = "2";
		calories = "3";
		fats = "4";
	}
	
	@Override
	public String getName() {
		return "Juice";
	}
	
	@Override
	public String getNameUA() {
		return "Ñ³ê";
	}
}
