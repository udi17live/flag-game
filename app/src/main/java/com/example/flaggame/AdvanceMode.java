package com.example.flaggame;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.annotation.ColorInt;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class AdvanceMode extends AppCompatActivity implements View.OnClickListener {
    
    //Import the Elements.
    Button btn_submit;
    ImageView iv_flag1, iv_flag2, iv_flag3;
    EditText et_flag1, et_flag2, et_flag3;
    TextView tv_score;

    List<Country> countryList = new ArrayList<>();

    CountryDatabase countryDB = new CountryDatabase();
    Country countryItem = null;
    int corr=0;
    int i=0;
    int attempt1 = 0;
    int attempt2 = 0;
    int attempt3 = 0;
    int correctCount = 0;
    String score;

    String ans1,ans2,ans3;
    int cFlag1, cFlag2, cFlag3 = 0;
    String[] ansArray = new String[3];
    int[] imageArray = new int[3];

    Random random;
    int turn = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advance_mode);

        random = new Random();

        //Connecting variables to elements in the view.
        iv_flag1 = (ImageView) findViewById(R.id.iv_flag1);
        iv_flag2 = (ImageView) findViewById(R.id.iv_flag2);
        iv_flag3 = (ImageView) findViewById(R.id.iv_flag3);

        et_flag1 = (EditText) findViewById(R.id.et_flag1);
        et_flag2 = (EditText) findViewById(R.id.et_flag2);
        et_flag3 = (EditText) findViewById(R.id.et_flag3);

        tv_score = (TextView) findViewById(R.id.tv_score);

        btn_submit = (Button) findViewById(R.id.btn_submit);

        // Adding Names,Codes and Images to a List
        for (int i = 0; i < countryDB.countryCodes.length; i++) {
            countryItem = new Country(countryDB.countryCodes[i], countryDB.countryNames[i], countryDB.flagImg[i]);
            countryList.add(i, countryItem);
        }

        //Shuffle to Randomize the List of Countries
        Collections.shuffle(countryList);

        //Generating First set of Random Flags
        newAdvancedCountry(turn);
        //resetColors();

        btn_submit.setOnClickListener(this);

    }

    //Click Events
    public void onClick (View v){
        if (i==0){
            clickEvent();
            //setting score
            attempt1 = correctCount;
            corr += attempt1;
            score = String.valueOf(corr);
            tv_score.setText(score);

            if (correctCount == 3){
                correctAnswerPopup();
                correctCount = 0;
                if (turn < countryList.size()){
                    turn++;
                    newAdvancedCountry(turn);
                }
                i =0;
            }

            i++;
        }else if (i==1){
            clickEvent();
            //setting score
            attempt2 = correctCount - attempt1;
            corr += attempt2;
            score = String.valueOf(corr);
            tv_score.setText(score);

            if (correctCount == 3){
                correctAnswerPopup();
                correctCount = 0;
                if (turn < countryList.size()){
                    turn++;
                    newAdvancedCountry(turn);
                }
                i = 0;
            }

            i++;
        }else if (i==2){
            clickEvent();
            //setting score
            attempt3 = correctCount - (attempt1 + attempt2);
            corr += attempt3;
            score = String.valueOf(corr);
            tv_score.setText(score);

            if (correctCount == 3){
                correctAnswerPopup();
                correctCount = 0;
                if (turn < countryList.size()){
                    turn++;
                    newAdvancedCountry(turn);
                }
                i = 0;
            }
            i++;
        }

        if (i == 3){
            if (correctCount == 3){
                correctAnswerPopup();
                correctCount = 0;

                if (turn < countryList.size()){
                    turn++;
                    //resetColors();
                    newAdvancedCountry(turn);

                }
            }else{
                wrongAnswerPopup();
                correctCount = 0;
                if (turn < countryList.size()){
                    turn++;
                    //resetColors();
                    newAdvancedCountry(turn);
                }

            }
            i=0;
        }
    }


    //This code executes when the submit button is clicked
    public void clickEvent(){
        correctCount = 0;
        if (et_flag1.getText().toString().equalsIgnoreCase(ans1)){
            et_flag1.setEnabled(false);
            et_flag1.setTextColor(Color.GREEN);
            correctCount++;
        } else{
            et_flag1.setTextColor(Color.RED);
            et_flag1.setEnabled(true);
        }
        if (et_flag2.getText().toString().equalsIgnoreCase(ans2)){
            et_flag2.setEnabled(false);
            et_flag2.setTextColor(Color.GREEN);
            correctCount++;
        } else{
            et_flag2.setTextColor(Color.RED);
            et_flag2.setEnabled(true);
        }
        if (et_flag3.getText().toString().equalsIgnoreCase(ans3)){
            et_flag3.setEnabled(false);
            et_flag3.setTextColor(Color.GREEN);
            correctCount++;
        } else{
            et_flag3.setTextColor(Color.RED);
            et_flag3.setEnabled(true);
        }



    }

    //reset all the elements to its initial state
    public void resetColors(){
        et_flag1.setText("");
        et_flag1.setEnabled(true);
        et_flag1.setTextColor(Color.WHITE);

        et_flag2.setText("");
        et_flag2.setEnabled(true);
        et_flag2.setTextColor(Color.WHITE);

        et_flag3.setText("");
        et_flag3.setEnabled(true);
        et_flag3.setTextColor(Color.WHITE);
    }


    //generating new items
    public void newAdvancedCountry(int num){
        resetColors();
        //setting random choices
        int correctAns = random.nextInt(3) + 1;

        int choice1 = num - 1;
        int choice2;
        int choice3;

        switch(correctAns){
            case 1:
                ansArray = new String[3];
                imageArray = new int[3];

                iv_flag1.setImageResource(countryList.get(choice1).getImage());
                ans1 = countryList.get(choice1).getName();

                do {
                    choice2 = random.nextInt(countryList.size());
                }while (choice2 == choice1);

                do {
                    choice3 = random.nextInt(countryList.size());
                }while (choice3 == choice1 || choice3 == choice2);

                iv_flag2.setImageResource(countryList.get(choice2).getImage());
                iv_flag3.setImageResource(countryList.get(choice3).getImage());
                ans2 = countryList.get(choice2).getName();
                ans3 = countryList.get(choice3).getName();

                ansArray[0] = ans1;
                ansArray[1] = ans2;
                ansArray[2] = ans3;

                cFlag1 = countryList.get(choice1).getImage();
                cFlag2 = countryList.get(choice2).getImage();
                cFlag3 = countryList.get(choice3).getImage();

                imageArray[0] = cFlag1;
                imageArray[1] = cFlag2;
                imageArray[2] = cFlag3;

                break;
            case 2:
                ansArray = new String[3];
                imageArray = new int[3];

                iv_flag2.setImageResource(countryList.get(choice1).getImage());
                ans2 = countryList.get(choice1).getName();

                do {
                    choice2 = random.nextInt(countryList.size());
                }while (choice2 == choice1);

                do {
                    choice3 = random.nextInt(countryList.size());
                }while (choice3 == choice1 || choice3 == choice2);

                iv_flag1.setImageResource(countryList.get(choice2).getImage());
                iv_flag3.setImageResource(countryList.get(choice3).getImage());
                ans1 = countryList.get(choice2).getName();
                ans3 = countryList.get(choice3).getName();

                ansArray[0] = ans1;
                ansArray[1] = ans2;
                ansArray[2] = ans3;

                cFlag1 = countryList.get(choice2).getImage();
                cFlag2 = countryList.get(choice1).getImage();
                cFlag3 = countryList.get(choice3).getImage();

                imageArray[0] = cFlag1;
                imageArray[1] = cFlag2;
                imageArray[2] = cFlag3;

                break;
            case 3:
                ansArray = new String[3];
                imageArray = new int[3];

                iv_flag3.setImageResource(countryList.get(choice1).getImage());
                ans3 = countryList.get(choice1).getName();

                do {
                    choice2 = random.nextInt(countryList.size());
                }while (choice2 == choice1);

                do {
                    choice3 = random.nextInt(countryList.size());
                }while (choice3 == choice1 || choice3 == choice2);

                iv_flag2.setImageResource(countryList.get(choice2).getImage());
                iv_flag1.setImageResource(countryList.get(choice3).getImage());
                ans2 = countryList.get(choice2).getName();
                ans1 = countryList.get(choice3).getName();

                ansArray[0] = ans1;
                ansArray[1] = ans2;
                ansArray[2] = ans3;

                cFlag1 = countryList.get(choice3).getImage();
                cFlag2 = countryList.get(choice2).getImage();
                cFlag3 = countryList.get(choice1).getImage();

                imageArray[0] = cFlag1;
                imageArray[1] = cFlag2;
                imageArray[2] = cFlag3;

                break;
        }


    }

    public void correctAnswerPopup(){
        Intent intent = new Intent(this, com.example.flaggame.correct.class);
        startActivity(intent);
    }

    public void wrongAnswerPopup(){
        Intent intent = new Intent(this, com.example.flaggame.WrongAdvancedMode.class);
        intent.putExtra("CorrectNameArr", ansArray);
        intent.putExtra("CorrectFlagArr", imageArray);
        startActivity(intent);

    }
}
