package com.demo.android.bmi;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import android.content.DialogInterface;
import android.widget.Toast;

public class AllTool extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_tool);
    }


    public void openOptionsDialogNomessage(View target){
        //AlertDialog.Builder(Context),其中Context是AppCompatActivity的父類別
        //若Dialog裏無內容,則compilier ok,但不會顯示對話框
        AlertDialog.Builder builer=new AlertDialog.Builder(this);
        builer.show();
    }

    public void openOptionsDialog(View target){
        new AlertDialog.Builder(AllTool.this)
                .setTitle("Title=關於")
                .setMessage("Message=計算機").show();
    }

    public void openOptionsDialogOK(View target){
        new AlertDialog.Builder(AllTool.this)
                .setTitle(R.string.dialog_title)
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //
                            }
                        }
                ).show();
    }

    public void openOptionsDialogListener(View target){
        //setPositiveButton,setNegativeButton,setNeutralButton三種只能各使用一次,以int which來取得按下那種
        //若有使用相同的多次,無效,compilier ok
        new AlertDialog.Builder(AllTool.this)
                .setTitle(R.string.dialog_title)
                .setMessage(R.string.dialog_message)
                .setPositiveButton("確認",dialogListener)
                .setNegativeButton("首頁",dialogListener)
                .setNeutralButton("取消",dialogListener)
                .show();
    }

    //user system's dialog,get the use options
    DialogInterface.OnClickListener dialogListener=new DialogInterface.OnClickListener(){
        @Override
        public void onClick(DialogInterface dialog, int which) {
            if (which==DialogInterface.BUTTON_POSITIVE)
                Toast.makeText(AllTool.this,"剛才按下確認",Toast.LENGTH_SHORT).show();
            else if (which==DialogInterface.BUTTON_NEGATIVE)
                Toast.makeText(AllTool.this,"剛才按下首頁",Toast.LENGTH_SHORT).show();
            else if (which==DialogInterface.BUTTON_NEUTRAL)
                Toast.makeText(AllTool.this,"剛才按下取消",Toast.LENGTH_SHORT).show();
        }
    };
    //openDialogMethod end


    public void DialogListenerALL(View target){
        AlertDialog.Builder builer=new AlertDialog.Builder(this);
        builer.setTitle(R.string.dialog_title);
        builer.setMessage(R.string.dialog_message);
        builer.setNeutralButton("首頁",dialog_listener);
        builer.show();
    }

    DialogInterface.OnClickListener dialog_listener=new DialogInterface.OnClickListener(){
        @Override
        public void onClick(DialogInterface dialog,int which) {
//            Uri uri= Uri.parse("geo:24.930739,121.172424");
            Uri mapUri = Uri.parse("geo:38.899533,-77.036476");
            Intent intent = new Intent(Intent.ACTION_VIEW, mapUri);
            startActivity(intent);

//                Uri uri= Uri.parse("https://sites.google.com./site/gasodroid/");
//                Intent intent = new Intent(Intent.ACTION_VIEW,uri);
//            startActivity(intent);
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

//            //打電話,會有權限問題
//            Uri callUri = Uri.parse("tel:100861");
//            Intent intent = new Intent(Intent.ACTION_CALL, callUri);
        }
    };

    //System's processDialog begin,it will close by self
    public void openProcessDialog(View target){
        final ProgressDialog progressDialog= ProgressDialog.show(this,"處理","請等一會,處理完畢會自動結束");
        Thread thread=new Thread(){
            @Override
            public void run(){
                try{
                    Thread.sleep(5000);//0.5s
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
                progressDialog.dismiss();  //cancel the prcessDialog
            }
        };
        thread.start();
    }

    public void openDefineDialog(View target){
        Intent intent=new Intent();
        intent.setClass(AllTool.this,DefineDialogActivity.class);
        startActivity(intent);
    }

    public void openToast(View target){
        //use Constant value Toast.LENGTH_SHORT   Toast.LENGTH_LONG
        Toast.makeText(this,"BMI計算器",Toast.LENGTH_SHORT).show();
    }

    //開啟在右上角有選單的新視窗
    public void openOptionMenu(View target){
        Intent intent=new Intent();
        intent.setClass(AllTool.this,OptionMenuActivity.class);
        startActivity(intent);
    }

    //在中間出現選單,但是要按久一點
    public void openContextMenu(View target){
        Intent intent=new Intent();
        intent.setClass(AllTool.this,ContextActivity.class);
        startActivity(intent);

    }

    public void openAnimation(View target){
        Intent intent=new Intent();
        intent.setClass(AllTool.this,AnimationActivity.class);
        startActivity(intent);
    }

    //open new windows
    public void openGameOX(View target){
        Intent intent=new Intent();
        intent.setClass(AllTool.this,GameOX.class);
        startActivity(intent);
    }

    public void openCaculator(View target){
        Intent intent=new Intent();
        intent.setClass(AllTool.this,CalculatorActivity.class);
        startActivity(intent);
    }

    public void openGasPoint(View target){
        Intent intent=new Intent();
        intent.setClass(AllTool.this,GasPointActivity.class);
        startActivity(intent);
    }
}

