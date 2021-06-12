package com.inhatc.secret;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    private ImageView ivMenu;
    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private ImageView profileImg;
    private BottomNavigationView bottomNavigationView;
    private TextView txtDrawerNick;
    private NavigationView navigation;
    private View nav_header_view;

    // FrameLayout에 각 메뉴의 Fragment를 바꿔 줌
    private FragmentManager fragmentManager = getSupportFragmentManager();
    // 4개의 메뉴에 들어갈 Fragment들
    private Fragment1 menu1 = new Fragment1();
    private Fragment2 menu2 = new Fragment2();
    private FreeBoardFrgment freeBoard = new FreeBoardFrgment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        ivMenu=findViewById(R.id.iv_menu);
        drawerLayout=findViewById(R.id.drawer_main);
        toolbar=findViewById(R.id.toolbar);

        navigation = (NavigationView)findViewById(R.id.navigation);
        nav_header_view = navigation.getHeaderView(0);

        txtDrawerNick = (TextView)nav_header_view.findViewById(R.id.txtDrawerNick);
        txtDrawerNick.setText(intent.getStringExtra("nickname"));
        ivMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(Gravity.LEFT);
            }
        });
        //액션바 변경하기(들어갈 수 있는 타입 : Toolbar type
        setSupportActionBar(toolbar);


        // 첫 화면 지정
        Bundle menu1Bundle = new Bundle();
        menu1Bundle.putString("nickname", getIntent().getStringExtra("nickname"));
        menu1Bundle.putString("school", getIntent().getStringExtra("school"));
        menu1.setArguments(menu1Bundle);
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frame_layout, menu1).commitAllowingStateLoss();

        bottomNavigationView = findViewById(R.id.bottom_navigation_view);
        // bottomNavigationView의 아이템이 선택될 때 호출될 리스너 등록
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                switch (item.getItemId()) {
                    case R.id.navigation_home: {
                        Bundle menu1Bundle = new Bundle();
                        menu1Bundle.putString("nickname", getIntent().getStringExtra("nickname"));
                        menu1Bundle.putString("school", getIntent().getStringExtra("school"));
                        menu1.setArguments(menu1Bundle);
                        transaction.replace(R.id.frame_layout, menu1).commitAllowingStateLoss();
                        break;
                    }
                    case R.id.navigation_community: {
                        Bundle menu2Bundle = new Bundle();
                        menu2Bundle.putString("nickname", getIntent().getStringExtra("nickname"));
                        menu2Bundle.putString("school", getIntent().getStringExtra("school"));
                        menu2.setArguments(menu2Bundle);
                        transaction.replace(R.id.frame_layout, menu2).commitAllowingStateLoss();
                        break;
                    }
                    case R.id.navigation_calendar: {
                        //transaction.replace(R.id.frame_layout, menu2).commitAllowingStateLoss();
                        break;
                    }
                    case R.id.navigation_setting: {
                        //transaction.replace(R.id.frame_layout, menu2).commitAllowingStateLoss();
                        break;
                    }
                }

                return true;
            }
        });
    }

    public void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment).commit();      // Fragment로 사용할 MainActivity내의 layout공간을 선택합니다.
    }

    public void changeNav(){
        bottomNavigationView.getMenu().getItem(1).setChecked(true);
    }
}