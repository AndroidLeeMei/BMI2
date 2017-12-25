package com.demo.android.bmi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.text.DecimalFormat;

public class ReportBMI2 extends AppCompatActivity {
    TextView result, suggest;
    DecimalFormat df = new DecimalFormat("0.00");  //用java的DecimalFormat
    public static final String TAG="LifeCycle";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_bmi2);
//        findViews();
//        valcBMI();
    }

    void findViews() {
        result = (TextView) findViewById(R.id.result);
        suggest = (TextView) findViewById(R.id.suggest);
    }

    void valcBMI() {
        //取得從bundle傳送過來的資料
        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();

        //取得資料,若物件之間沒有繼承關係時,可以用toString()做轉型
        String height =bundle.getString("KEY_HEIGHT");
        String weight =bundle.getString("KEY_WEIGHT");
        Log.d(TAG,"BMI值");
        if ((!height.isEmpty()) && (!weight.isEmpty())) {
            double h = Double.parseDouble(height) / 100;

            double w = Double.parseDouble(weight);
            double BMI = w / (h * h);

            System.out.println("===h=" + h);
            Log.d(TAG,"BMI值="+ BMI);
            result.setText("您的BMI值:" + df.format(BMI));
//            System.out.println("tel" + et.getText());
            if (BMI > 25)
                suggest.setText(R.string.advice_heavy + "");
            else if (BMI < 20)
                suggest.setText(R.string.advice_light + "");
            else
                suggest.setText(R.string.advice_average + "");

        }
    }
}

