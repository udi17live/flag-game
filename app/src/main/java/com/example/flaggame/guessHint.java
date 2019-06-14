package com.example.flaggame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.nio.CharBuffer;

public class guessHint extends AppCompatActivity {
    EditText et_country;
    TextView txt_dashedCountry;
    Button btn_submit;

    String text = "Hello";
    char[] countryCharArr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guess_hint);

        et_country = (EditText) findViewById(R.id.et_country);
        txt_dashedCountry = (TextView) findViewById(R.id.txt_dashedCountry);
        btn_submit = (Button) findViewById(R.id.btn_submit);



        countryCharArr = text.toUpperCase().toCharArray();





    }


}
