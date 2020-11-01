package com.example.firebasedemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CustomAdapter3 extends RecyclerView.Adapter<CustomAdapter3.MyViewHolder> {
    Context context;
    private List<StudentSemester> studentSemesterList;
    String name;
    String department;

    public CustomAdapter3(Context context, List<StudentSemester> studentSemesterList,String name, String department) {
        this.context = context;
        this.studentSemesterList = studentSemesterList;
        this.name=name;
        this.department=department;
    }

    @NonNull
    @Override
    public CustomAdapter3.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater= LayoutInflater.from(context);
        View view= layoutInflater.inflate(R.layout.all_details_sample,parent,false);
        return new CustomAdapter3.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter3.MyViewHolder holder, int position) {
        holder.semesterTextView.setText("Semester: "+studentSemesterList.get(position).getSemester());
        holder.cgpaTextView.setText("CGPA: "+studentSemesterList.get(position).getCgpa());
        holder.nameTextView.setText("Name: "+name);
        holder.departmentTextView.setText("Dept : "+department);
    }

    @Override
    public int getItemCount() {
        return studentSemesterList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView,departmentTextView,semesterTextView,cgpaTextView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView=itemView.findViewById(R.id.nameTextViewId);
            departmentTextView=itemView.findViewById(R.id.departmentTextViewId);
            semesterTextView=itemView.findViewById(R.id.semesterTextViewId);
            cgpaTextView=itemView.findViewById(R.id.cgpaTextViewId);
        }
    }
}
