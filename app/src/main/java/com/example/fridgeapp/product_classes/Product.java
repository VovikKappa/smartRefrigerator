package com.example.fridgeapp.product_classes;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class Product {
	protected static String calories;
	protected static String proteins;
	protected static String fats;
	protected static String carbohydrates;
	public String surrogates;
	public String duedate;
	public String time;
	public int rating = 1;
	public int quantity = 0;
	public int db_id = 0;
	public Boolean alarm = false;
	
	public Product() {
		carbohydrates = "0";
		proteins = "0";
		fats = "0";
		calories = "0";
	}
	
	public String getCalories() {
		return calories;
	}
	
	public String getProteins() {
		return proteins;
	}
	
	public String getFats() {
		return fats;
	}
	
	public String getCarbohydrates() {
		return carbohydrates;
	}
	
	public String getCaloriesTitle() {
		return "Calories/100g:";
	}
	
	public String getCarbohydratesTitle() {
		return "Carbohydrates/100g:";
	}
	
	public String getProteinsTitle() {
		return "Proteins/100g:";
	}
	
	public String getFatsTitle() {
		return "Fats/100g:";
	}
	
	public String getQuantity() {
		return Integer.toString(quantity);
	}
	
	public String getDaysLeft() {
		Date endDate = parseDate(duedate);
		Date curDate = parseDate(giveCurrentDate());
	    Calendar cal1 = Calendar.getInstance();
	    cal1.setTime(curDate);
	    Calendar cal2 = Calendar.getInstance();
	    cal2.setTime(endDate);
	    long daysLeft = daysBetween(cal1, cal2);
	    if (daysLeft == 1) return "1 day";
	    else return Long.toString(daysLeft) + " days";
	}
	
	public String getName() {
		return "Product";
	}
	
	public String getNameUA() {
		return "Продукт";
	}
	
	private Date parseDate(String dateString) {
	    DateFormat df = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
	    try {
	    	Date endDate =  df.parse(dateString);
		    return endDate;
	    } catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private String giveCurrentDate()
    {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(cal.getTime());
    }
	
	private long daysBetween(Calendar startDate, Calendar endDate) {
        Calendar date = (Calendar) startDate.clone();
        long daysBetween = 0;
        while (date.before(endDate)) {
            date.add(Calendar.DAY_OF_MONTH, 1);
            daysBetween++;
        }
        return daysBetween;
    }
	
	public static ArrayList getProductTypes() {
		ArrayList<String> resultArray = new ArrayList<String>();
		resultArray.add("Meat Products");
		resultArray.add("Beverages");
		resultArray.add("Bread Products");
		resultArray.add("Eggs");
		resultArray.add("Fish Products");
		resultArray.add("Fruits");
		resultArray.add("Milk Products");
		resultArray.add("Sauces");
		resultArray.add("Vegetables");
		return resultArray;
	}
	
	public static ArrayList getProductTypesUA() {
		ArrayList<String> resultArray = new ArrayList<String>();
		resultArray.add("М'ясо");
		resultArray.add("Напитки");
		resultArray.add("Хлібо-булочні");
		resultArray.add("Яйця");
		resultArray.add("Риба");
		resultArray.add("Фрукти");
		resultArray.add("Молочні");
		resultArray.add("Соуси");
		resultArray.add("Овочі");
		return resultArray;
	}
	
	public static ArrayList getProductsUA() {
		ArrayList<String> resultArray = new ArrayList<String>();
		resultArray.add("Ананаси");
		resultArray.add("Яблука");
		resultArray.add("Банани");
		resultArray.add("Апельсини");
		resultArray.add("Лимон");
		resultArray.add("Яловичина");
		resultArray.add("Свинина");
		resultArray.add("Телятина");
		resultArray.add("Курятина");
		resultArray.add("Щука");
		resultArray.add("Окунь");
		resultArray.add("Карась");
		resultArray.add("Молоко");
		resultArray.add("Йогурт");
		resultArray.add("Сметана");
		resultArray.add("Кетчуп");
		resultArray.add("Гірчиця");
		resultArray.add("Майонез");
		resultArray.add("Огірки");
		resultArray.add("Томати");
		resultArray.add("Морква");
		resultArray.add("Мінералка");
		resultArray.add("Сік");
		resultArray.add("Кола");
		resultArray.add("Хліб");
		resultArray.add("Яйця курячі");
		return resultArray;
	}
	
	public static ArrayList getProducts() {
		ArrayList<String> resultArray = new ArrayList<String>();
		resultArray.add("Ananas");
		resultArray.add("Apple");
		resultArray.add("Banana");
		resultArray.add("Orange");
		resultArray.add("Lemon");
		resultArray.add("Beef");
		resultArray.add("Pork");
		resultArray.add("Veal");
		resultArray.add("Chicken");
		resultArray.add("Esox");
		resultArray.add("Perch");
		resultArray.add("Carassius");
		resultArray.add("Milk");
		resultArray.add("Yoghurt");
		resultArray.add("SourCream");
		resultArray.add("Ketchup");
		resultArray.add("Mustard");
		resultArray.add("Mayonnaise");
		resultArray.add("Cucumber");
		resultArray.add("Tomato");
		resultArray.add("Carrot");
		resultArray.add("Mineral");
		resultArray.add("Juice");
		resultArray.add("Cola");
		resultArray.add("Bread");
		resultArray.add("ChickenEggs");
		return resultArray;
	}
	
	public static String getProductNameEN(String productNameUA) {
		for (int i = 0; i < getProducts().size(); i++) {
			if (productNameUA.equalsIgnoreCase(getProductsUA().get(i).toString())) {
				return getProducts().get(i).toString();
			}
		}
		return "not found";
	}
}