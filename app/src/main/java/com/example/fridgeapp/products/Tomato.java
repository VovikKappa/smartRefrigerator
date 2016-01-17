package com.example.fridgeapp.products;

import com.example.fridgeapp.product_classes.Vegetable;

public class Tomato extends Vegetable {
	public Tomato() {
		carbohydrates = "2";
		proteins = "2";
		calories = "3";
		fats = "4";
	}
	
	@Override
	public String getName() {
		return "Tomato";
	}
	
	@Override
	public String getNameUA() {
		return "Томати";
	}
}
