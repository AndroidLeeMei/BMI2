package com.demo.android.bmi;//package com.demo.android.bmi;
//
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//
//public class CalculatorActivity extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_calculator);
//    }
//}



//package com.demo.android.bmi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;


public class CalculatorActivity extends AppCompatActivity {
    DecimalFormat formatType=new DecimalFormat("0.00");
    Button bt00,bt0,bt1,bt2,bt3,bt4,bt5,bt6,bt7,bt8,bt9,bt_a,bt_m,bt_mm,bt_d,bt_equal,bt_dot,bt_clr;

    String problem="", operType,ansError="";
    double ans=0.0,NextNum=0.0;
    int preIndex;
    TextView textView;
    boolean nextProblem=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        findViews();
        setOnClick();


    }

    private double getProblenAns(String problem){
        boolean booOper = true;
//        System.out.println("problem.length() =" + problem.length() );
        for (int i = 0; i < problem.length() ; i++) {


            if ((problem.substring(i, i + 1) .equals("="))|| (problem.substring(i, i + 1) .equals("+")) || (problem.substring(i, i + 1).equals( "-")) || (problem.substring(i, i + 1).equals( "x")) || (problem.substring(i, i + 1).equals( "/")))
            {

                if (i==0||(i== preIndex)) {
                    System.out.println("==index=" + i);
                    System.out.println("char=" + problem.substring(i, i + 1));
                    System.out.println("preIndex="+preIndex);
                    System.out.println("i== preIndex" + (i== preIndex));
//                    if (i==0||(i== problem.length()-2)) {
                    ansError="can not compute!!";
                    break;
                }
//                System.out.println("===" + problem.substring(i, i + 1) +",  booOper====" +booOper);
//                System.out.println("oper=====" +problem.substring(i, i + 1) == "+");
//                System.out.println("booOper == false=" + (booOper == true));
                if (booOper==  true){
//                    System.out.println("booOper=" + booOper);
                    ans = Double.parseDouble(problem.substring(0, i ));
//                   System.out.println("ans====" +ans);
                    booOper = false;
                    operType=problem.substring(i, i + 1);

                }
                else {
                    NextNum = Double.parseDouble(problem.substring(preIndex+1, i ));
                    System.out.println("NextNum=" + NextNum);
                    ans = getAns(ans, NextNum, operType);
                    operType=problem.substring(i, i + 1);
                }


//                System.out.println("operType" + operType);
                preIndex=i;
            }
        }
//        System.out.println("ans=" + ans);
        return ans;
    }

    private double getAns(Double preNum,Double nextNum,String operType){
        double result=0.0;
//        System.out.println(preNum + "   "  +nextNum  + "  "  + operType);

        switch (operType) {
            case "+":
                result=preNum +nextNum;
                break;
            case "-":
                result=preNum -nextNum;
                break;
            case "x":
                result=preNum *nextNum;
                break;
            case "/":
                result = preNum / nextNum;

                break;
        }
        return result;


    }

    private void setOnClick(){
        bt00.setOnClickListener(listener);
        bt0.setOnClickListener(listener);
        bt1.setOnClickListener(listener);
        bt2.setOnClickListener(listener);
        bt3.setOnClickListener(listener);
        bt4.setOnClickListener(listener);
        bt5.setOnClickListener(listener);
        bt6.setOnClickListener(listener);
        bt7.setOnClickListener(listener);
        bt8.setOnClickListener(listener);
        bt9.setOnClickListener(listener);
        bt_a.setOnClickListener(listener);
        bt_m.setOnClickListener(listener);
        bt_mm.setOnClickListener(listener);
        bt_d.setOnClickListener(listener);
        bt_equal.setOnClickListener(listener);
        bt_dot.setOnClickListener(listener);
        bt_clr.setOnClickListener(listener);

    }
    private void findViews(){
        bt00=(Button) findViewById(R.id.button00);
        bt0=(Button) findViewById(R.id.button0);
        bt1=(Button) findViewById(R.id.button1);
        bt2=(Button) findViewById(R.id.button2);
        bt3=(Button) findViewById(R.id.button3);
        bt4=(Button) findViewById(R.id.button4);
        bt5=(Button) findViewById(R.id.button5);
        bt6=(Button) findViewById(R.id.button6);
        bt7=(Button) findViewById(R.id.button7);
        bt8=(Button) findViewById(R.id.button8);
        bt9=(Button) findViewById(R.id.button9);
        bt_a=(Button) findViewById(R.id.add);
        bt_m=(Button) findViewById(R.id.minus);
        bt_mm=(Button) findViewById(R.id.multi);
        bt_d=(Button) findViewById(R.id.division);
        bt_equal=(Button) findViewById(R.id.button_equal);
        bt_dot=(Button) findViewById(R.id.button_dot);
        bt_clr=(Button) findViewById(R.id.button_clr);

        textView=(TextView) findViewById(R.id.textView);

    }

    private void getNextProblem(){
        if (nextProblem==false) {
            textView.setText("");
            nextProblem=true;
            preIndex=0;
            problem = "";
        }
    }

    private View.OnClickListener listener=new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            getNextProblem();
            if (v==bt_clr ) {
                textView.setText("");
                problem = "";
                preIndex=0;
            }
            else {
                if (v == bt0)
                    problem += "0";
                else if (v == bt00)
                    problem += "00";
                else if (v == bt1)
                    problem += "1";
                else if (v == bt2)
                    problem += "2";
                else if (v == bt3)
                    problem += "3";
                else if (v == bt4)
                    problem += "4";
                else if (v == bt5)
                    problem += "5";
                else if (v == bt6)
                    problem += "6";
                else if (v == bt7)
                    problem += "7";
                else if (v == bt8)
                    problem += "8";
                else if (v == bt9)
                    problem += "9";
                else if (v == bt_a)
                    problem += "+";
                else if (v == bt_m)
                    problem += "-";
                else if (v == bt_mm)
                    problem += "x";
                else if (v == bt_d)
                    problem += "/";
                else if (v == bt_dot)
                    problem += ".";

                else if (v == bt_equal) {
                    problem += "=";
                    double aa =  getProblenAns(problem);




                    if (ansError=="")
                        problem=problem + formatType.format(aa);
                    else {
                        problem = problem + ansError;
                        ansError = "";
                    }

                    nextProblem=false;

                }
                textView.setText(problem );
            }
        }



    };


}
