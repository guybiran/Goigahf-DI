package com.example.omriakerman.goigahf2;

import android.support.v7.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.Calendar;

public class LessonDetailsActivity extends AppCompatActivity {
    Integer lessonId;
    Lesson lesson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_details);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        lessonId = intent.getIntExtra("lesson_id", 0);
        lesson = Database.getLessonById(lessonId);

        TextView date = (TextView) findViewById(R.id.date_textView);
        date.setText(String.valueOf(lesson.getLessonDay())
                + "/" + String.valueOf(lesson.getLessonMonth())
                + "/" + String.valueOf(lesson.getLessonYear()));

        TextView startTime = (TextView) findViewById(R.id.startTime_textView);

        int h, m;
        Calendar c = Calendar.getInstance();
        c.setTime(lesson.getStartTimeDate());
        h = c.get(Calendar.HOUR_OF_DAY);
        m = c.get(Calendar.MINUTE);
        startTime.setText(String.valueOf(h) + ":" + String.valueOf(m));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(), HomeScreenActivity.class);
        startActivityForResult(myIntent, 0);
        return true;

    }

}
