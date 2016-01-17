package com.example.fridgeapp.products;

import com.example.fridgeapp.product_classes.MilkProduct;

public class Yoghurt extends MilkProduct {
	public Yoghurt() {
		carbohydrates = "2";
		proteins = "2";
		calories = "3";
		fats = "4";
	}
	
	@Override
	public String getName() {
		return "Yoghurt";
	}
	
	@Override
	public String getNameUA() {
		return "Йогурт";
	}
}
