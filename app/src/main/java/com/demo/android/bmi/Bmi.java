package com.demo.android.bmi;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.text.DecimalFormat;




//華氏=(攝*9/5)+32
//目前android預設繼承AppCompatActivity,方法二
//AppCompatActivity己經實作完所有抽象方法
public class Bmi extends AppCompatActivity {
    private TextView tipText2,tipText3;
    private LinearLayout des;
    ImageView ivDark,imageView1,imageView3,img;
    boolean isVisible = true;
    Button button,buttonTemp;
    EditText fieldHeight,fieldWeight,fieldTemperature;//,fieldTemp
    //TextView result,suggest;
    DecimalFormat df = new DecimalFormat("0.00");  //用java的DecimalFormat
    String[] items;
    int[] iconRes;
    static int clickCoutn = 1;
    public static final String TAG="LifeCycle";

    //讓畫面旋轉完後可以還原實作onSaveInstanceState,onRestoreInstanceState
//    boolean isFirstTime=true;
    @Override//在onPause之前會被呼叫
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);  //預設只有第一行
        Log.d(TAG,"BMI.onSaveInstanceState");
        if (outState!=null)
            outState.putInt("clickCoutn",clickCoutn);
//        if (outState==null)
//            outState=new Bundle();
//        outState.putInt("clickCount",clickCoutn);//保存整數值
    }

    @Override//在onresume之前會被呼叫
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState !=null)
            clickCoutn=savedInstanceState.getInt("clickCoutn");

//        Log.d(TAG,"BMI.onRestoreInstanceState");
//        if (savedInstanceState!=null)
//            clickCoutn=savedInstanceState.getInt("clickCount");
        //旋轉後不再出現提示文
        if (clickCoutn>=3)
            des.setVisibility(View.GONE);
        else if (clickCoutn==2) { //當點第一次後畫面旋轉再點第二次時
            des.setVisibility(View.VISIBLE);
            tipText2.setVisibility(View.VISIBLE);
            tipText3.setVisibility(View.VISIBLE);
        }else if (clickCoutn==1) {
            //下面不用寫
//            des.setVisibility(View.VISIBLE);
//            tipText2.setVisibility(View.VISIBLE);
//            tipText3.setVisibility(View.VISIBLE);
        }
//        else
//            clickCoutn=1;  //旋轉後重新出現提示
    }


    public void onClickDescription(View target){
        System.out.println("onClickDescription, and clickCoutn=" +clickCoutn);
        System.out.println("clickCoutn == 2="+ (clickCoutn == 2));
//        if (isFirstTime ) {  //先檢查是不是第一次,是第一次才需要出現提示文
            if (clickCoutn == 1) {
                tipText2.setVisibility(View.VISIBLE);
                clickCoutn++;
            } else if (clickCoutn == 2) {
                System.out.println("in clickCoutn == 2");
                tipText3.setVisibility(View.VISIBLE);
                clickCoutn++;
            } else
                des.setVisibility(View.GONE);
//        }
//        isFirstTime=false;//提示文只出現一次,以後旋轉後不再出現
    }





    public void onClickMoveTrans(View target){
//        System.out.println("動畫效果");
//        playViewAnimation();

        Intent intent=new Intent();
        intent.setClass(Bmi.this,AnimActivity.class);
        startActivity(intent);

    }


    public void onClickBMI_Report(View target){
            System.out.println("BMi ");
            Intent intent = new Intent();
            intent.setClass(Bmi.this, ReportBMI2.class);
            System.out.println("BMi1 ");
            Bundle bundle = new Bundle();
            bundle.putString("KEY_HEIGHT", fieldHeight.getText().toString());
            bundle.putString("KEY_WEIGHT", fieldWeight.getText().toString());
            System.out.println("BMi 2");
            intent.putExtras(bundle);
            startActivity(intent);  //開始出發
            finish();


            //不要bundle的寫法
//        intent.putExtra("KEY_HEIGHT",fieldHeight.getText().toString());
//        intent.putExtra("KEY_WEIGHT",fieldWeight.getText().toString());

        }

    @Override
    protected void onCreate(Bundle savedInstanceState) {  //由onCreate方法開始執行
        super.onCreate(savedInstanceState);  //一定要加,要呼叫到父類別現有的功能
        Log.d(TAG,"BMI.onCreate");
        setContentView(R.layout.activity_bmi);//設定主畫面參考檔案,不要加副檔名,這時才會產生button等物件
        init();  //專門負責各種初始化資料
        findViews();
        setListeners();
//        playViewAnimation();
        //屬性動畫開始
        playFrameAnimation4();
        //底下兩個方法二選一1
        playPorpertyAnimationFromXML();
//        playPropertyAnimation();
        //屬性動畫束
        playFrameAnimation();
        playFrameAnimation3();

//
//        AnimationDrawable anim1=(AnimationDrawable) imageView1.getBackground();
//        anim1.start();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG,"Bmi.onStart");
    }
    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG,"Bmi.onResume");
    }
    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG,"Bmi.onPause");
    }
    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG,"Bmi.onStop");
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG,"Bmi.onRestart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"Bmi.onDestroy");
    }








    //取得陣列資料
    void init(){
        items=getResources().getStringArray(R.array.items);
        iconRes=new int[]{
                R.drawable.bullbasaur
                ,R.drawable.meowth
                ,R.drawable.meowth
                ,R.drawable.pikachu
                ,R.drawable.meowth
                ,R.drawable.psyduck};

        //隱藏標題BMI名稱
        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(false);

    }

    void findViews() {
        buttonTemp = (Button) findViewById(R.id.buttonTemp);
        button = (Button) findViewById(R.id.submit);
//        buttonBMI = (Button) findViewById(R.id.buttonBMI);
        fieldHeight = (EditText) findViewById(R.id.height);
        fieldWeight = (EditText) findViewById(R.id.weight);
        //fieldTemp=  (EditText) findViewById(R.id.temp);
        fieldTemperature=(EditText)findViewById(R.id.edit_temperature);
        imageView3=(ImageView)findViewById(R.id.image3);
        imageView1=(ImageView)findViewById(R.id.image1);
        ivDark=(ImageView)findViewById(R.id.iv_dark);
//        result = (TextView) findViewById(R.id.result);
//        suggest = (TextView) findViewById(R.id.suggest);
        img= (ImageView)findViewById(R.id.imageProperty);


        tipText2=(TextView)findViewById(R.id.tipText2);
        tipText3=(TextView)findViewById(R.id.tipText3);
        des=(LinearLayout)findViewById(R.id.description);

        ivDark=(ImageView)findViewById(R.id.iv_dark);


    }

    void setListeners() {
        button.setOnClickListener(listener);
        buttonTemp.setOnClickListener(listener);
    }


    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            System.out.println("listener1");
            Intent intent=new Intent();
