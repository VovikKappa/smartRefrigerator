package com.example.fridgeapp.products;

import com.example.fridgeapp.product_classes.Fruit;

public class Apple extends Fruit {
	public Apple() {
		carbohydrates = "2";
		proteins = "2";
		calories = "3";
		fats = "4";
	}
	
	@Override
	public String getName() {
		return "Apple";
	}
	
	@Override
	public String getNameUA() {
		return "������";
	}
}
