package com.example.lab5_milestone1_hzhou279;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sharedPreferences = getSharedPreferences("com.example.lab5_milestone1_hzhou279", Context.MODE_PRIVATE);
        if (!sharedPreferences.getString("username", "").equals("")) {
            String username = sharedPreferences.getString("username", "");
            goToAfterLogin(username);
        } else {
            setContentView(R.layout.activity_main);
        }
    }

    public void login(View view) {
        EditText myTextField = (EditText) findViewById(R.id.userName);
        String userName = myTextField.getText().toString();
        SharedPreferences sharedPreferences = getSharedPreferences("com.example.lab5_milestone1_hzhou279", Context.MODE_PRIVATE);
        sharedPreferences.edit().putString("username", userName).apply();
        goToAfterLogin(userName);
    }

    public void goToAfterLogin(String userName) {
        Intent intent = new Intent(this, AfterLogin.class);
        intent.putExtra("message", userName);
        startActivity(intent);
    }

}