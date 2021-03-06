package com.example.firebasedemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AddSemesterActivity extends AppCompatActivity {
    TextView studentNameTextView;
    Button  saveButton;
    Spinner semesterSpinner;
    EditText cgpaEditText;
    String name, id,dept;
    DatabaseReference databaseReference;


    RecyclerView recyclerView;
    CustomAdapter3 customAdapter3;
    List<StudentSemester> studentSemesterList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_semester);
        studentNameTextView=findViewById(R.id.nameTextViewId);
        saveButton=findViewById(R.id.saveButtonId);
        semesterSpinner=findViewById(R.id.semesterSpinnerId);
        cgpaEditText=findViewById(R.id.cgpaEditTextId);
        recyclerView=findViewById(R.id.recyclerViewId);

        id = getIntent().getStringExtra("id");
        name=getIntent().getStringExtra("name");
        dept=getIntent().getStringExtra("department");
        studentNameTextView.setText(name);

        // dataBase access with id
        databaseReference= FirebaseDatabase.getInstance().getReference("Semester").child(id);

        studentSemesterList=new ArrayList<>();
        customAdapter3=new CustomAdapter3(AddSemesterActivity.this,studentSemesterList,name,dept);
        recyclerView.setLayoutManager(new LinearLayoutManager(AddSemesterActivity.this));


        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveSemester();
            }
        });



    }

    @Override
    protected void onStart() {

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                studentSemesterList.clear();
                for (DataSnapshot studentSnapshot:snapshot.getChildren()){
                    StudentSemester studentSemester=studentSnapshot.getValue(StudentSemester.class);
                    studentSemesterList.add(studentSemester);
                    Toast.makeText(AddSemesterActivity.this, String.valueOf(studentSemesterList.size()), Toast.LENGTH_SHORT).show();
                }
                recyclerView.setAdapter(customAdapter3);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        super.onStart();
    }


    private void saveSemester() {
        String semester=semesterSpinner.getSelectedItem().toString();
        String cgpa=cgpaEditText.getText().toString();
        if (!TextUtils.isEmpty(cgpa)){
            String id=databaseReference.push().getKey();
            StudentSemester studentSemester=new StudentSemester(id,semester,cgpa);
            // set data 
            databaseReference.child(id).setValue(studentSemester);
        }
        else {
            cgpaEditText.setError("Enter a value");
        }

    }
}