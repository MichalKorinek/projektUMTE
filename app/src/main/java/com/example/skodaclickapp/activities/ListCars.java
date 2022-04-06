package com.example.skodaclickapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.skodaclickapp.R;
import com.example.skodaclickapp.ReadCsvData;
import com.example.skodaclickapp.RecyclerAdapter;
import com.example.skodaclickapp.listeners.DetailClickListener;
import com.example.skodaclickapp.model.Car;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Objects;

public class ListCars extends AppCompatActivity implements DetailClickListener {


    private List<Car> cars;
    private final ReadCsvData readCsvData = new ReadCsvData();
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_cars);
        hideTitle();
        ImageButton backButton = findViewById(R.id.backImageButton);
        backButton.setOnClickListener(view -> openMainActivity());
        recyclerView = findViewById(R.id.carListView);
        readData();
        setAdapter();
    }

    private void openMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


    private void setAdapter() {
        RecyclerAdapter adapter = new RecyclerAdapter(cars, this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    private void readData(){
        InputStream is = this.getResources().openRawResource(R.raw.data);
        try {
            cars = readCsvData.readData(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void hideTitle(){
        Objects.requireNonNull(getSupportActionBar()).hide();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    @Override
    public void onDetailClick(int id) {
        Intent intent = new Intent(this, DetailOfCar.class);
        String idString = id + "";
        intent.putExtra("id", idString);
        startActivity(intent);
    }
}