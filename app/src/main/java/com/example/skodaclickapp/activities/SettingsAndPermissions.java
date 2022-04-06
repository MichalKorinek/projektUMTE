package com.example.skodaclickapp.activities;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.skodaclickapp.R;

import java.util.Objects;

public class SettingsAndPermissions extends AppCompatActivity {

    private ImageButton settingsImageButton;
    private TextView storagePermissionText;
    private TextView cameraPermissionText;
    private ImageButton grantPermissions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_and_permissions);
        hideTitle();
        setUpListeners();
        checkPermissions();

    }

    @SuppressLint("SetTextI18n")
    private void checkPermissions() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            storagePermissionText.setText("Uložiště: Práva udělena!");
        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            cameraPermissionText.setText("Kamera: Práva udělena!");
        }

    }

    private void setUpListeners() {
        settingsImageButton = findViewById(R.id.settingsImageButton);
        settingsImageButton.setOnClickListener(view -> openMainActivity());
        grantPermissions = findViewById(R.id.grantPermissions);
        grantPermissions.setOnClickListener(view -> grantPermissionsOnClick());
        storagePermissionText = findViewById(R.id.storagePermissionText);
        cameraPermissionText = findViewById(R.id.cameraPermissionText);
    }

    private void grantPermissionsOnClick() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(SettingsAndPermissions.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA}, 0);
        }


    }

    private void openMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


    private void hideTitle() {
        Objects.requireNonNull(getSupportActionBar()).hide();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
}