package com.jger.groupe5v2.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.jger.groupe5v2.controller.ScoreBaseHelper;
import com.jger.groupe5v2.controller.ScoreDao;
import com.jger.groupe5v2.controller.ScoreService;
import com.jger.groupe5v2.model.exception.DivideException;
import com.jger.groupe5v2.R;
import com.jger.groupe5v2.model.TypeOperationEnum;

import java.util.Random;

public class CalculActivity extends AppCompatActivity {
    Integer premierElement = 0;
    Integer deuxiemeElement = 0;
    Integer resultat = 0;
    String stringResultat = "";
    TypeOperationEnum typeOperation = null;
    TextView textViewCalculAffichage;
    TextView textViewCalculResultat;
    TextView textViewResultat;
    MenuItem textViewLife;
    MenuItem textViewScore;
    Integer BORNE_HAUTE = 9999;
    ScoreService scoreService;
    Integer MaxRandom = 99;
    Integer MinRandom = 1;
    Integer LifeNumber = 3;
    Integer score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calcul);
        scoreService = new ScoreService(new ScoreDao(new ScoreBaseHelper(this)));
        textViewCalculAffichage = findViewById(R.id.textviewCalculAffichage);
        textViewCalculResultat = findViewById(R.id.textviewCalculResultat);
        textViewResultat = findViewById(R.id.textViewResultat);

        Button button1 = findViewById(R.id.button_1);
        button1.setOnClickListener(view -> ajouterNombre(1));
        Button button2 = findViewById(R.id.button_2);
        button2.setOnClickListener(view -> ajouterNombre(2));
        Button button3 = findViewById(R.id.button_3);
        button3.setOnClickListener(view -> ajouterNombre(3));
        Button button4 = findViewById(R.id.button_4);
        button4.setOnClickListener(view -> ajouterNombre(4));
        Button button5 = findViewById(R.id.button_5);
        button5.setOnClickListener(view -> ajouterNombre(5));
        Button button6 = findViewById(R.id.button_6);
        button6.setOnClickListener(view -> ajouterNombre(6));
        Button button7 = findViewById(R.id.button_7);
        button7.setOnClickListener(view -> ajouterNombre(7));
        Button button8 = findViewById(R.id.button_8);
        button8.setOnClickListener(view -> ajouterNombre(8));
        Button button9 = findViewById(R.id.button_9);
        button9.setOnClickListener(view -> ajouterNombre(9));
        Button button0 = findViewById(R.id.button_0);
        button0.setOnClickListener(view -> ajouterNombre(0));
        Button buttonValider = findViewById(R.id.boutton_valider);
        buttonValider.setOnClickListener(view -> validerCalcul());
        Button buttonEffacer = findViewById(R.id.boutton_effacer);
        buttonEffacer.setOnClickListener(view -> videTextViewResultat());

        majTextAffichage();
    }

    private void GenerateRandomNumber(){
         premierElement = new Random().nextInt((MaxRandom - MinRandom) + 1) + MinRandom;
         if(typeOperation == TypeOperationEnum.DIVIDE || typeOperation == TypeOperationEnum.SUBSTRACT) {
             deuxiemeElement = new Random().nextInt((premierElement > 0 ? premierElement - 1 : 1) - MinRandom) + MinRandom;
         }else {
             deuxiemeElement = new Random().nextInt((MaxRandom - MinRandom) + 1) + MinRandom;
         }
    }

    private void ajouterSymbol() {
        Integer randSymbol = new Random().nextInt(4) + 1;
        switch (randSymbol){
            case 1:
                this.typeOperation = TypeOperationEnum.ADD;
                break;
            case 2:
                this.typeOperation = TypeOperationEnum.SUBSTRACT;
                break;
            case 3:
                this.typeOperation = TypeOperationEnum.MULTIPLY;
                break;
            case 4:
                this.typeOperation = TypeOperationEnum.DIVIDE;
                break;
        }
    }

    public void ajouterNombre(Integer valeur) {
        if (Integer.parseInt(stringResultat + valeur.toString()) > BORNE_HAUTE) {
            Toast.makeText(this, getString(R.string.ERROR_VALEUR_TROP_GRANDE), Toast.LENGTH_LONG).show();
        } else {
            stringResultat += valeur.toString();
        }

        resultat = Integer.parseInt(stringResultat);
        majTextResultat();
    }

    private void majTextResultat() {
        String textAAfficher = resultat.toString();
        textViewCalculResultat.setText(textAAfficher);
    }

    private void majTextAffichage() {
        ajouterSymbol();
        GenerateRandomNumber();
        String textAAfficher = premierElement + " " + typeOperation.getSymbol() + " " + deuxiemeElement;
        textViewCalculAffichage.setText(textAAfficher);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar, menu);

        textViewLife = menu.findItem(R.id.toolbarVies);
        textViewScore = menu.findItem(R.id.toolbarScore);
        updateLife();
        textViewScore.setTitle(String.format("%s %s",getResources().getString(R.string.toolbar_score), String.valueOf(score)));
        return super.onPrepareOptionsMenu(menu);
    }

    private boolean updateLife(){
        System.out.println(textViewLife);

        textViewLife.setTitle(String.format("%s %s",getResources().getString(R.string.toolbar_vies), String.valueOf(LifeNumber)));
        return true;
    }
    private boolean updateScore(){
        score ++;
        textViewScore.setTitle(String.format("%s %s",getResources().getString(R.string.toolbar_score), String.valueOf(score)));
        return true;
    }

    private boolean videTextViewResultat() {
        textViewCalculResultat.setText("");
        stringResultat = "";
        resultat = 0;
        return true;
    }


    private Integer  faisLeCalcul() throws DivideException {
            switch (typeOperation) {
                case ADD:
                    return premierElement + deuxiemeElement;
                case DIVIDE:
                    if (deuxiemeElement != 0) {
                        return premierElement / deuxiemeElement;
                    } else {
                        throw new DivideException();
                    }

                case SUBSTRACT:
                    return premierElement - deuxiemeElement;
                case MULTIPLY:
                    return premierElement * deuxiemeElement;
            }
            return 0;
    }

    private void goToFinPartie() {
        Intent intent = new Intent(this, FinPartieActivity.class);
        intent.putExtra("score",score);
        startActivity(intent);
    }

    public boolean validerCalcul(){
        boolean valid = false;
        boolean gameOver = false;

        try{
            if (resultat == faisLeCalcul()){
                updateScore();
                Toast.makeText(this,getString(R.string.SUCCESS_CALCUL),Toast.LENGTH_LONG).show();
                valid =  true;

            }else{
                Toast.makeText(this,getString(R.string.ERROR_CALCUL),Toast.LENGTH_LONG).show();
                LifeNumber--;
                if(LifeNumber > 0){
                    updateLife();
                }else{
                    Toast.makeText(this,getString(R.string.ERROR_GAME_OVER),Toast.LENGTH_LONG).show();
                    gameOver = true;
                }
            }
        }catch (DivideException e){
            Toast.makeText(this,getString(R.string.ERROR_DIVISION_ZERO),Toast.LENGTH_LONG).show();
            LifeNumber--;
            if(LifeNumber > 0){
                updateLife();
            }else{
                Toast.makeText(this,getString(R.string.ERROR_GAME_OVER),Toast.LENGTH_LONG).show();
                gameOver = true;
            }
        }
        videTextViewResultat();
        majTextAffichage();


        if(gameOver){
            goToFinPartie();
        }
        return valid;
    }


}