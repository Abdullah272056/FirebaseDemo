package com.example.firebasedemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DetailsActivity extends AppCompatActivity {
    DatabaseReference databaseReference;
    List<Student> studentList;
    ListView listView;
    CustomAdapter customAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        databaseReference= FirebaseDatabase.getInstance().getReference("student");
        studentList=new ArrayList<>();
        customAdapter=new CustomAdapter(DetailsActivity.this,studentList);
        listView=findViewById(R.id.listViewId);


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
               listView.setAdapter(customAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        super.onStart();
    }
}