package com.example.firebasedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    EditText nameEditText;
    Button saveButton;
    Spinner departmentSpinner;
    DatabaseReference databaseStudent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        databaseStudent= FirebaseDatabase.getInstance().getReference("student");
        
        nameEditText=findViewById(R.id.nameEditTextId);
        saveButton=findViewById(R.id.saveButtonId);
        departmentSpinner=findViewById(R.id.departmentSpinnerId);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addStudentInfo();
            }
        });
    }

    private void addStudentInfo() {
        String name=nameEditText.getText().toString();
        String department= departmentSpinner.getSelectedItem().toString();
        if (!TextUtils.isEmpty(name)){
        String id=databaseStudent.push().getKey();
        Student student=new Student(id,name,department);
        databaseStudent.child(id).setValue(student);
            Toast.makeText(this, "insert success", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "name is empty", Toast.LENGTH_SHORT).show();
        }
    }
}