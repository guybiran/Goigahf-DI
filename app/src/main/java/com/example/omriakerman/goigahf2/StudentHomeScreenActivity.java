package com.example.omriakerman.goigahf2;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Scroller;
import android.widget.Toast;

import java.lang.reflect.Field;

public class StudentHomeScreenActivity extends AppCompatActivity {

    private Toolbar myToolbar;
    private BottomNavigationView navigation;
    private ViewPager viewPager;
    private Fragment containerFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_home_screen);

        myToolbar = findViewById(R.id.toolbar);
        myToolbar.setTitle("Student");
        setSupportActionBar(myToolbar);

        navigation = findViewById(R.id.navigation);
        disableShiftMode(navigation);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;
                switch (item.getItemId()) {
                    case R.id.chat_fragment:
                        selectedFragment = new ChatFragment();
                        break;
                    case R.id.profile_fragment:
                        selectedFragment = new StudentProfileFragment();
                        break;
                    case R.id.calendar_fragment:
                        selectedFragment = new StudentCalendarFragment();
                        break;
                    case R.id.history_fragment:
                        selectedFragment = new StudentHistoryFragment();
                        break;
                }

                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout, selectedFragment);
                transaction.commit();
                return true;
            }
        });

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, new StudentCalendarFragment());
        transaction.commit();

        navigation.getMenu().getItem(2).setChecked(true);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.student_home_screen, menu); //this is the default toolbar menu

        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu){
        super.onPrepareOptionsMenu(menu);

        int selectedItemId = navigation.getSelectedItemId();

        if(selectedItemId == navigation.getMenu().getItem(3).getItemId()){
            //fetch toolbar menu for history tab
        } else if(selectedItemId == navigation.getMenu().getItem(2).getItemId()) {
            //fetch toolbar menu for calendar tab
        } else if(selectedItemId == navigation.getMenu().getItem(1).getItemId()){
            //fetch toolbar menu for profile tab
        } else if(selectedItemId == navigation.getMenu().getItem(0).getItemId()){
            //fetch toolbar menu for chat tab
        } else {

        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.action_logout){
            StartingActivity.connectedAs=-1;
            startActivity(new Intent(this, StartingActivity.class));
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private Boolean exit = false;
    @Override
    public void onBackPressed() {
        if (exit) {
            finish(); // finish activity
        } else {
            Toast.makeText(this, "Press Back again to Exit.",
                    Toast.LENGTH_SHORT).show();
            exit = true;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    exit = false;
                }
            }, 3 * 1000);
        }
    }

    @SuppressLint("RestrictedApi")
    private void disableShiftMode(BottomNavigationView view) {
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) view.getChildAt(0);
        try {
            Field shiftingMode = menuView.getClass().getDeclaredField("mShiftingMode");
            shiftingMode.setAccessible(true);
            shiftingMode.setBoolean(menuView, false);
            shiftingMode.setAccessible(false);
            for (int i = 0; i < menuView.getChildCount(); i++) {
                BottomNavigationItemView item = (BottomNavigationItemView) menuView.getChildAt(i);
                //item.setShiftingMode(false);
                // set once again checked value, so view will be updated
                item.setChecked(item.getItemData().isChecked());
            }
        } catch (NoSuchFieldException e) {
            Log.e("BNVHelper", "Unable to get shift mode field", e);
        } catch (IllegalAccessException e) {
            Log.e("BNVHelper", "Unable to change value of shift mode", e);
        }
    }



}
