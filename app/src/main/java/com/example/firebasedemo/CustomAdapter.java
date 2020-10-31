package com.example.firebasedemo;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<Student> {
    private Activity context;
    private List<Student> studentsList;

    public CustomAdapter(@NonNull Activity context, List<Student> studentsList) {
        super(context, R.layout.sample_layout, studentsList);
        this.context = context;
        this.studentsList = studentsList;
    }

    @NonNull
    @Override
    public View getView(int position,View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater=context.getLayoutInflater();
        View view=layoutInflater.inflate(R.layout.sample_layout,null,true);
      Student student=studentsList.get(position);
        TextView nameTextVew=view.findViewById(R.id.nameTextViewId);
        TextView departmentTextView=view.findViewById(R.id.departmentTextViewId);
        nameTextVew.setText(student.getName());
        departmentTextView.setText(student.getDepartment());
        return view;
    }
}
