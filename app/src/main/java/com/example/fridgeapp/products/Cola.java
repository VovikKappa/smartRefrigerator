package com.example.fridgeapp.products;

import com.example.fridgeapp.product_classes.Beverage;

public class Cola extends Beverage {
	public Cola() {
		carbohydrates = "2";
		proteins = "2";
		calories = "3";
		fats = "4";
	}
	
	@Override
	public String getName() {
		return "Cola";
	}
	
	@Override
	public String getNameUA() {
		return "Кола";
	}
}
