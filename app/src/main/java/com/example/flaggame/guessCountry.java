package com.example.flaggame;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class guessCountry extends AppCompatActivity {

    ImageView iv_CountryFlags;
    Spinner sp_country;
    Button btn_submit;

    List<Country> countryList = new ArrayList<>();
    ArrayAdapter<CharSequence> adapter;

    CountryDatabase countryDB = new CountryDatabase();
    Country countryItem = null;
    String correctAnswer="";
    String changeText;

    Random random;
    int turn = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guess_country);

        random = new Random();



        //Connecting Variables to its view counterparts
        iv_CountryFlags = (ImageView) findViewById(R.id.iv_CountryFlags);
        sp_country = (Spinner) findViewById(R.id.sp_country);
        btn_submit = (Button) findViewById(R.id.btn_submit);

        //Adding Items to Spinner Widget
        adapter = ArrayAdapter.createFromResource(this,R.array.country_list,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_country.setAdapter(adapter);

        // Adding Names,Codes and Images to a List
        for(int i=0; i< countryDB.countryCodes.length;i++){
            countryItem = new Country(countryDB.countryCodes[i],countryDB.countryNames[i],countryDB.flagImg[i]);
            countryList.add(i,countryItem);
        }

        //Shuffle to Randomize
        Collections.shuffle(countryList);


        newFlag(turn);


        //Submitting the answer
       btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sp_country.getSelectedItem().toString().equalsIgnoreCase(countryList.get(turn-1).getName())){
                    correctAnswerPopup();
                    changeText = "NEXT";
                    btn_submit.setText(changeText);

                    if (turn < countryList.size()){
                        turn++;
                        newFlag(turn);
                        changeText = "SUBMIT";
                        btn_submit.setText(changeText);

                    }

                }else{
                    correctAnswer = countryList.get(turn-1).getName();

                    wrongAnswerPopup();
                    changeText = "NEXT";
                    btn_submit.setText(changeText);

                    if (turn < countryList.size()){
                        turn++;
                        newFlag(turn);
                        changeText = "SUBMIT";
                        btn_submit.setText(changeText);

                    }


                }
            }
        });

    }

    public void newFlag(int num){
        iv_CountryFlags.setImageResource(countryList.get(num - 1).getImage());
    }

    public void correctAnswerPopup(){
        Intent intent = new Intent(this, com.example.flaggame.correct.class);
        startActivity(intent);
    }

    public void wrongAnswerPopup(){
        Intent intent = new Intent(this, com.example.flaggame.wrong.class);
        intent.putExtra("CorrectAnswer", correctAnswer);
        startActivity(intent);
    }



}
