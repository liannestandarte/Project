package com.example.project;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText email, password;
    String email_input, name;
    sqlite db;
    Cursor res;
    int counter = 0;
    SharedPreferences pref;
    String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        pref = getSharedPreferences("AppPref", MODE_PRIVATE);
        token = pref.getString("token", "");

        if(token.toString().isEmpty()) {

        }else{
            startActivity(new Intent(LoginActivity.this, MenuActivity.class));
            finish();
        }

        db = new sqlite(this);

        res = db.getAllData();

        final TextView signup = (TextView) findViewById(R.id.linkSignup);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });

        Button login = (Button) findViewById(R.id.btnLogin);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                res = db.getAllData();
                if(email.getText().toString().isEmpty() || password.getText().toString().isEmpty()){
                    if(email.getText().toString().isEmpty()){
                        email.setError("Enter Username!");
                    }
                    if(password.getText().toString().isEmpty()){
                        password.setError("Enter Password!");
                    }
                }else {
                    while (res.moveToNext()) {
                        if (email.getText().toString().equals(res.getString(res.getColumnIndex("email"))) && password.getText().toString().equals(res.getString(res.getColumnIndex("password")))) {
                            name = res.getString(res.getColumnIndex("name"));
                            email_input = email.getText().toString();
                            SharedPreferences.Editor edit = pref.edit();
                            edit.putString("token", email_input);
                            edit.putString("email", email_input);
                            edit.putString("name", name);
                            edit.commit();
                            startActivity(new Intent(LoginActivity.this, MenuActivity.class));
                            counter = 1;
                        }
                    }

                    if(counter == 0) {
                        Toast.makeText(LoginActivity.this, "Incorrect Email/Password", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
    @Override
    public void onBackPressed() {

    }
}
