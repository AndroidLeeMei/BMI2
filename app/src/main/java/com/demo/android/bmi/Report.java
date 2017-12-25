package com.demo.android.bmi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.util.Log;
import android.view.View;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;

public class Report extends AppCompatActivity implements ViewStub.OnClickListener{
    TextView result,suggest,suggest1;
    Button back;
    public static final String TAG="LifeCycle";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        
        findViews();
        calcBMI();
    }



    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG,"Report.onStart");
    }
    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG,"Report.onStop");
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG,"Report.onRestart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"Report.onDestroy");
    }
    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG,"Report.onResume");
    }
    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG,"Report.onPause");
    }
    void findViews(){
        result = (TextView) findViewById(R.id.result);
        suggest = (TextView) findViewById(R.id.suggest);
        suggest1 = (TextView)findViewById(R.id.suggest);

        back=(Button) findViewById(R.id.btn_back);
        back.setOnClickListener(this);
    }

    private void calcBMI(){
        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        //取得資料,若物件之間沒有繼承關係時,可以用toString()做轉型
        String height = bundle.getString("KEY_HEIGHT");
        String weight = bundle.getString("KEY_WEIGHT");

        System.out.println("您的BMI值:" + height);


        if ((!height.isEmpty()) &&(!weight.isEmpty())) {
            double h = Double.parseDouble(height) / 100;
            double w = Double.parseDouble(weight);
            double BMI = w / (h * h);
            System.out.println("===h=" + h);
            DecimalFormat df = new DecimalFormat("0.00");  //用java的DecimalFormat
            suggest1.setText("222");

            result.setText("您的BMI值: " + df.format(BMI));
            System.out.println("BMI > 25=" + (BMI > 25));
            suggest.setText("您該節");
            if (BMI > 25) {
                suggest.setText(R.string.advice_heavy);
//                suggest.setText("您1");
                System.out.println("1BMI > 25");
            }
            else if (BMI < 20){
                System.out.println("2BMI < 25");

                suggest.setText(R.string.advice_light );
//                suggest.setText("您2");
            }

            else{
                System.out.println("3else");

                suggest.setText(R.string.advice_average);
//                suggest.setText("您3");
            }


            System.out.println("BMI > 25=" + (BMI > 25));
        }

    }

    @Override
    public void onClick(View v) {
//        fini在sh();  //會消毁此物件
        //之前的身高,體重資料還
//        10-11 05:20:34.345 28547-28547/com.demo.android.bmi D/LifeCycle: Report.onPause
//        10-11 05:20:34.358 28547-28547/com.demo.android.bmi D/LifeCycle: Bmi.onRestart
//        10-11 05:20:34.359 28547-28547/com.demo.android.bmi D/LifeCycle: Bmi.onStart
//        10-11 05:20:34.359 28547-28547/com.demo.android.bmi D/LifeCycle: Bmi.onResume
//        10-11 05:20:34.717 28547-28547/com.demo.android.bmi D/LifeCycle: Report.onStop
//        10-11 05:20:34.717 28547-28547/com.demo.android.bmi D/LifeCycle: Report.onDestroy//
        //並沒有要finish,之前的身高,體重資料己經消失,因為是因為又建立一個新的BMI,之前的資料還留在舊的BMI裏
        Intent intent=new Intent();
        intent.setClass(Report.this,Bmi.class);
        startActivity(intent);
//        10-11 05:19:23.407 26118-26118/com.demo.android.bmi D/LifeCycle: Report.onPause
//        10-11 05:19:23.420 26118-26118/com.demo.android.bmi D/LifeCycle: BMI.onCreate
//        10-11 05:19:23.442 26118-26118/com.demo.android.bmi D/LifeCycle: Bmi.onStart
//        10-11 05:19:23.442 26118-26118/com.demo.android.bmi D/LifeCycle: Bmi.onResume
//        10-11 05:19:23.883 26118-26118/com.demo.android.bmi D/LifeCycle: Report.onStop
    }
}
