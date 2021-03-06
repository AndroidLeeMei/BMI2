package com.demo.android.bmi;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class Report_Game extends AppCompatActivity {
    Button[] bt = new Button[9];
    Button  buttonTar;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report__game);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        findViews();
    }

    private void findViews() {
        bt[0] = (Button) findViewById(R.id.button1);
        bt[1] = (Button) findViewById(R.id.button2);
        bt[2] = (Button) findViewById(R.id.button3);
        bt[3] = (Button) findViewById(R.id.button4);
        bt[4] = (Button) findViewById(R.id.button5);
        bt[5] = (Button) findViewById(R.id.button6);
        bt[6] = (Button) findViewById(R.id.button7);
        bt[7] = (Button) findViewById(R.id.button8);
        bt[8] = (Button) findViewById(R.id.button9);
        textView=(TextView)findViewById(R.id.textResult);
    }


    int clickCount = 0;
    ArrayList<String> strOX = new ArrayList<>();
    StringBuilder sbO = new StringBuilder();
    StringBuilder sbX = new StringBuilder();
    Boolean gameStart = true;
    String strWinner="";

    public void buttonClick(View target) {
        if (gameStart == true) {
            clickCount++;
            buttonTar = (Button) target;

            String strbtName;
            strbtName = buttonTar.toString().trim();
            strbtName = strbtName.substring(strbtName.length() - 2, strbtName.length() - 1);


            if (clickCount % 2 == 0) {
                if (buttonTar.getText().length() == 0) {
                    buttonTar.setText("X");
                    sbX.append(strbtName);
                    strWinner="X";
                }
            } else {
                if (buttonTar.getText().length() == 0) {
                    buttonTar.setText("O");
                    sbO.append(strbtName);
                    strWinner="O";
                }
            }
            strOX.add(sbX.toString());
            strOX.add(sbO.toString());

            int getGrade = 0;
            String[][] strSuccess = {{"1", "4", "7"}, {"1", "2", "3"}, {"1", "5", "9"}, {"2", "5", "8"}, {"3", "6", "9"}, {"3", "5", "7"}, {"7", "8", "9"}, {"4", "5", "6"}};
            complete:
            for (String str : strOX) {
                for (String[] s1 : strSuccess) {
                    getGrade = 0;
                    for (String s2 : s1) {
                        if (str.contains(s2)) {
                            getGrade++;
                        }
                        if (getGrade == 3) {
                            textView.setText("Winner is " + strWinner);
                            gameStart = false;
                            break complete;
                        }
                    }
                }
            }
        }
    }
}
