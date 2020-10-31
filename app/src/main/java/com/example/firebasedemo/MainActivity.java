package com.example.firebasedemo;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelStore;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText nameEditText;
    Button saveButton,detailsBtn,detailsBtn2;
    Spinner departmentSpinner;
    DatabaseReference databaseReference;
    ListView listView;
    List<Student> studentList;
    CustomAdapter customAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        databaseReference= FirebaseDatabase.getInstance().getReference("student");
        nameEditText=findViewById(R.id.nameEditTextId);
        saveButton=findViewById(R.id.saveButtonId);
        detailsBtn=findViewById(R.id.detailsBtnId);
        detailsBtn2=findViewById(R.id.detailsBtnId2);

        studentList=new ArrayList<>();
        customAdapter=new CustomAdapter(MainActivity.this,studentList);
        listView=findViewById(R.id.mainPageListViewId);

        departmentSpinner=findViewById(R.id.departmentSpinnerId);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addStudentInfo();
            }
        });
        detailsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,DetailsActivity.class);
                startActivity(intent);
            }
        });
        detailsBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,DetailsActivity2.class);
                startActivity(intent);
            }
        });
    }


    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                studentList.clear();
                for (DataSnapshot studentSnapshot:snapshot.getChildren()){
                    Student student=studentSnapshot.getValue(Student.class);
                    studentList.add(student);
                }
                listView.setAdapter(customAdapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void addStudentInfo() {
        String name=nameEditText.getText().toString();
        String department= departmentSpinner.getSelectedItem().toString();
        if (!TextUtils.isEmpty(name)){
        String id=databaseReference.push().getKey();
        Student student=new Student(id,name,department);
            databaseReference.child(id).setValue(student);
            Toast.makeText(this, "insert success", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "name is empty", Toast.LENGTH_SHORT).show();
        }
    }
}