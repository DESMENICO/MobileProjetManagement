package be.helha.desmette.mobileprojetmanagement.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.WindowDecorActionBar;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import be.helha.desmette.mobileprojetmanagement.db.DbSchema;
import be.helha.desmette.mobileprojetmanagement.db.ProjectCursorWrapper;
import be.helha.desmette.mobileprojetmanagement.db.StudentBaseHelper;
import be.helha.desmette.mobileprojetmanagement.db.StudentCursorWrapper;

public class StudentList implements Serializable {
    private Context mContext;
    private SQLiteDatabase mDatabase;
    List<Student> studentList = new ArrayList<Student>();


    public StudentList(Context context){
        mContext = context.getApplicationContext();
        mDatabase = new StudentBaseHelper(mContext).getWritableDatabase();
    }

    public List<Student> getStudentList(){
                return studentList;
    }

    public void addStudent(Student student){
        mDatabase.insert(DbSchema.StudentTable.NAME, null,getContentValues(student));
    }


    public ContentValues getContentValues(Student student){
        ContentValues values = new ContentValues();
        values.put(DbSchema.StudentTable.cols.FirstName, student.getFirstName());
        values.put(DbSchema.StudentTable.cols.UUID,student.getId().toString());
        return values;
    }

    public void updateStudent(Student student){
        String uuidString = student.getId().toString();
        ContentValues values = getContentValues(student);

        mDatabase.update(DbSchema.StudentTable.NAME,values,DbSchema.StudentTable.cols.UUID + "= ?",new String[]{uuidString});
    }

    public List<Project> getProjectOfStudent(UUID ownerId){
        ArrayList<Project> projects = new ArrayList<>();

        ProjectCursorWrapper cursor = queryProject(DbSchema.ProjectTable.cols.OwnerID + "= ?",new String[]{ownerId.toString()});
        try{
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                projects.add(cursor.getProject());
                cursor.moveToNext();
            }
        }finally {
            cursor.close();
        }
        return projects;
    }

    public void flushStudent(){

    }


    public Student getStudent(UUID uuid){
        StudentCursorWrapper cursor = queryStudent(DbSchema.StudentTable.cols.UUID + "= ?",new String[]{uuid.toString()});
        try {
            if(cursor.getCount() == 0){
                return null;
            }
            cursor.moveToFirst();
            Student student = cursor.getStudent();

            return student;
        } finally {
            cursor.close();
        }
    }

    public List<Project> getProjects(UUID uuid){
        ArrayList<Project> projectsList = new ArrayList<>();

        ProjectCursorWrapper cursor = queryProject(DbSchema.ProjectTable.cols.OwnerID + "= ?",new String[]{uuid.toString()});
        try{
            if(cursor.getCount() == 0){
                return null;
            }
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                projectsList.add(cursor.getProject());
                cursor.moveToNext();
            }
        }finally {
            cursor.close();
        }
        return projectsList;
    }



    public List<Student> getStudents(){
        ArrayList<Student> students = new ArrayList<>();

        StudentCursorWrapper cursor = queryStudent(null,null);
        try{
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                students.add(cursor.getStudent());
                cursor.moveToNext();
            }
        }finally {
            cursor.close();
        }
        return students;
    }

    private ProjectCursorWrapper queryProject(String whereClause,String[]whereArgs){
        return new ProjectCursorWrapper(mDatabase.query(DbSchema.ProjectTable.NAME,null,whereClause,whereArgs,null,null,null));
    }


    private StudentCursorWrapper queryStudent(String whereClause,String[] whereArgs){
        return new StudentCursorWrapper(mDatabase.query(DbSchema.StudentTable.NAME,null,whereClause,whereArgs,null,null,null));
    }

    public void removeStudentByID(UUID id){
        Student studentToRemove = null;
        for (Student student: studentList) {
            if(student.getId().equals(id)){
                studentToRemove = student;
            }
        }
        studentList.remove(studentToRemove);
    }
    public Student getStudentByID(UUID id){
        for (Student student: studentList) {
            if(student.getId().equals(id)){
                return student;
            }
        }
        return null;
    }



}
