package com.example.hightechstudents;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseviewHolder> {
    ArrayList<Courseview> courseAdapters;
    public CourseAdapter(ArrayList<Courseview> madapter){
        courseAdapters=madapter;
    }
    @NonNull
    @Override
    public CourseAdapter.CourseviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.custum_view_course,parent,false);
        return new CourseviewHolder(v);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull final CourseAdapter.CourseviewHolder holder, final int position) {
        holder.Coursename.setText(courseAdapters.get(position).getCoursename());
        final int value=courseAdapters.get(position).Credithour;
        holder.Coursename.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(v.getContext(),CourseDetail.class);
                intent.putExtra("CourseTitle",courseAdapters.get(position).getCoursename());
                intent.putExtra("CreditHour",value );
                 v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (courseAdapters == null) {
            return 0;
        }
        return courseAdapters.size();
    }

    public class CourseviewHolder extends RecyclerView.ViewHolder {
        protected TextView Coursename;
        
        public CourseviewHolder(@NonNull View itemView) {
            super(itemView);
            Coursename=itemView.findViewById(R.id.coursename);

        }
    }
}
