package com.example.firebasedemo;

import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class CustomAdapter2 extends RecyclerView.Adapter<CustomAdapter2.MyViewHolder> {
    Context context;
    private List<Student> studentList;
    private static DatabaseReference mDatabase;
    public CustomAdapter2(Context context, List<Student> studentList) {
        this.context = context;
        this.studentList = studentList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater= LayoutInflater.from(context);
        View view= layoutInflater.inflate(R.layout.sample_layout,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.nameTextView.setText("Name: "+studentList.get(position).getName());
        holder.departmentTextView.setText("Dept: "+studentList.get(position).getDepartment());
    holder.itemView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(context, "clicked", Toast.LENGTH_SHORT).show();
            mDatabase= FirebaseDatabase.getInstance().getReference("student");

               //mDatabase.child(studentList.get(position).getId()).setValue(null);

                // update data by id
               mDatabase.child(studentList.get(position).getId()).setValue(new Student(studentList.get(position).getId(),studentList.get(position).getName(),"Bolbona"));

              // mDatabase.child(studentList.get(position).getId()).removeValue();


        }
    });
    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView,departmentTextView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView=itemView.findViewById(R.id.nameTextViewId);
            departmentTextView=itemView.findViewById(R.id.departmentTextViewId);
        }
    }
}
