package com.example.servicedelautomotor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    public void buttonRegister(View view) {
        Intent intent = new Intent(this, Dashboard.class);
        startActivity(intent);
    }

    public void textLogin(View view) {
        Intent intent = new Intent(this, Dashboard.class);
        startActivity(intent);
    }
}