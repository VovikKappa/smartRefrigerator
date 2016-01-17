package com.example.fridgeapp.products;

import com.example.fridgeapp.product_classes.MeatProduct;

public class Pork extends MeatProduct{
	public Pork() {
		carbohydrates = "2";
		proteins = "2";
		calories = "3";
		fats = "4";
	}
	
	@Override
	public String getName() {
		return "Pork";
	}
	
	@Override
	public String getNameUA() {
		return "Свинина";
	}
}
