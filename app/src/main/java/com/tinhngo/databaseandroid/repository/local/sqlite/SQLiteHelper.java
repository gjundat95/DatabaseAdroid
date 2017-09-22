package com.tinhngo.databaseandroid.repository.local.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.tinhngo.databaseandroid.repository.local.model.StudentModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tinhngo on 9/21/17.
 */

public class SQLiteHelper extends SQLiteOpenHelper {

    private static final int Database_Version = 3;

    private static final String Database_Name = "ManagerStudent";

    private static final String Table_Student = "Student";
    private static final String Id = "Id";
    private static final String Name = "Name";
    private static final String Address = "Address";
    private static final String Birthday = "Birthday";
    private static final String UrlImage = "UrlImage";

    public SQLiteHelper(Context context) {
        super(context, Database_Name, null, Database_Version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        final String createTableStudent = "create table " + Table_Student + " ( "
                + Id + " text primary key, " + Name + " text, " + Address + " text, "
                + Birthday + " text, " + UrlImage + " text " + " )";
        sqLiteDatabase.execSQL(createTableStudent);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        final String dropTableStudent = "drop table if exists " + Table_Student;
        sqLiteDatabase.execSQL(dropTableStudent);

        onCreate(sqLiteDatabase);
    }

    /**
     * CURD Table Student
     */

    public List<StudentModel> getAllStudent() {
        List<StudentModel> studentModels = new ArrayList<>();

        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        final String getAllStudent = "select * from " + Table_Student;
        Cursor cursor = sqLiteDatabase.rawQuery(getAllStudent, null);

        while (cursor.moveToNext()) {
            StudentModel studentModel = new StudentModel(
                    cursor.getString(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4)
            );
            studentModels.add(studentModel);
        }
        return studentModels;
    }

    public StudentModel getStudentById(String idStudent) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        final String getStudent = "select * from " + Table_Student + " where " + Id + " = " + idStudent;
        Cursor cursor = sqLiteDatabase.rawQuery(getStudent, null);
        if( cursor != null) {
            cursor.moveToNext();
            return new StudentModel(
                    cursor.getString(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4)
            );
        }
        return null;
    }

    public long addStudent(StudentModel studentModel) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(Id, studentModel.getId());
        contentValues.put(Name, studentModel.getName());
        contentValues.put(Address, studentModel.getAddress());
        contentValues.put(Birthday, studentModel.getBirthday());
        contentValues.put(UrlImage, studentModel.getUrlImage());

        return sqLiteDatabase.insert(Table_Student, null, contentValues);
    }

    public long updateStudent(StudentModel studentModel) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(Name, studentModel.getName());
        contentValues.put(Address, studentModel.getAddress());
        contentValues.put(Birthday, studentModel.getBirthday());
        contentValues.put(UrlImage, studentModel.getUrlImage());

        return sqLiteDatabase.update(Table_Student, contentValues, Id + " = ?", new String[]{ studentModel.getId()});
    }

    public long deleteStudent(String idStudent) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        return sqLiteDatabase.delete(Table_Student, Id + " = ?", new String[]{ idStudent });

    }

}
