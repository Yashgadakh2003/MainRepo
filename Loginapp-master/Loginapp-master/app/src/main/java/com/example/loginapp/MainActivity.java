package com.example.loginapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText username, password;
    Button login, register;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);
        register = findViewById(R.id.register);
        db = new DBHelper(this);

        login.setOnClickListener(v -> {
            String user = username.getText().toString();
            String pass = password.getText().toString();
            if (db.checkUser(user, pass)) {
                Toast.makeText(this, "Login successful!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Invalid credentials", Toast.LENGTH_SHORT).show();
            }
        });

        register.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
            startActivity(intent);
        });
    }
}
