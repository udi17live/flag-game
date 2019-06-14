package com.example.flaggame;

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

public class WrongPopupFlagMode extends AppCompatActivity {
    Button btn_close;
    ImageView iv_flag;
    TextView tv_correct;

    String correctAns;
    int correctFLag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wrong_popup_flag_mode);

        DisplayMetrics display = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(display);

        int width = display.widthPixels;
        int height = display.heightPixels;

        getWindow().setLayout((int)(width*.8),(int)(height*.7));

        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.CENTER;

        getWindow().setAttributes(params);

        btn_close = (Button) findViewById(R.id.btn_close);
        tv_correct = (TextView) findViewById(R.id.tv_correct);
        iv_flag = (ImageView) findViewById(R.id.iv_flag);

        correctAns = getIntent().getExtras().getString("CorrectAnswer");
        tv_correct.setText(correctAns);

        correctFLag =getIntent().getExtras().getInt("CorrectFlag");
        iv_flag.setImageResource(correctFLag);


        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WrongPopupFlagMode.this.finish();
            }
        });
    }
}
