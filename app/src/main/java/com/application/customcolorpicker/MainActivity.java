package com.application.customcolorpicker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {

    private TextView tvSelectedColor;
    private RadioGroup rgColorPicker;
    private String[] colorPalette;
    public static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        createColorPalette();
        initListeners();
    }


    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {

        //get id of checked button
        int selectedButtonId = radioGroup.getCheckedRadioButtonId();

        //id indicate position of color in array, getting selected color hash value
        String selectedColor = colorPalette[i];

        //setting selected color hash value to textview and changing its color
        tvSelectedColor.setText("Selected Color : "+selectedColor);
        tvSelectedColor.setTextColor(ColorStateList.valueOf(Color.parseColor(colorPalette[i])));
    }


    private void createColorPalette() {
       colorPalette = getResources().getStringArray(R.array.color_palette);

       for (int i = 0; i < colorPalette.length; i++)
       {
           //create radio button by inflating radio button layout
           LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
           View rbView = inflater.inflate(R.layout.custom_radio_button,null);
           RadioButton rb = (RadioButton) rbView.getRootView();

           //set unique id
           rb.setId(i);

           //set some margin to radio buttons
           RadioGroup.LayoutParams params = new RadioGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
           params.setMargins(6, 6, 6, 6);
           rb.setLayoutParams(params);

           //set color from pallete
           rb.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(colorPalette[i])));

           //add view
           rgColorPicker.addView(rb);
       }
    }



    private void initViews() {
        rgColorPicker = findViewById(R.id.activity_main_rg_color_picker);
        tvSelectedColor = findViewById(R.id.activity_main_tv_selected_color);
    }

    private void initListeners() {
        rgColorPicker.setOnCheckedChangeListener(this);
    }

}