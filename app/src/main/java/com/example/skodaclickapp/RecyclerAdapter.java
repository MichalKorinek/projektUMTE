package com.example.skodaclickapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.skodaclickapp.activities.MainActivity;
import com.example.skodaclickapp.listeners.DetailClickListener;
import com.example.skodaclickapp.model.Car;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    private List<Car> cars;
    private DetailClickListener detailClickListener;

    public RecyclerAdapter(List<Car> cars, DetailClickListener detailClickListener) {
        this.cars = cars;
        this.detailClickListener = detailClickListener;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView carId;
        private TextView carSpz;
        private TextView typeOfCar;
        private TextView fuelOfCar;
        private ImageView imageView;
        private ImageButton imageButton;

        public MyViewHolder(final View view) {
            super(view);
            carId = view.findViewById(R.id.carId);
            carSpz = view.findViewById(R.id.carSpz);
            typeOfCar = view.findViewById(R.id.typeOfCar);
            fuelOfCar = view.findViewById(R.id.fuelOfCar);
            imageView = view.findViewById(R.id.imageView3);
            imageButton = view.findViewById(R.id.moreInfoBtn);

        }


    }


    @NonNull
    @Override
    public RecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_items, parent, false);
        return new MyViewHolder(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.MyViewHolder holder, int position) {
        holder.carId.setText("" + cars.get(position).getId());
        holder.carSpz.setText(cars.get(position).getSpz());
        holder.typeOfCar.setText(cars.get(position).getType());
        holder.fuelOfCar.setText((cars.get(position).getFuel() * 100) + "%");
        if(cars.get(position).getFuel() < 0.35){
            holder.fuelOfCar.setTextColor(Color.rgb(255,0,0));
        } else if(cars.get(position).getFuel() < 0.75){
            holder.fuelOfCar.setTextColor(Color.rgb(255,140,0));
        } else {
            holder.fuelOfCar.setTextColor(Color.rgb(61,182,66));
        }

        String color = cars.get(position).getColor();
        switch (cars.get(position).getType()) {
            case "Scala":
                if (color.equals("red")) {
                    holder.imageView.setImageResource(R.drawable.scala_red);
                } else if (color.equals("blue")) {
                    holder.imageView.setImageResource(R.drawable.scala_blue);
                } else {
                    holder.imageView.setImageResource(R.drawable.scala_white);
                }
                break;
            case "Octavia":
                if (color.equals("blue")) {
                    holder.imageView.setImageResource(R.drawable.octavia_blue);
                } else if (color.equals("green")) {
                    holder.imageView.setImageResource(R.drawable.octavia_green);
                } else if (color.equals("grey")) {
                    holder.imageView.setImageResource(R.drawable.octavia_grey);
                } else {
                    holder.imageView.setImageResource(R.drawable.octavia_white);
                }
                break;
            case "KamiQ":
                if (color.equals("black")) {
                    holder.imageView.setImageResource(R.drawable.kamiq_black);
                } else {
                    holder.imageView.setImageResource(R.drawable.kamiq_red);
                }
                break;
            default:
                holder.imageView.setImageResource(R.drawable.scala_white);

        }

        holder.imageButton.setOnClickListener(view -> detailClickListener.onDetailClick(cars.get(position).getId()));

    }



    @Override
    public int getItemCount() {
        return cars.size();
    }
}
