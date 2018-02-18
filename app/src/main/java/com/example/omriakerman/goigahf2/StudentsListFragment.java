package com.example.omriakerman.goigahf2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by lg199 on 12/6/2017.
 */

public class StudentsListFragment extends Fragment {

    private StudentsListAdapter myAdapter;
    private ArrayList<Student> myStudents = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View rootView = inflater.inflate(R.layout.fragment_students_list, container, false);

        myStudents.addAll(Database.getAllStudents());

        myAdapter = new StudentsListAdapter(getContext(), R.layout.student_list_item, myStudents);

        ListView studentsListView = (ListView) rootView.findViewById(R.id.students_listView);
        studentsListView.setAdapter(myAdapter);
        studentsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Student student = myStudents.get(position);
                Intent intent = new Intent(getActivity(), StudentWallActivity.class);
                intent.putExtra("student_id", student.getIdNumber());
                startActivity(intent);
            }
        });

        return rootView;
    }
}
