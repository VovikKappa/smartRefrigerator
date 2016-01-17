package com.example.fridgeapp.products;

import com.example.fridgeapp.product_classes.MeatProduct;

public class Beef extends MeatProduct {
	public Beef() {
		carbohydrates = "2";
		proteins = "2";
		calories = "3";
		fats = "4";
	}
	
	@Override
	public String getName() {
		return "Beef";
	}
	
	@Override
	public String getNameUA() {
		return "яловичина";
	}
}
