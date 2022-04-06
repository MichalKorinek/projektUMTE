package com.example.skodaclickapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.Switch;

import com.example.skodaclickapp.R;

import java.util.Objects;

public class SettingsAndPermissions extends AppCompatActivity {

    private ImageButton settingsImageButton;
    private Switch storagePermissionSwitch;
    private Switch cameraPermissionSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_and_permissions);
        hideTitle();
        setUpListeners();

    }

    private void setUpListeners() {
        settingsImageButton = findViewById(R.id.settingsImageButton);
        settingsImageButton.setOnClickListener(view -> openMainActivity());

        storagePermissionSwitch = findViewById(R.id.storagePermissionSwitch);
        cameraPermissionSwitch = findViewById(R.id.cameraPermissionSwitch);
    }

    private void openMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }



    private void hideTitle(){
        Objects.requireNonNull(getSupportActionBar()).hide();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
}