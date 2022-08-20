package com.example.predictcoin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    ListView listView;

    // 네이게이션 바
    MeowBottomNavigation bottomNavigation;

    // 뒤로가기 버튼 띄우기
    private long backPressedTime;


    @Override
    public void onBackPressed() {

        if(backPressedTime + 2000 > System.currentTimeMillis()) {
            super.onBackPressed();
            return;
        } else {
            Toast.makeText(getBaseContext(), "한번더 뒤로가기 버튼을 누르세요", Toast.LENGTH_SHORT).show();
        }
        backPressedTime = System.currentTimeMillis();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.list1);

        //Assign variable
        bottomNavigation = findViewById(R.id.bottom_navigation);

        //Add menu item
       bottomNavigation.add(new MeowBottomNavigation.Model(1, R.drawable.ic_baseline_notifications_24));
       bottomNavigation.add(new MeowBottomNavigation.Model(2, R.drawable.ic_baseline_monetization_on_24));
       bottomNavigation.add(new MeowBottomNavigation.Model(3, R.drawable.ic_baseline_web_24));

       bottomNavigation.setOnShowListener(new MeowBottomNavigation.ShowListener() {
           @Override
           public void onShowItem(MeowBottomNavigation.Model item) {
               //intialize Fragment
               Fragment fragment = null;

               //Check Conditon
               switch(item.getId()) {
                   case 1:
                       fragment = new Notification();
                       break;
                   case 2:
                       fragment = new Coin();
                       break;
                   case 3:
                       fragment = new Web();
                       break;
               }

               loadFragment(fragment);
           }
       });

//       //set notifiaction count
//        bottomNavigation.setCount(1, "10");
        //set home fragment itinially seledted
        bottomNavigation.show(2, true);

        bottomNavigation.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
            @Override
            public void onClickItem(MeowBottomNavigation.Model item) {
                //Display Toast
                Toast.makeText(getApplicationContext(), "선택된 페이지"+ item.getId(), Toast.LENGTH_SHORT).show();
            }
        });

        bottomNavigation.setOnReselectListener(new MeowBottomNavigation.ReselectListener() {
            @Override
            public void onReselectItem(MeowBottomNavigation.Model item) {
                //Display Toast
                Toast.makeText(getApplicationContext(), "선택된 페이지 " + item.getId(), Toast.LENGTH_SHORT).show();
            }
        });

        if(savedInstanceState == null)
        {
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,
                    new Notification()).commit();
        }
    }

    private void loadFragment(Fragment fragment) {
        //Replace Fragment
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_layout, fragment)
                .commit();
    }

    // 사이트
    public void onButton1Clicked(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://upbit.com/home"));
        startActivity(intent);
    }

    public void onButton2Clicked(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.bithumb.com/"));
        startActivity(intent);
    }

    public void onButton3Clicked(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://coinone.co.kr/"));
        startActivity(intent);
    }

    public void onButton4Clicked(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.korbit.co.kr/"));
        startActivity(intent);
    }

    public void onButton5Clicked(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://kr.coinness.com/"));
        startActivity(intent);
    }

    public void onButton6Clicked(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://kr.investing.com/crypto/"));
        startActivity(intent);
    }

    public void onButton7Clicked(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://zum.com/"));
        startActivity(intent);
    }

}