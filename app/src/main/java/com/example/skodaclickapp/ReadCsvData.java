package com.example.skodaclickapp;

import android.widget.Toast;

import com.example.skodaclickapp.activities.DetailOfCar;
import com.example.skodaclickapp.model.Car;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ReadCsvData {

    public List<Car> readData(InputStream is) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        List<Car> cars = new ArrayList<>();
        String line;
        while((line = br.readLine()) != null){
            String[] tokens = line.split(",");
            Car car = new Car();
            if(!tokens[0].equals("carNumber")){
                car.setId(Integer.parseInt(tokens[0]));
                car.setType(tokens[1]);
                car.setColor(tokens[2]);
                car.setSpz(tokens[3]);
                car.setFuel(Float.parseFloat(tokens[4]));
                car.setDescription(tokens[5]);
                car.setFuelCard(Integer.parseInt(tokens[6]));
                cars.add(car);
            }
        }
        return cars;
    }

    public Car readDataById(InputStream is, String id) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String line;
        Car car = new Car();
        boolean found = false;
        while((line = br.readLine()) != null){
            String[] tokens = line.split(",");
            if(tokens[0].equals(id)){
                car.setId(Integer.parseInt(tokens[0]));
                car.setType(tokens[1]);
                car.setColor(tokens[2]);
                car.setSpz(tokens[3]);
                car.setFuel(Float.parseFloat(tokens[4]));
                car.setDescription(tokens[5]);
                car.setFuelCard(Integer.parseInt(tokens[6]));
                found = true;
            }
        }
        if (!found) {
            car.setId(40400404);
        }
        return car;

    }
}
