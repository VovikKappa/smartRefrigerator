package com.example.fridgeapp.products;

import com.example.fridgeapp.product_classes.Vegetable;

public class Carrot extends Vegetable {
	public Carrot() {
		carbohydrates = "2";
		proteins = "2";
		calories = "3";
		fats = "4";
	}
	
	@Override
	public String getName() {
		return "Carrot";
	}
	
	@Override
	public String getNameUA() {
		return "Морква";
	}
}
