package com.tinhngo.databaseandroid.ui.main;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.tinhngo.databaseandroid.R;
import com.tinhngo.databaseandroid.repository.local.model.StudentModel;
import com.tinhngo.databaseandroid.repository.local.sqlite.SQLiteHelper;
import com.tinhngo.databaseandroid.ui.addstudent.AddStudentActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int MainActivity_Id = 1;
    private static final int AddStudent_Id = 2;

    private List<StudentModel> studentModels = new ArrayList<>();
    private SQLiteHelper sqLiteHelper = new SQLiteHelper(this);
    private StudentAdapter adapter;

    private RecyclerView recyclerView;
    private FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        getAllStudent();

    }

    private void init() {
        recyclerView = (RecyclerView) findViewById(R.id.rcvStudent);
        floatingActionButton = (FloatingActionButton) findViewById(R.id.float_button_action);

        adapter = new StudentAdapter(studentModels, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AddStudentActivity.class);
                startActivityForResult(intent, MainActivity_Id);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == MainActivity_Id) {
            studentModels.clear();
            getAllStudent();
            adapter.notifyDataSetChanged();

        }
    }

    private void getAllStudent() {
        studentModels.addAll(sqLiteHelper.getAllStudent());
    }

}
