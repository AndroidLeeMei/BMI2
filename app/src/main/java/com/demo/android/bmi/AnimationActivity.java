package com.demo.android.bmi;

//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//
//public class AnimationActivity extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_animation);
//    }
//}

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class AnimationActivity extends AppCompatActivity {
    //閃爍動畫變數宣告
    private TextView tipText2,tipText3;
    private LinearLayout des;
    ImageView ivDark;
    //移動動畫宣告
    TextView textViewTranslate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        findViews();
        playViewAnimationAlpha(); //閃爍動畫
        playViewAnimationTranslate();//移動動畫
    }

    void findViews() {
        //閃爍動畫
        ivDark=(ImageView)findViewById(R.id.iv_dark);
        tipText2=(TextView)findViewById(R.id.tipText2);
        tipText3=(TextView)findViewById(R.id.tipText3);
        des=(LinearLayout)findViewById(R.id.description);
        //移動動畫
        textViewTranslate=(TextView)findViewById(R.id.textTranslate);
    }

    void playViewAnimationAlpha(){
        //要先產生一個動畫物件,並參數的地方要設定動畫資源
        Animation animation= AnimationUtils.loadAnimation(this,R.anim.anim_alpha);
        ivDark.startAnimation(animation);
    }

    //讓畫面旋轉完後可以還原實作onSaveInstanceState,onRestoreInstanceState
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
//        Log.d(TAG,"BMI.onSaveInstanceState");
        if (outState==null)
            outState=new Bundle();
        outState.putInt("clickCount",clickCoutn);//保存整數值
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
//        Log.d(TAG,"BMI.onRestoreInstanceState");
        if (savedInstanceState!=null)
            clickCoutn=savedInstanceState.getInt("clickCount");
        //旋轉後不再出現提示文
        if (clickCoutn>2)
            des.setVisibility(View.INVISIBLE);
//        des.setVisibility(View.INVISIBLE .GONE)
    }

    int clickCoutn = 1;
    public void onClickDescription(View target){
//        System.out.println("onClickDescription, and clickCoutn=" +clickCoutn);

        if (clickCoutn == 1) {
            tipText2.setVisibility(View.VISIBLE);
            clickCoutn++;
        } else if (clickCoutn == 2) {
            tipText3.setVisibility(View.VISIBLE);
            clickCoutn++;
        } else
            des.setVisibility(View.GONE);
    }

    //移動動畫開始
    void playViewAnimationTranslate(){
        //要先產生一個動畫物件,並參數的地方要設定動畫資源
        Animation animation= AnimationUtils.loadAnimation(this,R.anim.anim_translate);
        textViewTranslate.startAnimation(animation);
    }
}
