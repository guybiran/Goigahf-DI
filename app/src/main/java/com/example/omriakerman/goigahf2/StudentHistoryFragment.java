package com.example.omriakerman.goigahf2;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by lg199 on 3/3/2018.
 */

public class StudentHistoryFragment extends android.support.v4.app.Fragment {

    public StudentHistoryFragment(){}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_student_history, container, false);

        return rootView;
    }
}
