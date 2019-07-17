package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class AccommodationActivity extends AppCompatActivity {

    SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accommodation);

        pref = getSharedPreferences("AppPref", MODE_PRIVATE);

        final ImageButton deluxe = (ImageButton) findViewById(R.id.deluxeimgbtn);
        final ImageButton one = (ImageButton) findViewById(R.id.onesuiteimgbtn);
        final ImageButton two = (ImageButton) findViewById(R.id.twosuiteimgbtn);
        final ImageButton three = (ImageButton) findViewById(R.id.threesuiteimgbtn);
        Button back = (Button) findViewById(R.id.btnBack);
        Button logout = (Button) findViewById(R.id.btnLogout);

        deluxe.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast toast = Toast.makeText(getApplicationContext(), "Deluxe Room reserved.", Toast.LENGTH_SHORT);
                toast.show();
                toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
            }
        });

        one.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast toast = Toast.makeText(getApplicationContext(), "One Bedroom Suite reserved.", Toast.LENGTH_SHORT);
                toast.show();
                toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
            }
        });

        two.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast toast = Toast.makeText(getApplicationContext(), "Two Bedroom Suite reserved.", Toast.LENGTH_SHORT);
                toast.show();
                toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
            }
        });

        three.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast toast = Toast.makeText(getApplicationContext(), "Three Bedroom Suite reserved.", Toast.LENGTH_SHORT);
                toast.show();
                toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AccommodationActivity.this, MenuActivity.class));
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor edit = pref.edit();
                edit.clear();
                edit.commit();
                startActivity(new Intent(AccommodationActivity.this, LoginActivity.class));
            }
        });
    }
}