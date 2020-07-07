package com.example.androidshaper.studentdashboardbyjetpackroomdatabase;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface StudentDao {

    @Insert
    public void studentInsert(Student student);

    @Update
    public void studentUpdate(Student student);

    @Delete
    public void studentDelete(Student student);

    @Query("select * From Student")
     LiveData<List<Student>> getAllStudent();

    @Query("Delete  from student")
     void deleteAllStudent();


}
