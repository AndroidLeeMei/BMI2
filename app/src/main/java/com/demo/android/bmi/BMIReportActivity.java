package com.demo.android.bmi;
//
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//
//public class BMIReportActivity extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_bmireport);
//    }
//}

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;

public class BMIReportActivity extends AppCompatActivity implements ViewStub.OnClickListener{
    TextView result, suggest,temperature;
    Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmireport);
        findViews();
        calcBMI();
        computTemperature();
    }


    void findViews() {
        result = (TextView) findViewById(R.id.result);
        suggest = (TextView) findViewById(R.id.suggest);
        temperature = (TextView)findViewById(R.id.changeTemperature);

        btnBack=(Button) findViewById(R.id.button_back);
        btnBack.setOnClickListener(this);
    }

    private void calcBMI() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        //取得資料,若物件之間沒有繼承關係時,可以用toString()做轉型
        String height = bundle.getString("KEY_HEIGHT");
        String weight = bundle.getString("KEY_WEIGHT");

        if ((!height.isEmpty()) && (!weight.isEmpty())) {
            double h = Double.parseDouble(height) / 100;
            double w = Double.parseDouble(weight);
            double BMI = w / (h * h);
            System.out.println("===h=" + h);
            DecimalFormat df = new DecimalFormat("0.00");  //用java的DecimalFormat

            result.setText("您的BMI值: " + df.format(BMI));
            System.out.println("BMI > 25=" + (BMI > 25));
            suggest.setText("您該節");
            if (BMI > 25)
                suggest.setText(R.string.advice_heavy);
            else if (BMI < 20)
                suggest.setText(R.string.advice_light);
            else
                suggest.setText(R.string.advice_average);

        }
    }
    //華氏=(攝*9/5)+32
    private void computTemperature(){
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        //取得資料,若物件之間沒有繼承關係時,可以用toString()做轉型
        String temp = bundle.getString("KEY_TEMPERATURE").toString();
        System.out.println("===temp=" + temp);
//        String temp = temperature.getText().toString();
        if (  !temp.isEmpty()) {
            double t = Double.parseDouble(temp);
            t=(t*9/5)+32;
            temperature.setText("攝氏溫度:"+ temp + "華氏溫度:"+t);
//            temperature.setText("華氏溫度:"+t );
//            temperature.setText("華氏溫度:"+df.format(t) );
        }
    }

    //        第二 種方法實作監聽器…
    @Override
    public void onClick(View v) {
        finish();
    }
}