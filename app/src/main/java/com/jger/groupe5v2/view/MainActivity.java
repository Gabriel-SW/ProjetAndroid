package com.jger.groupe5v2.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.jger.groupe5v2.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button boutonCommencer = findViewById(R.id.button_Start);
        Button boutonScore = findViewById(R.id.button_Scoreboard);
        boutonCommencer.setOnClickListener(view -> lancerActivityCalcul());
        boutonScore.setOnClickListener(view -> lancerActivityScore());

    }

    private void lancerActivityScore() {
        Intent intent = new Intent(this, ScoreBoardActivity.class);
        startActivity(intent);
    }

    private void lancerActivityCalcul() {
        Intent intent = new Intent(this, CalculActivity.class);
        startActivity(intent);
    }
}