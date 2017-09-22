package com.tinhngo.databaseandroid.ui.detailstudent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.tinhngo.databaseandroid.R;
import com.tinhngo.databaseandroid.repository.local.model.StudentModel;
import com.tinhngo.databaseandroid.repository.local.sqlite.SQLiteHelper;
import com.tinhngo.databaseandroid.ui.addstudent.AddStudentActivity;
import com.tinhngo.databaseandroid.ui.main.MainActivity;

public class DetailStudentActivity extends AppCompatActivity {

    private SQLiteHelper sqLiteHelper = new SQLiteHelper(this);
    private TextView tvName, tvAddress, tvBirthday;
    private Button btnDelete, btnUpdate;
    private String id;
    private StudentModel studentModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_student);
        init();
        getData();
    }

    private void init() {

        tvName = (TextView) findViewById(R.id.tvName);
        tvAddress = (TextView) findViewById(R.id.tvAddress);
        tvBirthday = (TextView) findViewById(R.id.tvBirthday);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sqLiteHelper.deleteStudent(id);
                finish();
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AddStudentActivity.class);
                Gson gson = new Gson();
                String student = gson.toJson(studentModel);
                intent.putExtra("student", student);
                startActivityForResult(intent, MainActivity.MainActivity_Id);
                finish();
            }
        });
    }

    public void getData() {
        Intent intent = this.getIntent();
        id = intent.getStringExtra("id");

        studentModel = sqLiteHelper.getStudentById(id);
        tvName.setText(studentModel.getName());
        tvAddress.setText(studentModel.getAddress());
        tvBirthday.setText(studentModel.getBirthday());
    }
}
