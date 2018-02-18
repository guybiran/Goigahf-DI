package com.example.omriakerman.goigahf2;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class StudentWallActivity extends AppCompatActivity {

    String studentId;
    Student student;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        studentId = getIntent().getStringExtra("student_id");
        student = Database.getStudentById(studentId);

        setContentView(R.layout.activity_student_wall);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        FloatingActionButton callFab = (FloatingActionButton) findViewById(R.id.call_fab);
        callFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Calling " + student.getName(), Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        FloatingActionButton chatFab = (FloatingActionButton) findViewById(R.id.chat_fab);
        chatFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Opening chat with " + student.getName(), Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        FloatingActionButton calendarFab = (FloatingActionButton) findViewById(R.id.calendar_fab);
        calendarFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Showing " + student.getName() + "'s lessons", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TextView name = (TextView) findViewById(R.id.name_textView);
        TextView license = (TextView) findViewById(R.id.license_textView);
        TextView titleVehicle = (TextView) findViewById(R.id.title_vehicle_textView);
        TextView titlePhoneNum = (TextView) findViewById(R.id.title_phone_textView);
        TextView vehicle = (TextView) findViewById(R.id.vehicle_textView);
        TextView phoneNumber = (TextView) findViewById(R.id.phone_number_textView);
        TextView dateJoined = (TextView) findViewById(R.id.date_joined_textView);

        if(name!=null){ name.setText(student.getName()); }
        if(license!=null){ license.setText("רשיון: " + student.getVehicle().getLicense()); }
        if(titleVehicle!=null){ titleVehicle.setText("רכב: "); }
        if(titlePhoneNum!=null){ titlePhoneNum.setText("מספר טלפון: " ); }
        if(vehicle!=null){ vehicle.setText(student.getVehicle().getName()); }
        if(phoneNumber!=null){ phoneNumber.setText(student.getPhoneNumber()); }
    }
}
