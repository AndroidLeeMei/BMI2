package com.demo.android.bmi;
//
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//
//public class OptionMenuActivity extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_option_menu);
//    }
//}

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class OptionMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option_menu);

    }
    //使用xml 方式建立menu
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.righttopmenu,menu);

        //方法可以混搭,除了xml內容之外的,還可動態加入menu內容
        //items.length是在strings已經有設定好
        init();
        for (int i=0;i<items.length;i++) {
            //menu.add(0,200,0,"結束");
            menu.add(0, (i + 1) * 100, 0, items[i]);
//            menu.add(0, (i + 1) * 100, 0, items[i]).setIcon(iconRes[i]);//加入圖片失敗
            //在2.0之後要加入setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM)才會顯示圖片
            //可加入部份圖檔
//            menu.add(0, (i + 1) * 100, 0, items[i]).setIcon(iconRes[i]).setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
//            System.out.println("items[i]=" + items[i]);

            //一定會顯示圖檔,在上方工具列
            menu.add(0, (i + 1) * 100, 0, items[i]).setIcon(iconRes[i]).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        }
        return super.onCreateOptionsMenu(menu);
    }


    String[] items;
    int[] iconRes;
    //取得圖片陣列資料
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


    //由xml建立選單時,需要用R.id.item1的方式取得
    public boolean onOptionsItemSelected1(MenuItem item) {
        switch(item.getItemId()){
            case R.id.item1:
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).show();

            case R.id.item2:
            case R.id.item3:
            case R.id.item4:
            case R.id.item5:
            case R.id.item6:
            default:
                // Toast.makeText(Bmi.this,item.getTitle(),Toast.LENGTH_LONG).show();
                Toast.makeText(this,item.getTitle(),Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
