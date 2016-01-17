package com.example.fridgeapp.products;

import com.example.fridgeapp.product_classes.Sauce;

public class Mustard extends Sauce {
	public Mustard() {
		carbohydrates = "2";
		proteins = "2";
		calories = "3";
		fats = "4";
	}
	
	@Override
	public String getName() {
		return "Mustard";
	}
	
	@Override
	public String getNameUA() {
		return "Ã³ð÷èöÿ";
	}
}
