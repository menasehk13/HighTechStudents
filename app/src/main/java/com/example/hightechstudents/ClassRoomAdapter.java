package com.example.hightechstudents;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ClassRoomAdapter extends RecyclerView.Adapter<ClassRoomAdapter.viewholder> {
    List<ClassRoomDetails> classRoomDetails;
    Context c;

    public ClassRoomAdapter(List<ClassRoomDetails> classRoomDetails, Context c) {
        this.classRoomDetails = classRoomDetails;
        this.c = c;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_upcoming_class,parent,false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ClassRoomAdapter.viewholder holder, int position) {
        holder.coursename.setText(classRoomDetails.get(position).getCoursename());
        holder.time.setText(classRoomDetails.get(position).getTime());
        holder.room.setText(classRoomDetails.get(position).getRoom());
    }


    @Override
    public int getItemCount() {
        if (classRoomDetails!=null){
            return classRoomDetails.size();
        }
        return 0;
    }


    public class viewholder extends RecyclerView.ViewHolder {
        TextView coursename,time,room;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            coursename=itemView.findViewById(R.id.upcomingcourename);
            time=itemView.findViewById(R.id.time_oftheclass);
            room=itemView.findViewById(R.id.croom);
        }
    }
}
