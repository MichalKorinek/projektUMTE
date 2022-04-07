package com.example.skodaclickapp.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.skodaclickapp.R;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideTitle();
        setContentView(R.layout.activity_main);

        ImageButton listCars = findViewById(R.id.listCars);
        listCars.setOnClickListener(view -> openListCarsActivity());

        ImageButton permissions = findViewById(R.id.permissions);
        permissions.setOnClickListener(view -> openSettings());

        ImageButton scanQR = findViewById(R.id.scanQR);
        scanQR.setOnClickListener(view -> openQrReader());

    }

    private void openQrReader() {
        if(checkCameraPermission()){
            Intent intent = new Intent(this, CameraReader.class);
            startActivity(intent);
        } else {
            Intent intent = new Intent(this, SettingsAndPermissions.class);
            startActivity(intent);
        }

    }

    private void openSettings() {
        Intent intent = new Intent(this, SettingsAndPermissions.class);
        startActivity(intent);
    }

    private void openListCarsActivity() {
        if(checkStoragePermission()){
            Intent intent = new Intent(this, ListCars.class);
            startActivity(intent);
        } else {
            Intent intent = new Intent(this, SettingsAndPermissions.class);
            startActivity(intent);
        }
        

    }

    private boolean checkStoragePermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else return false;
    }

    private boolean checkCameraPermission(){
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else return false;
    }


    private void hideTitle(){
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }





}