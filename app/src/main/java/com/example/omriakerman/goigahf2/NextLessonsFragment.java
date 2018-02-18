package com.example.omriakerman.goigahf2;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import static com.example.omriakerman.goigahf2.InstructorHomeScreenActivity.db;

/**
 * Created by user on 09/10/2017.
 */

public class NextLessonsFragment extends android.support.v4.app.Fragment {

    private TodayScheduleAdapter myAdapter;
    private ArrayList<Lesson> todayLessons = new ArrayList<>();
    private ListView lessonsListView;
    int y,m,d;

    Timer timer = new Timer();
    TimerTask timerTask = new TimerTask() {
        @Override
        public void run() {
            updateList(getActivity(), myAdapter);
        }
    };

    public NextLessonsFragment(){}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Calendar cal = Calendar.getInstance();
        y = cal.get(Calendar.YEAR);
        m = cal.get(Calendar.MONTH)+1;
        d = cal.get(Calendar.DATE);

        timer.scheduleAtFixedRate(timerTask, 10, 4*1000);

        todayLessons.addAll(db.getAllLessonsInDate(y, m, d));
        myAdapter = new TodayScheduleAdapter(getContext(), R.layout.today_schedule_list_item, todayLessons);

        super.onCreate(savedInstanceState);
    }

    @Override
    public void onPause(){
        timer.cancel();
        super.onPause();
    }

    @Override
    public void onResume(){
        int i, offFromTop=0;
        View prevLesson;
        Lesson lesson;
        Date currentTime = Calendar.getInstance().getTime();

        myAdapter.notifyDataSetChanged();
        for(i=0; i<todayLessons.size()-1; i++){
            lesson = todayLessons.get(i);
            if (currentTime.after(lesson.getEndTimeDate())) {
                continue;
            } else if(currentTime.after(lesson.getStartTimeDate()) && currentTime.before(lesson.getEndTimeDate())){
                Log.e("CONTROL2", "POS - " + String.valueOf(i));
                break;
            } else {
                Log.e("CONTROL2", "POS - " + String.valueOf(i));
                break;
            }
        }

        if(i!=0){
            View view  = myAdapter.getView(i, null, null);
            view.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                    View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
            offFromTop = view.getMeasuredHeight();
        }
        lessonsListView.setSelectionFromTop(i,offFromTop);
        super.onResume();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_next_lessons, container, false);

        lessonsListView = (ListView) rootView.findViewById(R.id.lessons_list);

        lessonsListView.setAdapter(myAdapter);
        lessonsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Lesson lesson = todayLessons.get(position);
                Intent intent = new Intent(getActivity(), LessonDetailsActivity.class);
                intent.putExtra("lesson_id", lesson.getLessonId());
                startActivity(intent);
            }
        });

        lessonsListView.setDivider(new ColorDrawable(Color.TRANSPARENT));
        lessonsListView.setDividerHeight(10);

        return rootView;
    }

    public static Thread performOnBackgroundThread(final Runnable runnable) {
        final Thread t = new Thread() {
            @Override
            public void run() {
                try {
                    runnable.run();
                } finally {

                }
            }
        };
        t.start();
        return t;
    }

    public static void updateList(Activity act, final ArrayAdapter<Lesson> aa)
    {
        act.runOnUiThread(new Runnable()
        {
            public void run()
            {
                aa.notifyDataSetChanged();
            }

        });
    }

}

//    Timer timer = new Timer();
//    TimerTask timerTask = new TimerTask() {
//        @Override
//        public void run() {
//            myAdapter.notifyDataSetChanged();
//            Toast.makeText(getContext(), "List refreshed!", Toast.LENGTH_SHORT).show();
//        }
//    };
//        timer.schedule(timerTask, 5*1000);