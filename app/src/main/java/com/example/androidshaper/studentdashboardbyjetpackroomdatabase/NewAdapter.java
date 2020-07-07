package com.example.androidshaper.studentdashboardbyjetpackroomdatabase;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class NewAdapter extends RecyclerView.Adapter<NewAdapter.NewViewHolder> {

    OnRecyclerItemClickInterface itemClickInterface;
    List<Student> myList;

    public NewAdapter(OnRecyclerItemClickInterface interFace, List<Student> myList) {
        this.itemClickInterface=interFace;
        this.myList = myList;
    }

    @NonNull
    @Override
    public NewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout,parent,false);


        return new NewViewHolder(view,itemClickInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull NewViewHolder holder, int position) {

        Student studentPosition=myList.get(position);



        if (!myList.isEmpty())
        {

            holder.textViewStudentName.setText(studentPosition.getStudentName());
            holder.textViewStudentId.setText(studentPosition.getStudentId());
            holder.textViewStudentSemester.setText(studentPosition.getStudentSemester());

        }

    }

    @Override
    public int getItemCount() {
        return myList.size();
    }

    public class NewViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textViewStudentName,textViewStudentId,textViewStudentSemester;

        OnRecyclerItemClickInterface onRecyclerItemClickInterface;

        public NewViewHolder(@NonNull View itemView,OnRecyclerItemClickInterface interFace) {
            super(itemView);
            textViewStudentName=itemView.findViewById(R.id.textViewStudentName);
            textViewStudentId=itemView.findViewById(R.id.textViewStudentId);
            textViewStudentSemester=itemView.findViewById(R.id.textViewStudentSemester);
            itemView.setOnClickListener(this);
            this.onRecyclerItemClickInterface=interFace;

        }

        @Override
        public void onClick(View view) {
            Toast.makeText(view.getContext(),"Click item",Toast.LENGTH_SHORT).show();


            onRecyclerItemClickInterface.OnItemClick(getAdapterPosition());


        }
    }

    public interface  OnRecyclerItemClickInterface{
        void OnItemClick(int position);
    }
}
