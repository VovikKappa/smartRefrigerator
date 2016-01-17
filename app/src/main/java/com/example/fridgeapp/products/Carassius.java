package com.example.fridgeapp.products;

import com.example.fridgeapp.product_classes.FishProduct;

public class Carassius extends FishProduct {
	public Carassius() {
		carbohydrates = "2";
		proteins = "2";
		calories = "3";
		fats = "4";
	}
	
	@Override
	public String getName() {
		return "Carassius";
	}
	
	@Override
	public String getNameUA() {
		return "Карась";
	}
}
