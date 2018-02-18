package com.example.omriakerman.goigahf2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StartingActivity extends AppCompatActivity {
    public static int connectedAs = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starting);

        switch (connectedAs){
            case -1:
                break;
            case 0:
                startActivity(new Intent(getApplicationContext(), StudentHomeScreenActivity.class));
                finish();
                break;
            case 1:
                startActivity(new Intent(getApplicationContext(), InstructorHomeScreenActivity.class));
                finish();
                break;
        }
    }

    public void startInstructor(View v){
        connectedAs=1;
        startActivity(new Intent(getApplicationContext(), InstructorHomeScreenActivity.class));
        finish();
    }

    public void startStudent(View v){
        connectedAs=0;
        startActivity(new Intent(getApplicationContext(), StudentHomeScreenActivity.class));
        finish();
    }
}
