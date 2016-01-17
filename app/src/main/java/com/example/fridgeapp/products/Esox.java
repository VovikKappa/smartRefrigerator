package com.example.fridgeapp.products;

import com.example.fridgeapp.product_classes.FishProduct;

public class Esox extends FishProduct {
	public Esox() {
		carbohydrates = "2";
		proteins = "2";
		calories = "3";
		fats = "4";
	}
	
	@Override
	public String getName() {
		return "Esox";
	}
	
	@Override
	public String getNameUA() {
		return "йѓър";
	}
}
