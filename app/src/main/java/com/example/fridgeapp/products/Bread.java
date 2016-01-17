package com.example.fridgeapp.products;

import com.example.fridgeapp.product_classes.BreadProduct;

public class Bread extends BreadProduct {
	public Bread() {
		carbohydrates = "2";
		proteins = "2";
		calories = "3";
		fats = "4";
	}
	
	@Override
	public String getName() {
		return "Bread";
	}
	
	@Override
	public String getNameUA() {
		return "’Î≥·";
	}
}