//            intent.setClass(Bmi.this,Report.class);
            intent.setClass(Bmi.this,BMIReportActivity.class);
//            開始Intent傳遞參數程式
            Bundle bundle=new Bundle();
            bundle.putString("KEY_HEIGHT",fieldHeight.getText().toString());
            bundle.putString("KEY_WEIGHT",fieldWeight.getText().toString());
            bundle.putString("KEY_TEMPERATURE",fieldTemperature.getText().toString());

            intent.putExtras(bundle);
            startActivity(intent);


//     //       if (v.getId()==R.id.submit)  //適於有layout時可使用
//            if (v==buttonTemp)   //適用於new出物件時的寫法,id可參考
//                computTemp();
//            else
//                computeBMI();
//            openOptionsDialog();

                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          }
    };


    void openOptionsDialog(){
        //toast begin
        Toast.makeText(Bmi.this,"顯示Toast訊",Toast.LENGTH_LONG ).show();

        final ProgressDialog progressDialog=ProgressDialog.show(Bmi.this,"處理","請等一會,處理完畢會自動結束");
        Thread thread=new Thread(){
            @Override
            public void run(){
                try{
                    Thread.sleep(5000);
                }  catch(InterruptedException e){
                    e.printStackTrace();
                }
                progressDialog.dismiss();
            }
        };
        thread.start();
    }

    void openOptionsDialog1(){

        //裏下為電話號嗎的
        findDialogViews();
        AlertDialog.Builder ab=new AlertDialog.Builder(this);
//        ab.setTitle(R.string.about_tilte);
//        ab.setTitle(getString(R.string.about_tilte));
//        ab.setMessage("我的內容");
//
//
////        ab.setTitle("我的標題").setMessage("我的內容").show();
//
//        ab.setPositiveButton("確定",dialogListener);
//        ab.setNeutralButton("首頁",dialogListener);
//        ab.setView(R.layout.dialog_layout);  //這一段無法執行關閉
        ab.setView(root);
        dialog=ab.show();
//

    }


