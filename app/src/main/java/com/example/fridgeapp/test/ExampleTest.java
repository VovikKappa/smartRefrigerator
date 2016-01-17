package com.example.fridgeapp.test;

import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.widget.Button;
import android.widget.TextView;

import com.example.fridgeapp.AlarmSetTimeActivity;
import com.example.fridgeapp.R;
import com.example.fridgeapp.product_classes.Fruit;
import com.example.fridgeapp.products.Milk;
import com.example.fridgeapp.products.Mustard;

import java.util.ArrayList;

/**
 * Created by Юрий on 17.01.2016.
 */
public class ExampleTest extends ActivityInstrumentationTestCase2<AlarmSetTimeActivity> {
    public ExampleTest() {
        super(AlarmSetTimeActivity.class);
    }


    public void test() throws Exception {
        final int expected = 5;
        final int reality = 5;
        assertEquals("false1111", expected, reality);
    }

    public void testMilk() throws Exception {
        Milk milkclass= new Milk();
        final String calories = milkclass.getCaloriesTitle();
        assertEquals("getCaloriesTitle method works wrong", "Calories/100ml:", calories);
    }


    public void testMustard() throws Exception {
        Mustard mustardclass= new Mustard();
        final String actual = mustardclass.getName();
        assertEquals("getName method works wrong", "Mustard", actual);
    }

    public void testTypeFruit() throws Exception {
        final ArrayList<String> actual = Fruit.getProductsUA();
       final ArrayList<String> excepted = new ArrayList<String>() {
       };
     //   ArrayList<String> places = new ArrayList<String>(Arrays.asList("Ананаси", "C?rdoba", "La Plata"));
        excepted.add("Ананаси");
        excepted.add("Яблука");
        excepted.add("Банани");
        excepted.add("Апельсини");
        excepted.add("Лимон");
        assertEquals(excepted, actual);
    }

    public void testTextView() throws Exception {
        AlarmSetTimeActivity activity = getActivity();
        final TextView nameEditText =
                (TextView) activity.findViewById(R.id.messageText);
        getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                nameEditText.requestFocus();
            }
        });

        getInstrumentation().waitForIdleSync();
        getInstrumentation().sendStringSync("Jake");


    }


    public void testButton() throws Exception {
        AlarmSetTimeActivity activity = getActivity();
        final Button savebutton =
                (Button) activity.findViewById(R.id.saveSettingsButton);
        TouchUtils.clickView(this, savebutton);


    }
}