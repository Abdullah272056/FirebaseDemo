package com.example.firebasedemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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

        // firebase dataBase access
        databaseReference= FirebaseDatabase.getInstance().getReference("student");

        studentList=new ArrayList<>();
        customAdapter=new CustomAdapter(DetailsActivity.this,studentList);
        listView=findViewById(R.id.listViewId);
        listView.setOnItemClickListener(new ListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(DetailsActivity.this,AddSemesterActivity.class);
                intent.putExtra("name",studentList.get(position).getName());
                intent.putExtra("department",studentList.get(position).getDepartment());
                intent.putExtra("id",studentList.get(position).getId());
                startActivity(intent);
            }
        });


    }


    // onCreate method call then data load
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