//    private void computTemp(){
//        //華氏=(攝*9/5)+32
//        String temp = fieldTemp.getText().toString();
//        if (  !temp.isEmpty()) {
//            double t = Double.parseDouble(temp);
//            t=(t*9/5)+32;
//      //      result.setText("攝氏溫度:"+ temp);
//       //     suggest.setText("華氏溫度:"+df.format(t) );
//        }
//    }
    private void computeBMI(){
        //取得資料,若物件之間沒有繼承關係時,可以用toString()做轉型
        String height = fieldHeight.getText().toString();
        String weight = fieldWeight.getText().toString();
        if ((!height.isEmpty()) &&(!weight.isEmpty())) {
            double h = Double.parseDouble(height) / 100;

            double w = Double.parseDouble(weight);
            double BMI = w / (h * h);
            System.out.println("===h=" + h);

//            result.setText("您的BMI值:" + df.format(BMI));
////            System.out.println("tel" + et.getText());
//            if (BMI > 25)
//                suggest.setText(R.string.advice_heavy + "" );
//            else if (BMI < 20)
//                suggest.setText(R.string.advice_light+ "" );
//            else
//                suggest.setText(R.string.advice_average+ "");
            openOptionsDialogA();

        }

    }


    AlertDialog dialog; //讓自定Layout可有關閉功能
    View root;
    EditText et;
    Button confirm;
    //找出自定ｌａｙｏｕｔ裏的元件,待對話框有顯示出來才可執行此方法
    void findDialogViews(){
        //setC
        root =getLayoutInflater().inflate(R.layout.dialog_layout,null);//找出根源樹,
        et=(EditText)root.findViewById(R.id.et_tel);  //若不使用root,則它會去找主畫面的layout的元件
        confirm=(Button)root.findViewById(R.id.btm_confirm);
        confirm.setOnClickListener(dialoglistener);
    }

    View.OnClickListener dialoglistener=new View.OnClickListener(){

        @Override
        public void onClick(View v) {
            System.out.println("按下自訂對話的按鈕");
//            System.out.println(et.getText());
//            suggest.setText(et.getText() );
//            suggest.setTextColor(Color.BLUE);
            dialog.dismiss();

        }
    };

//    //若要用自制的layout,則不用這個
//    DialogInterface.OnClickListener dialogListener=new DialogInterface.OnClickListener() {
//        @Override
//        public void onClick(DialogInterface dialog, int which) {
//            //setPositiveButton  -3,setBUTTON_NEUTRAL -1
////            if (which==-3)//setPositiveButton
//            if (which==DialogInterface.BUTTON_POSITIVE)
//                System.out.println("按下確認");
//            else if (which==DialogInterface.BUTTON_NEUTRAL)
//                System.out.println("按下首頁");
//
//
//            System.out.println("DialogInterface.BUTTON_POSITIVE"+DialogInterface.BUTTON_POSITIVE);
//            System.out.println("DialogInterface.BUTTON_POSITIVE"+DialogInterface.BUTTON_NEUTRAL);
//            System.out.println("DialogInterface.BUTTON_POSITIVE"+DialogInterface.BUTTON_NEGATIVE);
//
//        }
//    };


    //使用code方式,動態建立menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //          menu.add(0,100,0,"結束");
        //在2.0之後要加入setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM)才會顯示圖片
        for (int i=0;i<items.length;i++) {
            //只會顯示部份圖檔MenuItem.SHOW_AS_ACTION_IF_ROOM
         //   menu.add(0, (i + 1) * 100, 0, items[i]).setIcon(iconRes[i]).setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);

            //一定會顯示圖檔
            menu.add(0, (i + 1) * 100, 0, items[i]).setIcon(iconRes[i]).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
            System.out.println("items[i]=" + items[i]);
        }
//

