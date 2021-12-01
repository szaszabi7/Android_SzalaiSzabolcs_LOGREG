package hu.petrik.logreg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LoggedInActivity extends AppCompatActivity {
    private TextView textview_kiir;
    private Button button_kijelentkezes;
    private DBHelper adatbazis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged_in);

        init();

        //TODO: bejelentkezett nevének kiírása

        button_kijelentkezes.setOnClickListener(View -> {
            Intent mainre = new Intent(LoggedInActivity.this, MainActivity.class);
            startActivity(mainre);
            finish();
        });
    }

    private void init() {
        textview_kiir = findViewById(R.id.textview_kiir);
        button_kijelentkezes = findViewById(R.id.button_kijelentkezes);
        adatbazis = new DBHelper(this);
    }
}