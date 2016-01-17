package com.example.fridgeapp.products;

import com.example.fridgeapp.product_classes.Fruit;

public class Banana extends Fruit {
	public Banana() {
		carbohydrates = "2";
		proteins = "2";
		calories = "3";
		fats = "4";
	}
	
	@Override
	public String getName() {
		return "Banana";
	}
	
	@Override
	public String getNameUA() {
		return "Банани";
	}
}
