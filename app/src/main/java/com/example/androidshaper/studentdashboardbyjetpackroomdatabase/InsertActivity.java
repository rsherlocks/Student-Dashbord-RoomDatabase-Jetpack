package com.example.androidshaper.studentdashboardbyjetpackroomdatabase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class InsertActivity extends AppCompatActivity implements View.OnClickListener {

    EditText editTextStudentName,editTextStudentId,editTextStudentSemester;
    Button buttonAddStudent;
    StudentViewModel studentViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);
        editTextStudentName=findViewById(R.id.editTextStudentName);
        editTextStudentId=findViewById(R.id.editTextStudentId);
        editTextStudentSemester=findViewById(R.id.editTextStudentSemester);
        buttonAddStudent=findViewById(R.id.buttonAddStudent);
        buttonAddStudent.setOnClickListener(this);

        studentViewModel= ViewModelProviders.of(this).get(StudentViewModel.class);

    }

    @Override
    public void onClick(View view) {

        if (view.getId()==R.id.buttonAddStudent)
        {
            String studentName=editTextStudentName.getText().toString();
            String studentId=editTextStudentId.getText().toString();
            String studentSemester=editTextStudentSemester.getText().toString();

            Student student=new Student(studentName,studentId,studentSemester);

            studentViewModel.insertStudentToViewModel(student);
            finish();

        }

    }
}