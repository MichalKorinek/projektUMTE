package com.example.skodaclickapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.skodaclickapp.R;
import com.example.skodaclickapp.ReadCsvData;
import com.example.skodaclickapp.model.Car;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Objects;

public class DetailOfCar extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_of_car);
        hideTitle();
        Intent intent = getIntent();
        try {
            readData(intent.getStringExtra("id"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        ImageButton imageButton = findViewById(R.id.detailImageButton);
        imageButton.setOnClickListener(view -> openListCarsActivity());


    }



    @SuppressLint("SetTextI18n")
    private void readData(String id) throws IOException {
        InputStream is = this.getResources().openRawResource(R.raw.data);
        ReadCsvData readCsvData = new ReadCsvData();
        Car car = readCsvData.readDataById(is, id);


        TextView idOfCarDetailed = findViewById(R.id.idOfCarDetailed);
        idOfCarDetailed.setText("ID: " + car.getId());
        TextView spzTextView = findViewById(R.id.spzTextView);
        spzTextView.setText("SPZ: " + car.getSpz());
        TextView detailTypeOfCar = findViewById(R.id.detailTypeOfCar);
        detailTypeOfCar.setText("ŠKODA " + car.getType());
        ImageView imageView = findViewById(R.id.detailImageView);
        switch (car.getType()) {
            case "Scala":
                if (car.getColor().equals("red")) {
                    imageView.setImageResource(R.drawable.scala_red);
                } else if (car.getColor().equals("blue")) {
                    imageView.setImageResource(R.drawable.scala_blue);
                } else {
                    imageView.setImageResource(R.drawable.scala_white);
                }
                break;
            case "Octavia":
                if (car.getColor().equals("blue")) {
                    imageView.setImageResource(R.drawable.octavia_blue);
                } else if (car.getColor().equals("green")) {
                    imageView.setImageResource(R.drawable.octavia_green);
                } else if (car.getColor().equals("grey")) {
                    imageView.setImageResource(R.drawable.octavia_grey);
                } else {
                    imageView.setImageResource(R.drawable.octavia_white);
                }
                break;
            case "KamiQ":
                if (car.getColor().equals("black")) {
                    imageView.setImageResource(R.drawable.kamiq_black);
                } else {
                    imageView.setImageResource(R.drawable.kamiq_red);
                }
                break;
            default:
                imageView.setImageResource(R.drawable.scala_white);

        }

        EditText fuelEditText = findViewById(R.id.fuelEditText);
        fuelEditText.setText((car.getFuel() * 100) + " %");
        EditText descriptionEditText = findViewById(R.id.descriptionEditText);
        if(car.getDescription().equals("")){
            descriptionEditText.setText(" ");
        } else descriptionEditText.setText(car.getDescription());


        TextView fuelCardIdDetail = findViewById(R.id.fuelCardIdDetail);
        fuelCardIdDetail.setText("Tankovací karta č. " + car.getFuelCard());
        TextView colorDetail = findViewById(R.id.colorDetail);
        colorDetail.setText("Barva vozu: " + car.getColor());

    }
    /*
    private void saveFile() throws IOException, CsvException {
        InputStreamReader isr = new InputStreamReader(this.getResources().openRawResource(R.raw.data));
        CSVReader csvReader = new CSVReader(isr);
        List<String[]> allData = csvReader.readAll();

        //CSVWriter csvWriter = new CSVWriter(new FileWriter())

    }

     */

    private void openListCarsActivity() {
        Intent intent = new Intent(this, ListCars.class);
        startActivity(intent);
    }

    private void hideTitle(){
        Objects.requireNonNull(getSupportActionBar()).hide();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
}