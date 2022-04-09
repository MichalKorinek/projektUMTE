package com.example.skodaclickapp.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.text.Editable;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.skodaclickapp.R;
import com.example.skodaclickapp.ReadCsvData;
import com.example.skodaclickapp.model.Car;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
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

        ImageButton saveDetailButton = findViewById(R.id.saveDetailButton);
        saveDetailButton.setOnClickListener(view -> {
            try {
                saveFile(intent.getStringExtra("id"));
            } catch (IOException | CsvException e) {
                e.printStackTrace();
            }
        });


    }


    @SuppressLint("SetTextI18n")
    private void readData(String id) throws IOException {
        File file = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        InputStream is = new FileInputStream(new File(file, "data.csv"));
        ReadCsvData readCsvData = new ReadCsvData();
        Car car = readCsvData.readDataById(is, id);
        if(car.getId() == 40400404){
            Toast.makeText(DetailOfCar.this, "Automobil nebyl nalezen!", Toast.LENGTH_SHORT).show();
            openListCarsActivity();

        } else {
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
            if (car.getDescription().equals("")) {
                descriptionEditText.setText(" ");
            } else descriptionEditText.setText(car.getDescription());


            TextView fuelCardIdDetail = findViewById(R.id.fuelCardIdDetail);
            fuelCardIdDetail.setText("Tankovací karta č. " + car.getFuelCard());
            TextView colorDetail = findViewById(R.id.colorDetail);
            colorDetail.setText("Barva vozu: " + car.getColor());

        }




    }

    private void saveFile(String id) throws IOException, CsvException {
        int dataFile = 0;
        File[] file = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).listFiles();
        for (int i = 0; i < Objects.requireNonNull(file).length; i++) {
            if (file[i].getName().equals("data")) {
                dataFile = i;
            }
        }
        InputStream is = new FileInputStream(file[dataFile]);
        ReadCsvData readCsvData = new ReadCsvData();
        List<Car> carList = readCsvData.readData(is);

        EditText descriptionEditText = findViewById(R.id.descriptionEditText);
        EditText fuel = findViewById(R.id.fuelEditText);

        for (int i = 0; i < carList.size(); i++) {
            if(carList.get(i).getId() == Integer.parseInt(id)){
                String[] carRow = String.valueOf(fuel.getText()).split(" ");
                Float value = (Float.parseFloat(carRow[0]))/100;
                carList.get(i).setFuel(value);
                carList.get(i).setDescription(String.valueOf(descriptionEditText.getText()));
            }
        }

        List<String[]> allData = new ArrayList<>();
        String[] rowData = new String[]{"carNumber", "type", "color", "SPZ", "fuel", "description", "fuelCard"};
        allData.add(rowData);
        for (int i = 0; i < carList.size(); i++) {
            allData.add(carList.get(i).toArray());
        }
        CSVWriter csvWriter = new CSVWriter(new FileWriter(file[dataFile]));
        csvWriter.writeAll(allData, false);
        csvWriter.flush();

        Toast toast=Toast.makeText(getApplicationContext(),"Data úspěšně uložena",Toast.LENGTH_SHORT);
        toast.show();

        Intent intent = new Intent(this, DetailOfCar.class);
        String idString = id + "";
        intent.putExtra("id", idString);
        startActivity(intent);
    }

    private void openListCarsActivity() {
        Intent intent = new Intent(this, ListCars.class);
        startActivity(intent);
    }

    private void hideTitle() {
        Objects.requireNonNull(getSupportActionBar()).hide();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
}