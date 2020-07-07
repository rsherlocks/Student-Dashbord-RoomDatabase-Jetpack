package com.example.androidshaper.studentdashboardbyjetpackroomdatabase;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class HomePage extends AppCompatActivity implements View.OnClickListener, NewAdapter.OnRecyclerItemClickInterface {

    List<Student> list;
    NewAdapter newAdapter;
    StudentViewModel studentViewModel;
    RecyclerView recyclerView;
    Button buttonAd;
    NewAdapter.OnRecyclerItemClickInterface onRecyclerItemClickInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        recyclerView=findViewById(R.id.recyclerViewStudent);
        buttonAd=findViewById(R.id.buttonAdd);
        buttonAd.setOnClickListener(this);
        list=new ArrayList<>();
        onRecyclerItemClickInterface=HomePage.this;
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        loadData();
        swipeDelete();

    }

    private void swipeDelete() {

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT
                | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

                DeleteStudent(viewHolder.getAdapterPosition());


            }
        }).attachToRecyclerView(recyclerView);
    }
    private void DeleteStudent(final int position) {

        new AlertDialog.Builder(this)
                .setMessage("Do you want to delete the semister?")
                .setTitle("WARNING!!!")
                .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //delete function called

                        Student temp=list.get(position);

                        studentViewModel.DeleteStudent(temp);
                        newAdapter.notifyDataSetChanged();
                        loadData();

                    }
                })
                .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .show();
    }


    private void loadData() {

        studentViewModel= ViewModelProviders.of(this).get(StudentViewModel.class);
        studentViewModel.getStudentListAll().observe(this, new Observer<List<Student>>() {
            @Override
            public void onChanged(List<Student> students) {
                list=students;

                if (students.isEmpty())
                {
                    Toast.makeText(getApplicationContext()," EMpty",Toast.LENGTH_SHORT).show();
                    newAdapter=new NewAdapter(onRecyclerItemClickInterface,list);
                    //NewAdapter newAdapter=new NewAdapter(students,getApplication());

                    recyclerView.setAdapter(newAdapter);

                }
                else
                {
                    //Toast.makeText(HomePage.this,students.size(),Toast.LENGTH_LONG).show();


//                   newAdapter=new NewAdapter(students,onRecyclerItemClickInterface);
                    newAdapter=new NewAdapter(onRecyclerItemClickInterface,list);
                    //NewAdapter newAdapter=new NewAdapter(students,getApplication());

                    recyclerView.setAdapter(newAdapter);
                }

            }
        });


    }

    @Override
    public void onClick(View view) {

        if (view.getId()==R.id.buttonAdd)
        {
            Intent intent=new Intent(this,InsertActivity.class);
            startActivity(intent);

        }



    }

    @Override
    public void OnItemClick(int position) {
        Student temp=list.get(position);
        temp.setStudentName("RAKIB");
        temp.setStudentId("7482");
        temp.setStudentSemester("Final year");

//        StudentViewModel studentViewModel;
//        studentViewModel= ViewModelProviders.of(this).get(StudentViewModel.class);


        studentViewModel.UpdateStudent(temp);
        Toast.makeText(getApplicationContext(), String.valueOf(list.get(position)), Toast.LENGTH_SHORT).show();
    }
}