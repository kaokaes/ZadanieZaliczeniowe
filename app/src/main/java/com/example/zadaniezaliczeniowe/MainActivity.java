package com.example.zadaniezaliczeniowe;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


public class MainActivity extends AppCompatActivity {
    private EditText etFirstName;
    private EditText etLastName;
    private Spinner spinnerPosition;
    private EditText etPasswordLength;
    private CheckBox cbUpperLower;
    private CheckBox cbDigits;
    private CheckBox cbSpecial;
    private Button btnGenerate;
    private Button btnConfirm;

    private String generatedPassword = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;




        });
        etFirstName = findViewById(R.id.etFirstName);
        etLastName = findViewById(R.id.etLastName);
        spinnerPosition = findViewById(R.id.spinnerPosition);
        etPasswordLength = findViewById(R.id.etPasswordLength);
        cbUpperLower = findViewById(R.id.cbUpperLower);
        cbDigits = findViewById(R.id.cbDigits);
        cbSpecial = findViewById(R.id.cbSpecial);
        btnGenerate = findViewById(R.id.btnGenerate);
        btnConfirm = findViewById(R.id.btnConfirm);

        String[] positions = {"Kierownik", "Starszy programista", "Młodszy programista", "Tester"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, positions);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPosition.setAdapter(adapter);

    }
}