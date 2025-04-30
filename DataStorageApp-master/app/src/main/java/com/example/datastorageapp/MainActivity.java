package com.example.datastorageapp;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.*;

public class MainActivity extends AppCompatActivity {

    EditText editTextData;
    Button buttonSave, buttonLoad;
    TextView textViewResult;
    RadioButton radioInternal, radioExternal;

    String fileName = "user_data.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextData = findViewById(R.id.editTextData);
        buttonSave = findViewById(R.id.buttonSave);
        buttonLoad = findViewById(R.id.buttonLoad);
        textViewResult = findViewById(R.id.textViewResult);
        radioInternal = findViewById(R.id.radioInternal);
        radioExternal = findViewById(R.id.radioExternal);

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, 1);

        buttonSave.setOnClickListener(v -> saveData());
        buttonLoad.setOnClickListener(v -> loadData());
    }

    private void saveData() {
        String data = editTextData.getText().toString();
        if (radioInternal.isChecked()) {
            try {
                FileOutputStream fos = openFileOutput(fileName, MODE_PRIVATE);
                fos.write(data.getBytes());
                fos.close();
                Toast.makeText(this, "Data saved to internal storage", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (radioExternal.isChecked()) {
            if (isExternalStorageWritable()) {
                File file = new File(getExternalFilesDir(null), fileName);
                try {
                    FileOutputStream fos = new FileOutputStream(file);
                    fos.write(data.getBytes());
                    fos.close();
                    Toast.makeText(this, "Data saved to external storage", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                Toast.makeText(this, "External storage not writable", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void loadData() {
        StringBuilder data = new StringBuilder();
        if (radioInternal.isChecked()) {
            try {
                FileInputStream fis = openFileInput(fileName);
                BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
                String line;
                while ((line = reader.readLine()) != null) {
                    data.append(line);
                }
                reader.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (radioExternal.isChecked()) {
            File file = new File(getExternalFilesDir(null), fileName);
            try {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String line;
                while ((line = reader.readLine()) != null) {
                    data.append(line);
                }
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        textViewResult.setText(data.toString());
    }

    public boolean isExternalStorageWritable() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }
}
