package com.example.firebasedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddSemesterActivity extends AppCompatActivity {
    TextView studentNameTextView;
    Button  saveButton;
    Spinner semesterSpinner;
    EditText cgpaEditText;
    String name, id;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_semester);
        studentNameTextView=findViewById(R.id.nameTextViewId);
        saveButton=findViewById(R.id.saveButtonId);
        semesterSpinner=findViewById(R.id.semesterSpinnerId);
        cgpaEditText=findViewById(R.id.cgpaEditTextId);

        id = getIntent().getStringExtra("id");
        name=getIntent().getStringExtra("name");
        studentNameTextView.setText(name);

        databaseReference= FirebaseDatabase.getInstance().getReference("Semester").child(id);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveSemester();
            }
        });



    }

    private void saveSemester() {
        String semester=semesterSpinner.getSelectedItem().toString();
        String cgpa=cgpaEditText.getText().toString();
        if (!TextUtils.isEmpty(cgpa)){
            String id=databaseReference.push().getKey();
            StudentSemester studentSemester=new StudentSemester(id,semester,cgpa);
            databaseReference.child(id).setValue(studentSemester);
        }
        else {
            cgpaEditText.setError("Enter a value");
        }

    }
}