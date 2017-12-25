package com.demo.android.bmi;
//
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//
//public class ContextActivity extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_context);
//    }
//}


import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

public class ContextActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_context);
        //註冊ContextMenu元件,registerForContextMenu()
        TextView tv=(TextView)findViewById(R.id.textView);
        registerForContextMenu(tv);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        //在@Override之後新增這一行,中間可出現選單???
        getMenuInflater().inflate(R.menu.middlemenu, menu);  //與onCreateOptionsMenu作法相同

        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override//按中間時要按久一點
    public boolean onContextItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()){
            case R.id.action_settings:
                customToast2All(R.string.action_meowth,R.drawable.meowth);
                break;
            case R.id.item1:
                customToast2All(R.string.action_pikachu,R.drawable.pikachu);
                break;
            case R.id.item2:
                customToast2All(R.string.action_pokeball,R.drawable.pokeball);
                break;
            case R.id.item3:
                customToast2All(R.string.action_psyduck,R.drawable.psyduck);
                break;
            case R.id.item4:
                customToast2All(R.string.action_snorlax,R.drawable.snorlax);
                break;
            case R.id.item5:
                customToast2All(R.string.action_settings,R.drawable.venonat);
                break;
            case R.id.submtem1:
                customToast2All(R.string.action_settings,R.drawable.venonat);
                break;
            case R.id.submtem2:
                customToast2All(R.string.action_settings,R.drawable.venonat);
                break;
            case R.id.submtem3:
                customToast2All(R.string.action_settings,R.drawable.venonat);
                break;
            case R.id.submtem4:
                customToast2All(R.string.action_settings,R.drawable.venonat);
                break;
            case R.id.submtem5:
                customToast2All(R.string.action_settings,R.drawable.venonat);
                break;

            case R.id.action_browser:
                Uri myBlogUri = Uri.parse("https://sites.google.com./site/gasodroid/");
                intent = new Intent(Intent.ACTION_VIEW, myBlogUri);
                startActivity(intent);
                break;
            case R.id.action_map:
                Uri mapUri = Uri.parse("geo:38.899533,-77.036476");
                intent = new Intent(Intent.ACTION_VIEW, mapUri);
                startActivity(intent);
                break;
            case R.id.action_dial:
                Uri telUri = Uri.parse("tel:100861");
                intent = new Intent(Intent.ACTION_DIAL, telUri);
                startActivity(intent);
                break;
            case R.id.action_uninstall:
                Uri uninstallUri = Uri.fromParts("package", "xxx", null);
                intent = new Intent(Intent.ACTION_DELETE, uninstallUri);
                startActivity(intent);
                break;
            case R.id.action_install:
                Uri installUri = Uri.fromParts("package", "xxx", null);
                intent = new Intent(Intent.ACTION_PACKAGE_ADDED, installUri);
                startActivity(intent);
                break;
            case R.id.action_play_music:
                String baseDir = Environment.getExternalStorageDirectory().getAbsolutePath();
                intent = new Intent();
                intent.setAction(android.content.Intent.ACTION_VIEW);
                File file = new File(baseDir+"/music/bgm.mp3");
                intent.setDataAndType(Uri.fromFile(file), "audio/*");
                startActivity(intent);
                break;
            case R.id.action_call:
                Uri callUri = Uri.parse("tel:0972485245");
                intent = new Intent(Intent.ACTION_CALL, callUri);
//                startActivity(intent);
                break;
            case R.id.action_2nd_activity:
//                intent=new Intent();
//                intent.setClass(MainActivity.this,Report.class);
//                startActivity(intent);
                break;
            default:
                Toast.makeText(this,item.getTitle(),Toast.LENGTH_LONG).show();
                //   customToast();
        }
        return super.onContextItemSelected(item);
    }

    //讓Toast有圖片
    void customToast2All(int intNum,int intImg){
        Toast toast=Toast.makeText(this, intNum, Toast.LENGTH_SHORT);
        View original=toast.getView();
        LinearLayout linearLayout=new LinearLayout(this);//改用程式動態建立,所不用findviewbyid
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        ImageView img=new ImageView(this);
        img.setImageResource(intImg);
        linearLayout.addView(img);
        linearLayout.addView(original);
        toast.setView(linearLayout);
        toast.show();
    }

    void customToast(){
        Toast toast=Toast.makeText(this, R.string.action_settings, Toast.LENGTH_SHORT);
        View original=toast.getView();
        LinearLayout linearLayout=new LinearLayout(this);//改用程式動態建立,所不用findviewbyid
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        ImageView img=new ImageView(this);
        img.setImageResource(R.drawable.meowth);
        linearLayout.addView(img);
        linearLayout.addView(original);
        toast.setView(linearLayout);
        toast.show();
    }
}
