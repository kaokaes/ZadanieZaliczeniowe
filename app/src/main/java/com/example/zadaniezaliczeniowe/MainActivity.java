package com.example.zadaniezaliczeniowe;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;


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


        btnGenerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generatePassword();
            }
        });

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSummary();
            }
        });
    }

    private void generatePassword(){
        int passwordLenght = Integer.parseInt(etPasswordLength.getText().toString());
        String letters = "abcdefghijklmnopqrstuvwxyz";
        String digits = "0123456789";
        String specialChars = "!@#$%^&*()_+-=";
        Random r = new Random();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < passwordLenght; i++) {
            sb.append(letters.charAt(r.nextInt(letters.length())));

        }
        if (cbUpperLower.isChecked()) {
            int index = r.nextInt(passwordLenght);
            char c = sb.charAt(index);
            sb.setCharAt(index, Character.toUpperCase(c));
        }

        if (cbDigits.isChecked() && passwordLenght > 0) {
            int index = 0;
            char digit = digits.charAt(r.nextInt(digits.length()));
            sb.setCharAt(index, digit);
        }

        if (cbSpecial.isChecked() && passwordLenght > 1) {
            int index = 1;
            char special = specialChars.charAt(r.nextInt(specialChars.length()));
            sb.setCharAt(index, special);
        }

        generatedPassword = sb.toString();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(generatedPassword);
        builder.setPositiveButton("OK", null);
        builder.show();


    }

    private void showSummary(){
        String firstName = etFirstName.getText().toString();
        String lastName = etLastName.getText().toString();
        String position = spinnerPosition.getSelectedItem().toString();

        String message = "Imię: " + firstName + "\n" +"Nazwisko: " + lastName + "\n" + "Stanowisko: " + position + "\n" + "Hasło: " + generatedPassword;

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message);
        builder.setPositiveButton("OK", null);
        builder.show();
    }
}



