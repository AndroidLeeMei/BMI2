package com.demo.android.bmi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class AlphaActivity extends AppCompatActivity {
    private TextView tipText2,tipText3;
    private LinearLayout des;
    ImageView ivDark;
    boolean isVisible = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alpha);
        playViewAnimation();
    }

    void playViewAnimation(){
        System.out.println("start playViewAnimation0");
        //要先產生一個動畫物件,並參數的地方要設定動畫資源
        Animation animation= AnimationUtils.loadAnimation(this,R.anim.anim);
        System.out.println("start playViewAnimation1");
//        ivDark.startAnimation(animation);
//        System.out.println("start playViewAnimation2");
    }

    int clickCoutn = 1;
    public void onClickDescription(View target){
//        System.out.println("onClickDescription, and clickCoutn=" +clickCoutn);
//
//        if (clickCoutn == 1) {
//            tipText2.setVisibility(View.VISIBLE);
//            clickCoutn++;
//        } else if (clickCoutn == 2) {
//            tipText3.setVisibility(View.VISIBLE);
//            clickCoutn++;
//        } else
//            des.setVisibility(View.GONE);
    }
}
