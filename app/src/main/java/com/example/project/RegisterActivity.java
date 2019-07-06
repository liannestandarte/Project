package com.example.project;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    sqlite db;
    Cursor res;
    EditText name, email, password;
    int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        name = (EditText) findViewById(R.id.name);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);

        db = new sqlite(this);

        res = db.getAllData();

        final TextView login = (TextView) findViewById(R.id.linkLogin);
        Button create = (Button) findViewById(R.id.btnCreate);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                res = db.getAllData();

                if (name.getText().toString().isEmpty() || email.getText().toString().isEmpty() || password.getText().toString().isEmpty()){
                    if(name.getText().toString().isEmpty()){
                        name.setError("Enter Name");
                    }
                    if(email.getText().toString().isEmpty()){
                        email.setError("Enter Email");
                    }
                    if(password.getText().toString().isEmpty()){
                        password.setError("Enter Password");
                    }
                } else {
                    while (res.moveToNext()) {
                        if (name.getText().toString().equals(res.getString(res.getColumnIndex("name"))) || email.getText().toString().equals(res.getString(res.getColumnIndex("email")))) {
                            counter = 1;
                        }
                    }
                    if (counter == 1) {
                        Toast.makeText(RegisterActivity.this, "Name/Email already exist", Toast.LENGTH_SHORT).show();
                        counter = 0;
                    } else {
                        insertToSqlite(name.getText().toString(), email.getText().toString(), password.getText().toString());
                        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                    }
                }
            }
        });
    }
    public void insertToSqlite(String name, String email, String password) {
        db.insertData(name, email, password);
    }
}
