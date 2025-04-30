package com.example.registrationapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ShowActivity extends AppCompatActivity {

    TextView textViewData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        textViewData = findViewById(R.id.textViewData);

        String name = getIntent().getStringExtra("name");
        String subject = getIntent().getStringExtra("subject");
        String gender = getIntent().getStringExtra("gender");
        String qualification = getIntent().getStringExtra("qualification");

        String display = "Name: " + name + "\nSubject: " + subject + "\nGender: " + gender + "\nQualification: " + qualification;
        textViewData.setText(display);
    }
}
