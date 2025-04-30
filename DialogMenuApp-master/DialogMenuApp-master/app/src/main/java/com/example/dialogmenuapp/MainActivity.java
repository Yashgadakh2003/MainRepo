package com.example.dialogmenuapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.*;
import android.widget.*;

public class MainActivity extends AppCompatActivity {

    Button btnAlertDialog, btnCustomDialog, btnPopupMenu;
    TextView textViewContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAlertDialog = findViewById(R.id.btnAlertDialog);
        btnCustomDialog = findViewById(R.id.btnCustomDialog);
        btnPopupMenu = findViewById(R.id.btnPopupMenu);
        textViewContext = findViewById(R.id.textViewContext);

        registerForContextMenu(textViewContext); // For context menu

        btnAlertDialog.setOnClickListener(v -> showAlertDialog());
        btnCustomDialog.setOnClickListener(v -> showCustomDialog());
        btnPopupMenu.setOnClickListener(v -> showPopupMenu(v));
    }

    private void showAlertDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Alert Dialog")
                .setMessage("This is a simple alert dialog.")
                .setPositiveButton("OK", null)
                .setNegativeButton("Cancel", null)
                .show();
    }

    private void showCustomDialog() {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_custom);
        Button closeBtn = dialog.findViewById(R.id.closeButton);
        closeBtn.setOnClickListener(v -> dialog.dismiss());
        dialog.show();
    }

    private void showPopupMenu(View v) {
        PopupMenu popup = new PopupMenu(this, v);
        popup.getMenuInflater().inflate(R.menu.popup_menu, popup.getMenu());
        popup.setOnMenuItemClickListener(item -> {
            Toast.makeText(this, item.getTitle() + " clicked", Toast.LENGTH_SHORT).show();
            return true;
        });
        popup.show();
    }

    // Context Menu
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        Toast.makeText(this, item.getTitle() + " selected", Toast.LENGTH_SHORT).show();
        return super.onContextItemSelected(item);
    }

    // Option Menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Toast.makeText(this, item.getTitle() + " selected from option menu", Toast.LENGTH_SHORT).show();
        return true;
    }
}
