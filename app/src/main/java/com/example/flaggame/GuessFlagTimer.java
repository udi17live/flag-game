package com.example.flaggame;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class GuessFlagTimer extends AppCompatActivity {
    private static final long COUNTDOWN_TIME = 10000;

    ImageView iv_ans1, iv_ans2, iv_ans3;
    TextView txt_country, txt_time;
    String ans1,ans2,ans3;

    List<Country> countryList = new ArrayList<>();
    CountryDatabase countryDB = new CountryDatabase();
    Country countryItem = null;
    String correctAnswer="";
    int correctFlag;

    Random random;
    int turn = 1;

    CountDownTimer countdownTimer;
    long timeLeft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guess_flag_timer);

        random = new Random();

        iv_ans1 = (ImageView) findViewById(R.id.iv_ans1);
        iv_ans2 = (ImageView) findViewById(R.id.iv_ans2);
        iv_ans3 = (ImageView) findViewById(R.id.iv_ans3);

        txt_country = (TextView) findViewById(R.id.txt_country);
        txt_time = (TextView) findViewById(R.id.txt_time);

        // Adding Names,Codes and Images to a List
        for(int i=0; i< countryDB.countryCodes.length;i++){
            countryItem = new Country(countryDB.countryCodes[i],countryDB.countryNames[i],countryDB.flagImg[i]);
            countryList.add(i,countryItem);
        }

        //Shuffle to Randomize
        Collections.shuffle(countryList);


        newCountry(turn);
        correctAnswer = countryList.get(turn-1).getName();
        correctFlag = countryList.get(turn-1).getImage();

        iv_ans1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //checking correct Answer
                if (countryList.get(turn - 1).getName().equalsIgnoreCase(ans1) ){
                    correctAnswerPopup();

                    if (turn < countryList.size()){
                        turn++;
                        newCountry(turn);
                        correctAnswer = countryList.get(turn-1).getName();
                        correctFlag = countryList.get(turn-1).getImage();
                    }
                }else{
                    wrongAnswerPopup();


                    if (turn < countryList.size()){
                        turn++;
                        newCountry(turn);
                        correctAnswer = countryList.get(turn-1).getName();
                        correctFlag = countryList.get(turn-1).getImage();
                    }
                }

            }
        });

        iv_ans2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //checking correct Answer
                if (countryList.get(turn - 1).getName().equalsIgnoreCase(ans2)){
                    correctAnswerPopup();

                    if (turn < countryList.size()){
                        turn++;
                        newCountry(turn);
                        correctAnswer = countryList.get(turn-1).getName();
                        correctFlag = countryList.get(turn-1).getImage();
                    }
                }else{
                    wrongAnswerPopup();


                    if (turn < countryList.size()){
                        turn++;
                        newCountry(turn);
                        correctAnswer = countryList.get(turn-1).getName();
                        correctFlag = countryList.get(turn-1).getImage();
                    }
                }
            }
        });

        iv_ans3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //checking correct Answer
                if (countryList.get(turn - 1).getName().equalsIgnoreCase(ans3) ){
                    correctAnswerPopup();

                    if (turn < countryList.size()){
                        turn++;
                        newCountry(turn);
                        correctAnswer = countryList.get(turn-1).getName();
                        correctFlag = countryList.get(turn-1).getImage();
                    }
                }else{
                    wrongAnswerPopup();


                    if (turn < countryList.size()){
                        turn++;
                        newCountry(turn);
                        correctAnswer = countryList.get(turn-1).getName();
                        correctFlag = countryList.get(turn-1).getImage();
                    }
                }
            }
        });
    }

    public void newCountry(int num){
        txt_country.setText(countryList.get(num-1).getName());

        //setting random choices
        int correctAns = random.nextInt(3) + 1;

        int choice1 = num - 1;
        int choice2;
        int choice3;

        switch(correctAns){
            case 1:
                iv_ans1.setImageResource(countryList.get(choice1).getImage());
                ans1 = countryList.get(choice1).getName();

                do {
                    choice2 = random.nextInt(countryList.size());
                }while (choice2 == choice1);

                do {
                    choice3 = random.nextInt(countryList.size());
                }while (choice3 == choice1 || choice3 == choice2);

                iv_ans2.setImageResource(countryList.get(choice2).getImage());
                iv_ans3.setImageResource(countryList.get(choice3).getImage());
                ans2 = countryList.get(choice2).getName();
                ans3 = countryList.get(choice3).getName();

                break;
            case 2:
                iv_ans2.setImageResource(countryList.get(choice1).getImage());
                ans2 = countryList.get(choice1).getName();

                do {
                    choice2 = random.nextInt(countryList.size());
                }while (choice2 == choice1);

                do {
                    choice3 = random.nextInt(countryList.size());
                }while (choice3 == choice1 || choice3 == choice2);

                iv_ans1.setImageResource(countryList.get(choice2).getImage());
                iv_ans3.setImageResource(countryList.get(choice3).getImage());
                ans1 = countryList.get(choice2).getName();
                ans3 = countryList.get(choice3).getName();

                break;
            case 3:
                iv_ans3.setImageResource(countryList.get(choice1).getImage());
                ans3 = countryList.get(choice1).getName();

                do {
                    choice2 = random.nextInt(countryList.size());
                }while (choice2 == choice1);

                do {
                    choice3 = random.nextInt(countryList.size());
                }while (choice3 == choice1 || choice3 == choice2);

                iv_ans2.setImageResource(countryList.get(choice2).getImage());
                iv_ans1.setImageResource(countryList.get(choice3).getImage());
                ans2 = countryList.get(choice2).getName();
                ans1 = countryList.get(choice3).getName();
                break;
        }
        timeLeft = COUNTDOWN_TIME;
        startCountdown();
    }

    public void correctAnswerPopup(){
        Intent intent = new Intent(this, com.example.flaggame.correct.class);
        startActivity(intent);
    }

    public void wrongAnswerPopup(){
        Intent intent = new Intent(this, com.example.flaggame.WrongPopupFlagMode.class);
        intent.putExtra("CorrectAnswer", correctAnswer);
        intent.putExtra("CorrectFlag", correctFlag);
        startActivity(intent);
    }

    private void startCountdown(){
        countdownTimer = new CountDownTimer(timeLeft,1000){

            @Override
            public void onTick(long millisUntilFinished) {
                timeLeft = millisUntilFinished;
                updateCountdownTimerText();
            }

            @Override
            public void onFinish() {
                timeLeft = 0;
                updateCountdownTimerText();
            }
        }.start();
    }

    private void updateCountdownTimerText(){
        int minutes = (int)(timeLeft/1000)/60;
        int seconds = (int) (timeLeft/1000) % 60;

        String formattedTime = String.format(Locale.getDefault(),"%02d:%02d", minutes,seconds);
        txt_time.setText(formattedTime);
    }

}
