package com.example.omriakerman.goigahf2;

import android.content.Intent;
import android.graphics.Color;
import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class InstructorHomeScreenActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private TabLayout tabLayout;
    private DateFormat df = new SimpleDateFormat("EEE - d MMM, yyyy");
    private String timeTitle = df.format(Calendar.getInstance().getTime());
    private TabsPagerAdapter mTabsPagerAdapter;
    private ViewPager mViewPager;
    public static Database db = new Database();
    static int pos=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(timeTitle);
        setSupportActionBar(toolbar);

        mTabsPagerAdapter = new TabsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mTabsPagerAdapter);
        mViewPager.setCurrentItem(1);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
        tabLayout.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);

        //createCustomTabs();
        tabLayout.getTabAt(0).setIcon(R.drawable.icon_mail);
        tabLayout.getTabAt(0).setText("דואר נכנס");
        tabLayout.getTabAt(1).setIcon(R.drawable.icon_today_schedule_marked);
        tabLayout.getTabAt(1).setText("שיעורים הבאים");
        tabLayout.getTabAt(2).setIcon(R.drawable.icon_chat);
        tabLayout.getTabAt(2).setText("צ'אט");

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                TabLayout.Tab tab = tabLayout.getTabAt(position);
                switch (position){
                    case 0:
                        tab.setIcon(R.drawable.icon_mail_marked);
                        tabLayout.getTabAt(1).setIcon(R.drawable.icon_today_schedule);
                        tabLayout.getTabAt(2).setIcon(R.drawable.icon_chat);
                        break;
                    case 1:
                        tab.setIcon(R.drawable.icon_today_schedule_marked);
                        tabLayout.getTabAt(0).setIcon(R.drawable.icon_mail);
                        tabLayout.getTabAt(2).setIcon(R.drawable.icon_chat);
                        break;
                    case 2:
                        tab.setIcon(R.drawable.icon_chat_marked);
                        tabLayout.getTabAt(0).setIcon(R.drawable.icon_mail);
                        tabLayout.getTabAt(1).setIcon(R.drawable.icon_today_schedule);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


    }

    private Boolean exit = false;
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
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
    }

    @Override
    public void onResume(){
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        super.onResume();
        mViewPager.setCurrentItem(pos);

        super.onResume();
        for (int i = 0; i < navigationView.getMenu().size(); i++) {
            navigationView.getMenu().getItem(i).setChecked(false);
        }
    }

    @Override
    public void onPause(){
        super.onPause();
        pos = tabLayout.getSelectedTabPosition();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home_screen, menu);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle student_home_screen_navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_calendar) {

        } else if (id == R.id.nav_students) {
            startActivity(new Intent(this, StudentsListActivity.class));
        } else if (id == R.id.nav_payments) {

        } else if (id == R.id.nav_profile) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public class TabsPagerAdapter extends FragmentPagerAdapter {

        public TabsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    return new PendingRequestsFragment();
                case 1:
                    return new NextLessonsFragment();
                case 2:
                    return new ChatFragment();
                default:
                    return new NextLessonsFragment();
            }
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }
    }

}
