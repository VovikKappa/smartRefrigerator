package com.example.fridgeapp.products;

import com.example.fridgeapp.product_classes.Eggs;

public class ChickenEggs extends Eggs {
	public ChickenEggs() {
		carbohydrates = "2";
		proteins = "2";
		calories = "3";
		fats = "4";
	}
	
	@Override
	public String getName() {
		return "ChickenEggs";
	}
	
	@Override
	public String getNameUA() {
		return "���� ������";
	}
}