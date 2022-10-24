package be.helha.desmette.mobileprojetmanagement.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.Serializable;

import be.helha.desmette.mobileprojetmanagement.R;
import be.helha.desmette.mobileprojetmanagement.model.Project;
import be.helha.desmette.mobileprojetmanagement.model.Student;
import be.helha.desmette.mobileprojetmanagement.model.StudentList;

public class Project_activity extends AppCompatActivity implements Serializable , Project_fragment.Listener{
    public static final int PROJECTDATA = 69;
    public static final String STUDENTID = "StudentID";



    Button mAddProject;
    LinearLayout mContainer;
    Student mStudent;
    StudentList mStudentList;
    TextView mTitle;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        mStudentList = StudentList.get(getApplicationContext());
        mStudentList.getStudents();
        mAddProject = findViewById(R.id.add_object_button);
        mContainer = findViewById(R.id.container_scrollview);
        mTitle = findViewById(R.id.title_textView);
        mTitle.setText("Liste des Projets");
        mStudent = (Student) getIntent().getSerializableExtra(STUDENTID);
        mAddProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Project project = new Project();
                mStudentList.addProject(project, mStudent);
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
        for (Project x: mStudentList.getProjectOfStudent(mStudent.getId())) {
            addFragmentOnUpdate(x);
        }
    }

    private void addFragmentOnUpdate(Project project){
        FragmentManager fragmentManager = getSupportFragmentManager();
        Project_fragment projectFragment = (Project_fragment) fragmentManager.findFragmentById(R.id.container_scrollview);
        projectFragment = new Project_fragment();
        projectFragment.setProject(project);
        projectFragment.setListener(this);
        fragmentManager.beginTransaction().add(R.id.container_scrollview,projectFragment).commit();
    }


    @Override
    public void onClickOnFragment(Project project) {
        Intent intent = new Intent(getApplicationContext(),Project_config_activity.class);
        intent.putExtra(Project_config_activity.ProjetID,project.getID());
        //intent.putExtra(Project_config_activity.StudentID,(Serializable) student);
        startActivityForResult(intent, PROJECTDATA);
    }
}
