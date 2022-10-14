package be.helha.desmette.mobileprojetmanagement.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import java.io.Serializable;

import be.helha.desmette.mobileprojetmanagement.R;
import be.helha.desmette.mobileprojetmanagement.model.Project;
import be.helha.desmette.mobileprojetmanagement.model.Student;
import be.helha.desmette.mobileprojetmanagement.model.StudentList;

public class Student_activity extends AppCompatActivity implements StudentFragment.Listener,AddStudentList.Listener {

    private static final int STUDENTDATA = 60;

    Button addStudent;
    LinearLayout mContainer;
    StudentList studentList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        studentList = new StudentList(getApplicationContext());
        studentList.getStudents();
        mContainer = findViewById(R.id.container_scrollview);
        addStudent = findViewById(R.id.add_object_button);
        addStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialogAddStudent();
            }
        });
        updateUI();

    }

    void updateUI(){
        mContainer.removeAllViews();
        for (Student student : studentList.getStudents()) {
            addFragmentOnUpdate(student);
        }
    }

    void openDialogAddStudent(){
        AddStudentList addStudentList = new AddStudentList();
        addStudentList.setListener(this);
        addStudentList.show(getSupportFragmentManager(),"name");
    }

    private void addFragmentOnUpdate(Student student) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        StudentFragment studentFragment = (StudentFragment) fragmentManager.findFragmentById(R.id.container_scrollview);
        studentFragment = new StudentFragment();
        studentFragment.setStudent(student);
        studentFragment.setListener(this);
        fragmentManager.beginTransaction().add(R.id.container_scrollview,studentFragment).commit();
    }

    @Override
    public void onClick(Student student) {
        Intent intent = new Intent(getApplicationContext(),Project_activity.class);
        intent.putExtra(Project_activity.STUDENTID,(Serializable) student);
        intent.putExtra(Project_activity.STUDENTLIST,(Serializable) studentList);
        startActivityForResult(intent, STUDENTDATA);
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateUI();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(resultCode==Project_config_activity.RESULT_OK){
            Student studentToReplace = (Student) data.getSerializableExtra(Project_activity.StudentDATA);
            studentList.removeStudentByID(studentToReplace.getId());
            studentList.addStudent(studentToReplace);
            Log.d("Test","ojndpiuzpeciuzeciuzebpcug");
        }
    }

    @Override
    public void getListStudentAdd(String string) {
        String[] studentNameList = string.split("\n");
        for (String name: studentNameList) {
            studentList.addStudent(new Student(name));
        }
        updateUI();

    }
}
