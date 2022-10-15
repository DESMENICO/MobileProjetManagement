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
import android.widget.TextView;

import java.io.Console;
import java.io.Serializable;

import be.helha.desmette.mobileprojetmanagement.R;
import be.helha.desmette.mobileprojetmanagement.model.Project;
import be.helha.desmette.mobileprojetmanagement.model.StepProject;
import be.helha.desmette.mobileprojetmanagement.model.Student;
import be.helha.desmette.mobileprojetmanagement.model.StudentList;

public class Project_activity extends AppCompatActivity implements Serializable ,ProjectFragment.Listener{
    public static final int PROJECTDATA = 69;
    public static final String STUDENTID = "StudentID";
    public static final String StudentDATA = "StudentDATA";


    Button addProject;
    LinearLayout mContainer;
    Student student;
    StudentList studentList;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        studentList = StudentList.get(getApplicationContext());
        studentList.getStudents();
        addProject = findViewById(R.id.add_object_button);
        mContainer = findViewById(R.id.container_scrollview);
        student = (Student) getIntent().getSerializableExtra(STUDENTID);
        //studentList = (StudentList) getIntent().getSerializableExtra(STUDENTLIST);
        addProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Project project = new Project();
                studentList.addProject(project,student);
                onClickOnFragment(project);
            }
        });
        updateUI();
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateUI();
    }

    private void updateUI() {
        mContainer.removeAllViews();
        for (Project x: studentList.getProjectOfStudent(student.getId())) {
            addFragmentOnUpdate(x);
        }
    }

    private void addFragmentOnUpdate(Project project){
        FragmentManager fragmentManager = getSupportFragmentManager();
        ProjectFragment projectFragment = (ProjectFragment) fragmentManager.findFragmentById(R.id.container_scrollview);
        projectFragment = new ProjectFragment();
        projectFragment.setProject(project);
        projectFragment.setListener(this);
        fragmentManager.beginTransaction().add(R.id.container_scrollview,projectFragment).commit();
    }


    @Override
    public void onClickOnFragment(Project project) {
        Intent intent = new Intent(getApplicationContext(),Project_config_activity.class);
        intent.putExtra(Project_config_activity.ProjetID,project.getId());
        //intent.putExtra(Project_config_activity.StudentID,(Serializable) student);
        startActivityForResult(intent, PROJECTDATA);
    }
}
