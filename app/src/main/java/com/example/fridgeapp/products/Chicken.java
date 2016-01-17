package com.example.fridgeapp.products;

import com.example.fridgeapp.product_classes.MeatProduct;

public class Chicken extends MeatProduct {
	public Chicken() {
		carbohydrates = "2";
		proteins = "2";
		calories = "3";
		fats = "4";
	}
	
	@Override
	public String getName() {
		return "Chicken";
	}
	
	@Override
	public String getNameUA() {
		return "Курятина";
	}
}
