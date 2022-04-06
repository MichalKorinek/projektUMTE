package com.example.skodaclickapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.skodaclickapp.R;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideTitle();
        setContentView(R.layout.activity_main);
        ImageButton listCars = findViewById(R.id.listCars);
        listCars.setOnClickListener(view -> openListCarsActivity());

    }


    private void openListCarsActivity() {
        Intent intent = new Intent(this, ListCars.class);
        startActivity(intent);
    }


    private void hideTitle(){
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }





}