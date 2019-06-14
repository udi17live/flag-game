package com.example.flaggame;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    View view;
    Switch timer;
    private CardView guessHints, guessCountry, guessFlag, advancedMode, about;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timer = (Switch) findViewById(R.id.sw_timer);
        view = this.getWindow().getDecorView();

        guessHints = findViewById(R.id.guessHints);
        guessCountry = findViewById(R.id.guessCountry);
        guessFlag = findViewById(R.id.guessFlag);
        advancedMode = findViewById(R.id.advancedMode);
        about = findViewById(R.id.about);


        timer.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked == true){
                    view.setBackgroundResource(R.color.colorBgTimer);
                    Toast.makeText(MainActivity.this, "Timer is on!", Toast.LENGTH_LONG).show();

                }else{
                    Toast.makeText(MainActivity.this, "Timer is off!", Toast.LENGTH_LONG).show();
                }
            }
        });

        guessHints.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGuessHints();
            }
        });

        guessCountry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGuessCountry();
            }
        });

        guessFlag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (timer.isChecked()){
                    openGuessFlagsTimer();
                }else{
                    openGuessFlags();
                }
            }
        });

        advancedMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAdvancedMMode();
            }
        });

        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAbout();
            }
        });

        
    }


    public void openGuessCountry(){
        Intent intent = new Intent(this, com.example.flaggame.guessCountry.class);
        startActivity(intent);
    }

    public void openGuessHints(){
        Intent intent = new Intent(this, com.example.flaggame.guessHint.class);
        startActivity(intent);
    }

    public void openGuessFlags(){
        Intent intent = new Intent(this, com.example.flaggame.GuessFlag.class);
        startActivity(intent);
    }

    public void openAdvancedMMode(){
        Intent intent = new Intent(this, com.example.flaggame.AdvanceMode.class);
        startActivity(intent);
    }
    public void openAbout(){
        Intent intent = new Intent(this, com.example.flaggame.About.class);
        startActivity(intent);
    }

    // With Timer Functionality
    public void openGuessFlagsTimer(){
        Intent intent = new Intent(this, com.example.flaggame.GuessFlag.class);
        startActivity(intent);
    }
}
