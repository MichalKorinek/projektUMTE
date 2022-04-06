package com.example.skodaclickapp.activities;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.skodaclickapp.R;
import com.example.skodaclickapp.ReadCsvData;
import com.example.skodaclickapp.model.Car;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Objects;

public class ListCars extends AppCompatActivity {


    private List<Car> cars;
    private final ReadCsvData readCsvData = new ReadCsvData();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_cars);
        hideTitle();
        readData();
        setText();
    }

    private void readData(){
        InputStream is = this.getResources().openRawResource(R.raw.data);
        try {
            cars = readCsvData.readData(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setText() {
        TextView textView = findViewById(R.id.csvTest);
        String text = "";
        for (int i = 0; i < cars.size(); i++) {
            text += cars.get(i).toString();
        }
        textView.setText(text);
    }

    private void hideTitle(){
        Objects.requireNonNull(getSupportActionBar()).hide();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
}