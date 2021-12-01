package hu.petrik.logreg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText edit_bejelentkezes_nev, edit_bejelentkezes_jelszo;
    private Button button_bejelentkezes, button_regisztracio;
    private DBHelper adatbazis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        button_regisztracio.setOnClickListener(view -> {
            Intent regre = new Intent(MainActivity.this, RegisterActivity.class);
            startActivity(regre);
            finish();
        });

        button_bejelentkezes.setOnClickListener(view -> {
            String nev = edit_bejelentkezes_nev.getText().toString().trim();
            String jelszo = edit_bejelentkezes_jelszo.getText().toString().trim();
            Cursor adat = adatbazis.bejelentkezes(nev, jelszo);

            if (nev.isEmpty() || jelszo.isEmpty()) {
                Toast.makeText(getApplicationContext(), "Minden mező kitöltése kötelező", Toast.LENGTH_SHORT).show();
            } else {
                if (adat.getCount() == 0) {
                    Toast.makeText(getApplicationContext(), "Rossz név vagy jelszó", Toast.LENGTH_SHORT).show();
                } else {
                    while (adat.moveToNext()) {
                        Toast.makeText(getApplicationContext(), "Sikeres bejelentkezés", Toast.LENGTH_SHORT).show();
                    }
                    Intent login = new Intent(MainActivity.this, LoggedInActivity.class);
                    login.putExtra("nev", nev);
                    startActivity(login);
                    finish();
                }
            }
        });
    }

    private void init() {
        edit_bejelentkezes_nev = findViewById(R.id.edit_bejelentkezes_nev);
        edit_bejelentkezes_jelszo = findViewById(R.id.edit_bejelentkezes_jelszo);
        button_bejelentkezes = findViewById(R.id.button_bejelentkezes);
        button_regisztracio = findViewById(R.id.button_regisztracio);
        adatbazis = new DBHelper(this);
    }
}