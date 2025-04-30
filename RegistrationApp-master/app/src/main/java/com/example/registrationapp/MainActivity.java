package com.example.registrationapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class MainActivity extends AppCompatActivity {

    EditText nameInput;
    Spinner subjectSpinner;
    RadioGroup genderGroup;
    CheckBox cbUG, cbPG, cbPhD;
    Button submitBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameInput = findViewById(R.id.editTextName);
        subjectSpinner = findViewById(R.id.spinnerSubject);
        genderGroup = findViewById(R.id.radioGroupGender);
        cbUG = findViewById(R.id.checkBoxUG);
        cbPG = findViewById(R.id.checkBoxPG);
        cbPhD = findViewById(R.id.checkBoxPhD);
        submitBtn = findViewById(R.id.buttonSubmit);

        // Spinner options
        String[] subjects = {"Math", "Science", "History", "Computer Science"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, subjects);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        subjectSpinner.setAdapter(adapter);

        submitBtn.setOnClickListener(v -> {
            String name = nameInput.getText().toString();
            String subject = subjectSpinner.getSelectedItem().toString();

            int selectedGenderId = genderGroup.getCheckedRadioButtonId();
            RadioButton selectedGender = findViewById(selectedGenderId);
            String gender = selectedGender != null ? selectedGender.getText().toString() : "Not selected";

            StringBuilder qualification = new StringBuilder();
            if (cbUG.isChecked()) qualification.append("UG ");
            if (cbPG.isChecked()) qualification.append("PG ");
            if (cbPhD.isChecked()) qualification.append("PhD ");

            Intent intent = new Intent(MainActivity.this, ShowActivity.class);
            intent.putExtra("name", name);
            intent.putExtra("subject", subject);
            intent.putExtra("gender", gender);
            intent.putExtra("qualification", qualification.toString().trim());

            startActivity(intent);
        });
    }
}
