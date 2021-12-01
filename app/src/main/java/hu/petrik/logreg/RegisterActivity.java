package hu.petrik.logreg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    private EditText edit_regisztracio_email, edit_regisztracio_nev, edit_regisztracio_jelszo, edit_regisztracio_teljes_nev;
    private Button button_regisztracio_reg, button_vissza;
    private DBHelper adatbazis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        init();
        button_regisztracio_reg.setOnClickListener(View -> {
            String email = edit_regisztracio_email.getText().toString().trim();
            String nev = edit_regisztracio_nev.getText().toString().trim();
            String jelszo = edit_regisztracio_jelszo.getText().toString().trim();
            String teljesnev = edit_regisztracio_teljes_nev.getText().toString().trim();

            if (email.isEmpty() || nev.isEmpty() || jelszo.isEmpty() || teljesnev.isEmpty()) {
                Toast.makeText(getApplicationContext(), "Minden mező kitöltése kötelező", Toast.LENGTH_SHORT).show();
            } else {
                try {
                    if (adatbazis.rogzites(email, nev, jelszo, teljesnev)) {
                        Toast.makeText(getApplicationContext(), "Sikeres rögzítés",
                                Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Sikeretelen rögzítés",
                                Toast.LENGTH_SHORT).show();
                    }
                } catch (NumberFormatException ex) {
                    Toast.makeText(getApplicationContext(), "Az alkoholtartalomnak számnak kell lennie",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        button_vissza.setOnClickListener(View -> {
            Intent mainre = new Intent(RegisterActivity.this, MainActivity.class);
            startActivity(mainre);
            finish();
        });
    }

    private void init() {
        edit_regisztracio_email = findViewById(R.id.edit_regisztracio_email);
        edit_regisztracio_nev = findViewById(R.id.edit_regisztracio_nev);
        edit_regisztracio_jelszo = findViewById(R.id.edit_regisztracio_jelszo);
        edit_regisztracio_teljes_nev = findViewById(R.id.edit_regisztracio_teljes_nev);
        button_regisztracio_reg = findViewById(R.id.button_regisztracio_reg);
        button_vissza = findViewById(R.id.button_vissza);
        adatbazis = new DBHelper(this);
    }
}