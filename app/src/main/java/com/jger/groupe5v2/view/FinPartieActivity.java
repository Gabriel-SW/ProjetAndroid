package com.jger.groupe5v2.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.jger.groupe5v2.R;
import com.jger.groupe5v2.controller.ScoreBaseHelper;
import com.jger.groupe5v2.controller.ScoreDao;
import com.jger.groupe5v2.controller.ScoreService;
import com.jger.groupe5v2.model.Score;

public class FinPartieActivity extends AppCompatActivity {
    ScoreService scoreService;
    Score score;
    EditText editUserName;
    String userName;
    Integer actualScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finpartie);
        scoreService = new ScoreService(new ScoreDao(new ScoreBaseHelper(this)));
        editUserName  = (EditText) findViewById(R.id.edit_username);

        Button buttonValider = findViewById(R.id.btn_validate);
        buttonValider.setOnClickListener(view -> getUserName());
        Intent intent = getIntent();
        actualScore = intent.getIntExtra("score", 0);

        TextView afficheScore = findViewById(R.id.text_score);
        afficheScore.setText("Score: " + String.valueOf(actualScore));
    }

    public void getUserName(){
        userName = editUserName.getText().toString();
        score = new Score(userName, actualScore);
        scoreService.storeInDB(score);
        Intent intent = new Intent(this, ScoreBoardActivity.class);
        startActivity(intent);
    }
}
