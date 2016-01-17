package com.example.fridgeapp.products;

import com.example.fridgeapp.product_classes.Fruit;

public class Ananas extends Fruit {
	public Ananas() {
		carbohydrates = "2";
		proteins = "2";
		calories = "3";
		fats = "4";
	}
	
	@Override
	public String getName() {
		return "Ananas";
	}
	
	@Override
	public String getNameUA() {
		return "Ананаси";
	}
}
