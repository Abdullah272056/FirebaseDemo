package com.example.firebasedemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DetailsActivity2 extends AppCompatActivity {
   RecyclerView recyclerView;
    CustomAdapter2 customAdapter2;
    DatabaseReference databaseReference;
    List<Student> studentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details2);
        recyclerView=findViewById(R.id.recyclerViewId);

        databaseReference= FirebaseDatabase.getInstance().getReference("student");
        studentList=new ArrayList<>();
        customAdapter2=new CustomAdapter2(DetailsActivity2.this,studentList);

        recyclerView.setLayoutManager(new LinearLayoutManager(DetailsActivity2.this));
    }
    @Override
    protected void onStart() {

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                studentList.clear();
                for (DataSnapshot studentSnapshot:snapshot.getChildren()){
                    Student student=studentSnapshot.getValue(Student.class);
                    studentList.add(student);
                    //Toast.makeText(MainActivity.this, String.valueOf(studentList.get(1).getName()), Toast.LENGTH_SHORT).show();
                }
                recyclerView.setAdapter(customAdapter2);
                //listView.setAdapter(customAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        super.onStart();
    }


}