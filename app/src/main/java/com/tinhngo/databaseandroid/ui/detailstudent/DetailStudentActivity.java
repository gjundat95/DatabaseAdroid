package com.tinhngo.databaseandroid.ui.detailstudent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.tinhngo.databaseandroid.R;
import com.tinhngo.databaseandroid.repository.local.model.StudentModel;
import com.tinhngo.databaseandroid.repository.local.sqlite.SQLiteHelper;

public class DetailStudentActivity extends AppCompatActivity {

    private SQLiteHelper sqLiteHelper = new SQLiteHelper(this);
    private TextView tvName, tvAddress, tvBirthday;
    private Button btnDelete;
    private String id;

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

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sqLiteHelper.deleteStudent(id);
                finish();
            }
        });

    }

    public void getData() {
        Intent intent = this.getIntent();
        id = intent.getStringExtra("id");

        StudentModel studentModel = sqLiteHelper.getStudentById(id);
        tvName.setText(studentModel.getName());
        tvAddress.setText(studentModel.getAddress());
        tvBirthday.setText(studentModel.getBirthday());
    }
}
