package com.tinhngo.databaseandroid.ui.main;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.tinhngo.databaseandroid.R;
import com.tinhngo.databaseandroid.repository.local.model.StudentModel;
import com.tinhngo.databaseandroid.ui.detailstudent.DetailStudentActivity;

import java.util.List;

/**
 * Created by tinhngo on 9/21/17.
 */

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentHolder> {

    private List<StudentModel> studentModels;
    private AppCompatActivity context;

    public StudentAdapter(List<StudentModel> studentModels, AppCompatActivity context) {
        this.studentModels = studentModels;
        this.context = context;
    }

    @Override
    public StudentHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext())
               .inflate(R.layout.recycler_item_student, parent, false);
        return new StudentHolder(view);
    }

    @Override
    public void onBindViewHolder(StudentHolder holder, int position) {
        final StudentModel student = studentModels.get(position);
        holder.tvName.setText(student.getName());
        holder.tvAddress.setText(student.getAddress());
        holder.tvBirthday.setText(student.getBirthday());

        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailStudentActivity.class);
                intent.putExtra("id", student.getId());
                context.startActivityForResult(intent, MainActivity.MainActivity_Id);
            }
        });
    }

    @Override
    public int getItemCount() {
        return studentModels.size();
    }

    public static class StudentHolder extends RecyclerView.ViewHolder {

        private ImageView imgAvatar;
        private TextView tvName, tvAddress, tvBirthday;
        private RelativeLayout relativeLayout;

        public StudentHolder(View itemView) {
            super(itemView);

            relativeLayout = itemView.findViewById(R.id.item_student);
            imgAvatar = itemView.findViewById(R.id.imgAvatar);
            tvName = itemView.findViewById(R.id.tvName);
            tvAddress = itemView.findViewById(R.id.tvAddress);
            tvBirthday = itemView.findViewById(R.id.tvBirthday);

        }

    }
}
