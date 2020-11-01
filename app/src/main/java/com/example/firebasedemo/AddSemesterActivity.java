package com.example.firebasedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class AddSemesterActivity extends AppCompatActivity {
    TextView studentNameTextView;
    Button  saveButton;
    Spinner semesterSpinner;
    EditText cgpaEditText;
    String name, id;

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



    }
}