//        menu.add(0,200,0,"結束");
//        menu.add(0,300,0,"項目3");
//        menu.add(0,400,0,"項目4");
//        menu.add(0,500,0,"項目5");
//        menu.add(0,600,0,"項目6");
        return super.onCreateOptionsMenu(menu);
    }

    //只支援co
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case 100:
                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).show();

            case 200:
            case 300:
            case 400:
            case 500:
            case 600:
            default:
                // Toast.makeText(Bmi.this,item.getTitle(),Toast.LENGTH_LONG).show();
                Toast.makeText(Bmi.this,item.getTitle(),Toast.LENGTH_SHORT).show();
                break;

        }
        return super.onOptionsItemSelected(item);
    }




    //使用xml方式建立menu
    public boolean onCreateOptionsMenu2(Menu menu) {
        //方法
        getMenuInflater().inflate(R.menu.menu,menu);
        //方法可以混搭
        for (int i=0;i<items.length;i++) {
            menu.add(0, (i + 1) * 100, 0, items[i]);
            System.out.println("items[i]=" + items[i]);
        }
        return super.onCreateOptionsMenu(menu);
    }




    //由xml建立選單時,需要用R.id.item1的方式取得
    public boolean onOptionsItemSelected2(MenuItem item) {
        switch(item.getItemId()){
            case R.id.item1:
                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).show();

            case R.id.item2:
            case R.id.item3:
            case R.id.item4:
            case R.id.item5:
            case R.id.item6:
            default:
               // Toast.makeText(Bmi.this,item.getTitle(),Toast.LENGTH_LONG).show();
                Toast.makeText(Bmi.this,item.getTitle(),Toast.LENGTH_SHORT).show();
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    void openOptionsDialogA(){
        AlertDialog.Builder builer=new AlertDialog.Builder(this);
        builer.setTitle(R.string.about_tilte);
        builer.setMessage(R.string.about_message);
        builer.setNeutralButton("首頁",dialog_listener);
        builer.show();
    }

    DialogInterface.OnClickListener dialog_listener=new DialogInterface.OnClickListener(){
        @Override
        public void onClick(DialogInterface dialog,int which) {
//            Uri uri= Uri.parse("geo:24.930739,121.172424");

        //    Uri uri= Uri.parse("https://sites.google.com./site/gasodroid/");

        //    Intent intent = new Intent(Intent.ACTION_VIEW,uri);

//            Uri telUri = Uri.parse("tel:100861");
//            Intent intent = new Intent(Intent.ACTION_DIAL, telUri);
       //     startActivity(intent);

            //音樂//取得根路徑
       //     String baseDir = Environment.getExternalStorageDirectory().getAbsolutePath();
       //     Intent intent = new Intent();
       //     intent.setAction(android.content.Intent.ACTION_VIEW);
            //代表一個檔案的物件
       //     File file = new File(baseDir+"/Music/bgm.mp3");//在sdcard/music,使用模擬器時,不支援中文檔名
       //     intent.setDataAndType(Uri.fromFile(file), "audio/*");  //資料類型
       //     startActivity(intent);

            //打電話
            Uri callUri = Uri.parse("tel:100861");
            Intent intent = new Intent(Intent.ACTION_CALL, callUri);
        }
    };


    //動畫
    void playViewAnimation(){
        //要先產生一個動畫物件,並參數的地方要設定動畫資源
        Animation animation= AnimationUtils.loadAnimation(this,R.anim.anim);
        ivDark.startAnimation(animation);
    }

    void playFrameAnimation(){
        AnimationDrawable anim1=(AnimationDrawable) imageView1.getBackground();
        anim1.start();
    }
    void playFrameAnimation3(){
        AnimationDrawable anim3=(AnimationDrawable) imageView3.getBackground();
        anim3.start();
    }

    void playFrameAnimation4(){
        AnimationDrawable anim1=(AnimationDrawable) img.getBackground();
        anim1.start();
    }

    //底下兩個二選一
    void playPorpertyAnimationFromXML(){
        AnimatorSet set=(AnimatorSet) AnimatorInflater.loadAnimator(this,R.animator.animator);//決定前面寫到的動畫效困是用那一種動畫,先後順序
        set.setTarget(img);
        set.start();
    }


    void playPropertyAnimation(){
        ObjectAnimator oaRotate =ObjectAnimator.ofFloat(img,"rotation",0,360);
        oaRotate.setDuration(1000);
        oaRotate.setRepeatCount(5);  //若是採用依序播放動畫方式,第一段動畫的播放次數不可設為無限,否則其他動畫看不到
        oaRotate.setRepeatMode(ObjectAnimator.REVERSE);
        oaRotate.addListener(new Animator.AnimatorListener() {//監聽動畫事件,非必要
            @Override
            public void onAnimationStart(Animator animation) {

            }
            @Override
            public void onAnimationRepeat(Animator animation) {

            }
            @Override
            public void onAnimationEnd(Animator animation) {
                Toast.makeText(Bmi.this,"第一段動畫結束",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }
        });
        //建立移動動畫
        //第二個參數是表示水平方向移動,Y表示上下移動,
        //第三個參數是從0移動到400
        ObjectAnimator oaForad=ObjectAnimator.ofFloat(img,"x",0,400);
        oaForad.setDuration(3000);
        oaForad.setRepeatCount(ObjectAnimator.INFINITE);
        oaForad.setRepeatMode(ObjectAnimator.REVERSE);

//        oaForad.setInterpolator(ObjectAnimator.);

        AnimatorSet as=new AnimatorSet();//決定前面寫到的動畫效困是用那一種動畫,先後順序
        as.playSequentially(oaRotate,oaForad);//先旋轉再平移
        as.start();
    }

//        <!--android:interpolator="@android:anim/fade_in"  淡入,淡山…-->
//    <!--android:interpolator="@android:anim/decelerate_interpolator"  先加速加減-->

    public void onClickAlpha(View target) {
        System.out.println("動畫效果");
        Intent intent=new Intent();
        intent.setClass(Bmi.this,AlphaActivity.class);
        startActivity(intent);
//        playViewAnimation();
    }

    //open new windows
    public void openAllTool(View target){
        System.out.println("openAllTool");
        Intent intent=new Intent();
        intent.setClass(  Bmi.this,AllTool.class);
        startActivity(intent);
    }

//    public void onClickDrawableAnim(View target){
//
//    }
}

