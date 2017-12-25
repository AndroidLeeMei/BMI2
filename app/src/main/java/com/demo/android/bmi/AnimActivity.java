package com.demo.android.bmi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class AnimActivity extends AppCompatActivity {
    ImageView ivDark;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anim);
        ivDark=(ImageView)findViewById(R.id.iv_dark);
        playViewAnimation();
    }

    void playViewAnimation(){
        //要先產生一個動畫物件,並參數的地方要設定動畫資源
        Animation animation= AnimationUtils.loadAnimation(this,R.anim.transfer);
        ivDark.startAnimation(animation);
    }
}
