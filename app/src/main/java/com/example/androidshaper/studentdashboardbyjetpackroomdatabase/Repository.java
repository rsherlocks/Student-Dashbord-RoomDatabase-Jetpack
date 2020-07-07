package com.example.androidshaper.studentdashboardbyjetpackroomdatabase;

import android.app.Application;
import android.os.AsyncTask;

import java.util.List;

import androidx.lifecycle.LiveData;

public class Repository {

    public StudentDao studentDao;

    LiveData<List<Student>> studentList;



    Repository(Application application)
   {
       StudentDatabase studentDatabase=StudentDatabase.getDatabase(application);
       studentDao=studentDatabase.studentDao();
       studentList=studentDao.getAllStudent();
   }

   public LiveData<List<Student>> getAllStudentList()
   {
       return studentList;
   }

   public void insertStudent(Student student)
    {
        new InsertTask(studentDao).execute(student);

    }

    public void updateStudent(Student student) {
        new Updatestudent(studentDao).execute(student);
    }

    public class Updatestudent extends AsyncTask<Student,Void,Void>
    {
        private StudentDao studentDao;

        public Updatestudent(StudentDao studentDao) {
            this.studentDao = studentDao;
        }

        @Override
        protected Void doInBackground(Student... students) {

            studentDao.studentUpdate(students[0]);
            return null;
        }
    }

    public class InsertTask extends AsyncTask<Student,Void,Void>
    {
        private StudentDao studentDao;

        public InsertTask(StudentDao studentDao) {
            this.studentDao = studentDao;
        }

        @Override
        protected Void doInBackground(Student... students) {

            studentDao.studentInsert(students[0]);
            return null;
        }


    }
    public void deleteStudent(Student student){
        new DeleteTask(studentDao).execute(student);
    }

    public class  DeleteTask extends AsyncTask<Student,Void,Void>{
        private  StudentDao studentDao;

        public DeleteTask(StudentDao studentDao) {

            this.studentDao = studentDao;
        }

        @Override
        protected Void doInBackground(Student... students) {

            studentDao.studentDelete(students[0]);

            return null;
        }
    }



}
