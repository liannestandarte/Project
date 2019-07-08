package com.example.project;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {

    TextView name, email;
    SharedPreferences pref;
    String name_txt, email_txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        pref = getSharedPreferences("AppPref", MODE_PRIVATE);
        name_txt = pref.getString("name", "");
        email_txt = pref.getString("email", "");

        Button logout = (Button) findViewById(R.id.btnLogout);
        Button back = (Button) findViewById(R.id.btnBack);
        name = (TextView) findViewById(R.id.name);
        email = (TextView) findViewById(R.id.email);

        name.setText(name_txt);
        email.setText(email_txt);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProfileActivity.this, MenuActivity.class));
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor edit = pref.edit();
                edit.clear();
                edit.commit();
                startActivity(new Intent(ProfileActivity.this, LoginActivity.class));
            }
        });
    }
}