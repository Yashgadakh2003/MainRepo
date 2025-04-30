package com.example.loginapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.*;

public class RegisterActivity extends AppCompatActivity {

    EditText username, password;
    Button register;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        register = findViewById(R.id.register);
        db = new DBHelper(this);

        register.setOnClickListener(v -> {
            String user = username.getText().toString();
            String pass = password.getText().toString();

            if (db.insertData(user, pass)) {
                Toast.makeText(this, "Registered successfully", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Registration failed", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
