package com.tinhngo.databaseandroid.ui.addstudent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.tinhngo.databaseandroid.R;
import com.tinhngo.databaseandroid.repository.local.model.StudentModel;
import com.tinhngo.databaseandroid.repository.local.sqlite.SQLiteHelper;

import java.util.Date;

public class AddStudentActivity extends AppCompatActivity {

    private EditText edtName, edtAddress, edtBirthday;
    private String name, address, birthday;
    private Button btnAdd;

    private SQLiteHelper sqLiteHelper = new SQLiteHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);
        init();
    }

    private void init() {
        edtName = (EditText) findViewById(R.id.edtName);
        edtAddress = (EditText) findViewById(R.id.edtAddress);
        edtBirthday = (EditText) findViewById(R.id.edtBirthday);
        btnAdd = (Button) findViewById(R.id.btnAdd);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(valid()) {
                    long id = new Date().getTime();
                    sqLiteHelper.addStudent(new StudentModel(
                            id + "",
                            name,
                            address,
                            birthday,
                            ""
                    ));
                    finish();
                }
            }
        });
    }

    private boolean valid() {
        name = edtName.getText().toString();
        address = edtAddress.getText().toString();
        birthday = edtBirthday.getText().toString();

        boolean isValid = true;

        if(name.isEmpty()) {
            edtName.setError("please enter name");
            isValid = false;
        } else {
            edtName.setError(null);
        }

        if(address.isEmpty()) {
            edtAddress.setError("please enter address");
            isValid = false;
        } else {
            edtAddress.setError(null);
        }

        if(birthday.isEmpty()) {
            edtBirthday.setError("please enter birthday");
            isValid = false;
        } else {
            edtBirthday.setError(null);
        }

        return isValid;
    }
}
