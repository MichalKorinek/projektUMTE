package com.example.skodaclickapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.skodaclickapp.model.Car;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private List<Car> cars;
    private ReadCsvData readCsvData = new ReadCsvData();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
        TextView textView = findViewById(R.id.csvData);
        String text = "";
        for (int i = 0; i < cars.size(); i++) {
            text += cars.get(i).toString();
        }
        textView.setText(text);
    }



}