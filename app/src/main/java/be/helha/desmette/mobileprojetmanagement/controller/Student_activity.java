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
import be.helha.desmette.mobileprojetmanagement.model.Student;
import be.helha.desmette.mobileprojetmanagement.model.StudentList;

public class Student_activity extends AppCompatActivity implements Student_fragment.Listener, Add_student_dialog.Listener {

    private static final int STUDENTDATA = 60;

    Button mAddStudent;
    LinearLayout mContainer;
    StudentList mStudentList;
    TextView mTitle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mStudentList = StudentList.get(getApplicationContext());
        mContainer = findViewById(R.id.container_scrollview);
        mAddStudent = findViewById(R.id.add_object_button);
        mTitle = findViewById(R.id.title_textView);
        mTitle.setText("Liste des Etudiants");
        mAddStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialogAddStudent();
            }
        });
        updateUI();

    }

    void updateUI(){
        mContainer.removeAllViews();
        for (Student student : mStudentList.getStudents()) {
            addFragmentOnUpdate(student);
        }
    }

    void openDialogAddStudent(){
        Add_student_dialog addStudentdialog = new Add_student_dialog();
        addStudentdialog.setmListener(this);
        addStudentdialog.show(getSupportFragmentManager(),"name");
    }

    private void addFragmentOnUpdate(Student student) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        Student_fragment studentFragment = (Student_fragment) fragmentManager.findFragmentById(R.id.container_scrollview);
        studentFragment = new Student_fragment();
        studentFragment.setStudent(student);
        studentFragment.setListener(this);
        fragmentManager.beginTransaction().add(R.id.container_scrollview,studentFragment).commit();
    }

    @Override
    public void onClick(Student student) {
        Intent intent = new Intent(getApplicationContext(),Project_activity.class);
        intent.putExtra(Project_activity.STUDENTID,(Serializable) student);
      //intent.putExtra(Project_activity.STUDENTLIST,(Serializable) studentList);
        startActivityForResult(intent, STUDENTDATA);
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateUI();
    }


    @Override
    public void getListStudentAdd(String string) {
        String[] studentNameList = string.split("\n");
        for (String name: studentNameList) {
            mStudentList.addStudent(new Student(name));
        }
        updateUI();

    }
}
