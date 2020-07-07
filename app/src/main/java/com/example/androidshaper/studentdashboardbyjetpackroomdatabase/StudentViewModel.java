package com.example.androidshaper.studentdashboardbyjetpackroomdatabase;

import android.app.Application;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class StudentViewModel extends AndroidViewModel {
    public Repository repository;
    LiveData<List<Student>> allListStudent;

    public StudentViewModel(@NonNull Application application) {
        super(application);
        repository=new Repository(application);
        this.allListStudent=repository.getAllStudentList();
    }

    public LiveData<List<Student>> getStudentListAll()
    {
        return allListStudent;
    }

    public void insertStudentToViewModel(Student student)
    {
        repository.insertStudent(student);

    }

    public void DeleteStudent(Student student) {
        repository.deleteStudent(student);
    }

    public void UpdateStudent(Student temp) {
        repository.updateStudent(temp);
    }
}
