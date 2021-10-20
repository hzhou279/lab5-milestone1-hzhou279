package com.example.lab5_milestone1_hzhou279;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class AfterLogin extends AppCompatActivity {

    TextView welcomeUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_login);

        welcomeUser = (TextView) findViewById(R.id.textWelcomeUser);
        Intent intent = getIntent();
        String userName = intent.getStringExtra("message");
        welcomeUser.setText("Welcome " + userName + "!");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout:
                goToMainActivity();
                break;
            case R.id.addNote:
                break;
        }
        return true;
    }

    public void goToMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        SharedPreferences sharedPreferences = getSharedPreferences("com.example.lab5_milestone1_hzhou279", Context.MODE_PRIVATE);
        sharedPreferences.edit().putString("username", "").apply();
        startActivity(intent);
    }
}