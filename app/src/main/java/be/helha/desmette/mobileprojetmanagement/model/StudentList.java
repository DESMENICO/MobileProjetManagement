package be.helha.desmette.mobileprojetmanagement.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import be.helha.desmette.mobileprojetmanagement.db.DbSchema;
import be.helha.desmette.mobileprojetmanagement.db.ProjectCursorWrapper;
import be.helha.desmette.mobileprojetmanagement.db.StepCursorWrapper;
import be.helha.desmette.mobileprojetmanagement.db.StudentBaseHelper;
import be.helha.desmette.mobileprojetmanagement.db.StudentCursorWrapper;

public class StudentList implements Serializable {
    private Context mContext;
    private SQLiteDatabase mDatabase;
    private static StudentList sStudentList;

    public static StudentList get(Context context) {
        if(sStudentList == null) {
            sStudentList = new StudentList(context);
        }
        return sStudentList;
    }


    public StudentList(Context context){
        mContext = context.getApplicationContext();
        mDatabase = new StudentBaseHelper(mContext).getWritableDatabase();
    }


//=====================================Student_method==============================================================================

    public void addStudent(Student student){
        mDatabase.insert(DbSchema.StudentTable.NAME, null, getContentValuesStudent(student));
    }


    public Student getStudent(UUID uuid){
        StudentCursorWrapper cursor = queryStudent(DbSchema.StudentTable.cols.UUID + " = ?",new String[]{uuid.toString()});
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

    private StudentCursorWrapper queryStudent(String whereClause,String[] whereArgs){
        return new StudentCursorWrapper(mDatabase.query(DbSchema.StudentTable.NAME,null,whereClause,whereArgs,null,null,null));
    }

    public ContentValues getContentValuesStudent(Student student){
        ContentValues values = new ContentValues();
        values.put(DbSchema.StudentTable.cols.FirstName, student.getFirstName());
        values.put(DbSchema.StudentTable.cols.UUID,student.getId().toString());
        return values;
    }


    //=============================================Project_Method======================================================




    public void addProject(Project project, Student student){
        mDatabase.insert(DbSchema.ProjectTable.NAME,null,getContentValuesProject(project,student));
    }

    public List<Project> getProjectOfStudent(UUID ownerId){
        ArrayList<Project> projects = new ArrayList<>();

        ProjectCursorWrapper cursor = queryProject(DbSchema.ProjectTable.cols.OwnerID + " = ? ",new String[]{ownerId.toString()});
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

    public Project getProject(UUID uuid){
        ProjectCursorWrapper cursor = queryProject(DbSchema.ProjectTable.cols.UUID + " = ?",new String[]{uuid.toString()});
        try {
            if(cursor.getCount() == 0){
                return null;
            }
            cursor.moveToFirst();
            Project project = cursor.getProject();

            return project;
        } finally {
            cursor.close();
        }
    }

    public String getAverageProject(UUID uuid){
        List<StepProject> steps = getStepsOfProject(uuid);
        float sum = 0;
        for (StepProject step: steps) {
            sum += step.getCotation();
        }
        if(sum == 0){
            return "/";
        }else{
            return String.format("%.1f",(sum / steps.size()) * 2);
        }
    }

    public ContentValues getContentValuesProject(Project project,Student student){
        ContentValues values = new ContentValues();
        values.put(DbSchema.ProjectTable.cols.Name,project.getName());
        values.put(DbSchema.ProjectTable.cols.Description,project.getDescription());
        values.put(DbSchema.ProjectTable.cols.UUID,project.getID().toString());
        values.put(DbSchema.ProjectTable.cols.Average,project.getAverage());
        values.put(DbSchema.ProjectTable.cols.OwnerID,student.getId().toString());
        return values;
    }


    private ProjectCursorWrapper queryProject(String whereClause,String[]whereArgs){
        return new ProjectCursorWrapper(mDatabase.query(DbSchema.ProjectTable.NAME,null,whereClause,whereArgs,null,null,null));
    }

    public void updateProject(Project project, Student student){
        String uuidString = project.getID().toString();
        ContentValues values = getContentValuesProject(project,student);
        mDatabase.update(DbSchema.ProjectTable.NAME,values,DbSchema.ProjectTable.cols.UUID + " = ?",new String[]{uuidString});

    }

    //==================================Step_Method============================================================


    public void addStep(StepProject step,Project project){
        mDatabase.insert(DbSchema.StepTable.NAME,null,getContentValuesStep(step,project));
    }

    public List<StepProject> getStepsOfProject(UUID ProjectID){
        ArrayList<StepProject> steps = new ArrayList<>();

        StepCursorWrapper cursor = queryStep(DbSchema.StepTable.cols.ProjectID + " = ? ",new String[]{ProjectID.toString()});
        try{
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                steps.add(cursor.getStep());
                cursor.moveToNext();
            }
        }finally {
            cursor.close();
        }
        return steps;
    }


    public ContentValues getContentValuesStep(StepProject step,Project project){
        ContentValues values = new ContentValues();
        values.put(DbSchema.StepTable.cols.Name,step.getStepName());
        values.put(DbSchema.StepTable.cols.Cotation,step.getCotation());
        values.put(DbSchema.StepTable.cols.UUID,step.getId().toString());
        values.put(DbSchema.StepTable.cols.ProjectID,project.getID().toString());
        return values;
    }

    public void updateCotation(StepProject step, Project project){
        String uuidString = step.getId().toString();
        ContentValues values = getContentValuesStep(step,project);
        mDatabase.update(DbSchema.StepTable.NAME,values,DbSchema.StepTable.cols.UUID + " = ?",new String[]{uuidString});

    }

    private StepCursorWrapper queryStep(String whereClause,String[]whereArgs){
        return new StepCursorWrapper(mDatabase.query(DbSchema.StepTable.NAME,null,whereClause,whereArgs,null,null,null));
    }

}
