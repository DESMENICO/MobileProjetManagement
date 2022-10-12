package be.helha.desmette.mobileprojetmanagement.db;

import android.database.Cursor;
import android.database.CursorWrapper;

import java.util.UUID;

import be.helha.desmette.mobileprojetmanagement.model.Student;

public class StudentCursorWrapper extends CursorWrapper {
    public StudentCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Student getStudent(){
        String firstNameString = getString(getColumnIndex(DbSchema.StudentTable.cols.FirstName));
        String uuidString = getString(getColumnIndex(DbSchema.StudentTable.cols.UUID));
        Student student = new Student(UUID.fromString(uuidString));
        student.setFirstName(firstNameString);
        return student;
    }
}
