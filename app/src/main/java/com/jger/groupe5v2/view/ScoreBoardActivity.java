package com.jger.groupe5v2.view;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.jger.groupe5v2.R;
import com.jger.groupe5v2.controller.ScoreBaseHelper;
import com.jger.groupe5v2.controller.ScoreDao;
import com.jger.groupe5v2.controller.ScoreService;

import java.util.HashMap;
import java.util.Map;

public class ScoreBoardActivity extends AppCompatActivity {
    private ScoreService scoreService;
    private String userName;
    private Map<String, Integer> dictScore = new HashMap<String, Integer>();

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        scoreService = new ScoreService(new ScoreDao(new ScoreBaseHelper(this)));
        dictScore = scoreService.getScore();
        setContentView(R.layout.activity_scoreboard);

        // reference the table layout
        LinearLayout linearLayout = (LinearLayout)findViewById(R.id.linearLayout);

        TextView txtvwUserName= new TextView(this);
        TextView txtvwScore= new TextView(this);

        TableLayout stk = (TableLayout) findViewById(R.id.table_main);

        TableRow.LayoutParams tvIzbrParms = new TableRow.LayoutParams((300), TableRow.LayoutParams.MATCH_PARENT);
        tvIzbrParms.setMargins(10, 10, 10, 10);

        TableRow tbrow0 = new TableRow(this);
        TextView tv1 = new TextView(this);
        tv1.setLayoutParams(tvIzbrParms);
        tv1.setText("UserName");
        tv1.setPadding(4, 4, 4, 4);
        tv1.setTextColor(Color.WHITE);
        tbrow0.addView(tv1);
        TextView tv2 = new TextView(this);
        tv2.setLayoutParams(tvIzbrParms);
        tv2.setText("Score");
        tv2.setTextColor(Color.WHITE);
        tv2.setPadding(4, 4, 4, 4);
        tbrow0.addView(tv2);
        stk.addView(tbrow0);

        for (Map.Entry<String, Integer> entry : dictScore.entrySet()) {
            String userName = entry.getKey();
            Integer score = entry.getValue();

            TableRow tbrow = new TableRow(this);

            txtvwUserName.setLayoutParams(tvIzbrParms);
            txtvwUserName.setPadding(4, 4, 4, 4);
            txtvwUserName.setTypeface(null, Typeface.BOLD);
            txtvwUserName.setTextColor(Color.WHITE);
            txtvwUserName.setGravity(Gravity.CENTER);
            txtvwUserName.setText(userName);

            tbrow.addView(txtvwUserName);

            txtvwScore.setLayoutParams(tvIzbrParms);
            txtvwScore.setPadding(4, 4, 4, 4);
            txtvwScore.setTypeface(null, Typeface.BOLD);
            txtvwScore.setTextColor(Color.WHITE);
            txtvwScore.setGravity(Gravity.CENTER);
            txtvwScore.setText(score.toString());

            tbrow.addView(txtvwScore);
            stk.addView(tbrow);
        }

    }
}