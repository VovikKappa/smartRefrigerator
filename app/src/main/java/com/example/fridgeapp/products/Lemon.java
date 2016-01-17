package com.example.fridgeapp.products;

import com.example.fridgeapp.product_classes.Fruit;

public class Lemon extends Fruit {
	public Lemon() {
		carbohydrates = "2";
		proteins = "2";
		calories = "3";
		fats = "4";
	}
	
	@Override
	public String getQuantity() {
		return Integer.toString(quantity);
	}
	
	@Override
	public String getName() {
		return "Lemon";
	}
	
	@Override
	public String getNameUA() {
		return "Лимон";
	}
}
