package com.example.flaggame;

import android.app.Activity;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class WrongAdvancedMode extends Activity {

    ImageView iv_CFlag1, iv_CFlag3, iv_CFlag2;
    TextView tv_CName1, tv_CName2, tv_CName3;
    Button btn_close;

    int[] imageArray = new int[3];
    String[] ansArray = new String[3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wrong_advanced_mode);

        DisplayMetrics display = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(display);

        int width = display.widthPixels;
        int height = display.heightPixels;

        getWindow().setLayout((int)(width*.8),(int)(height*.9));

        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.CENTER;

        getWindow().setAttributes(params);

        iv_CFlag1 = (ImageView) findViewById(R.id.iv_CFlag1);
        iv_CFlag2 = (ImageView) findViewById(R.id.iv_CFlag2);
        iv_CFlag3 = (ImageView) findViewById(R.id.iv_CFlag3);

        tv_CName1 = (TextView) findViewById(R.id.tv_CName1);
        tv_CName2 = (TextView) findViewById(R.id.tv_CName2);
        tv_CName3 = (TextView) findViewById(R.id.tv_CName3);

        btn_close = (Button) findViewById(R.id.btn_close);

        ansArray = getIntent().getExtras().getStringArray("CorrectNameArr");
        imageArray = getIntent().getExtras().getIntArray("CorrectFlagArr");

        iv_CFlag1.setImageResource(imageArray[0]);
        iv_CFlag2.setImageResource(imageArray[1]);
        iv_CFlag3.setImageResource(imageArray[2]);

        tv_CName1.setText(ansArray[0]);
        tv_CName2.setText(ansArray[1]);
        tv_CName3.setText(ansArray[2]);


        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WrongAdvancedMode.this.finish();
            }
        });
    }
}
