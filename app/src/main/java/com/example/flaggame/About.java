package com.example.flaggame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class About extends AppCompatActivity {
    TextView tv_about;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        tv_about = (TextView) findViewById(R.id.tv_about);

        tv_about.setText("This game was created as a Coursework of the Mobile App Development Programme under module code 5COSC011C.2." +
                "\n Name: Uditha Mahindarathna" +
                "\n BB Name : Don Mahindarathna" +
                "\n UOW # : W1673623" +
                "\n IIT # : 2016211" +
                "\n SUBMISSION DATE: 20/03/2019");
    }
}
