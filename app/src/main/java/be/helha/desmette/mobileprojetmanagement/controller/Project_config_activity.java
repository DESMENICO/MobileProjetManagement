package be.helha.desmette.mobileprojetmanagement.controller;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import be.helha.desmette.mobileprojetmanagement.R;
import be.helha.desmette.mobileprojetmanagement.model.Project;
import be.helha.desmette.mobileprojetmanagement.model.StepProject;
import be.helha.desmette.mobileprojetmanagement.model.StudentList;

public class Project_config_activity extends AppCompatActivity implements Serializable, StepProject_Dialog.Listener, Name_dialog.Listener, Cotation_step_project_fragment.Interface {

    public static final String ProjetID = "ProjetID";
    public static final String StudentID= "StudentID";
    public static final String ProjectDATA = "PROJECTDATA";

    Project mProject;
    StudentList mStudentList;
    Button mAddStep,mProjectModify;
    TextView mProject_name, mAverageCotation;
    TextView mProject_description;
    LinearLayout mContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.project_config_activity);
        mStudentList = StudentList.get(getApplicationContext());
        getIntentData();
        mAddStep = findViewById(R.id.add_step_button);
        mProjectModify = findViewById(R.id.projet_name_modify);
        mProject_name = findViewById(R.id.projet_name_in_configuration);
        mProject_description = findViewById(R.id.description_project_textfield);
        mAverageCotation = findViewById(R.id.average_textView);
        mContainer = findViewById(R.id.AddStepLayout);
        mProjectModify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {openNameDialog();}
        });
        mAddStep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openStepDialog();
            }
        });

        updateUI();
    }


    private void getIntentData() {
        UUID projectId = (UUID) getIntent().getSerializableExtra(ProjetID);
        mProject = mStudentList.getProject(projectId);
    }

    private void updateUI() {
        mContainer.removeAllViews();
        List<StepProject> stepProjectList = mStudentList.getStepsOfProject(mProject.getID());

        for (StepProject x :stepProjectList) {
            addFragmentOnUpdate(x);
        }
        mAverageCotation.setText(mStudentList.getAverageProject(mProject.getID()) + " /20");
        if(mProject.getName() != null && mProject.getDescription() != null){
        mProject_name.setText(mProject.getName());
        mProject_description.setText(mProject.getDescription());}
        else{
            mProject_name.setText(R.string.project_name);
            mProject_description.setText(R.string.description_text_default);
        }
    }


    private void openNameDialog(){
        Name_dialog nameDialog = new Name_dialog();
        nameDialog.setListener(this);
        nameDialog.show(getSupportFragmentManager(),"name");
    }
    public void openStepDialog(){
        StepProject_Dialog stepProjectDialog = new StepProject_Dialog();
        stepProjectDialog.setListener(this);
        stepProjectDialog.show(getSupportFragmentManager(),"step");
    }

    public void addFragmentOnUpdate(StepProject stepProject){

        FragmentManager fragmentManager = getSupportFragmentManager();
        Cotation_step_project_fragment cotationStepprojectfragment = (Cotation_step_project_fragment) fragmentManager.findFragmentById(R.id.AddStepLayout);
        cotationStepprojectfragment = new Cotation_step_project_fragment();
        cotationStepprojectfragment.setInterface(this);
        cotationStepprojectfragment.setProject(stepProject);
        fragmentManager.beginTransaction().add(R.id.AddStepLayout, cotationStepprojectfragment).commit();

    }

    public void addFragment(StepProject step){
        FragmentManager fragmentManager = getSupportFragmentManager();
        Cotation_step_project_fragment cotationStepprojectfragment = (Cotation_step_project_fragment) fragmentManager.findFragmentById(R.id.AddStepLayout);
        cotationStepprojectfragment = new Cotation_step_project_fragment();
        cotationStepprojectfragment.setInterface(this);
        cotationStepprojectfragment.setProject(step);
        fragmentManager.beginTransaction().add(R.id.AddStepLayout, cotationStepprojectfragment).commit();
    }

    @Override
    public void getStepName(String stepName) {
        StepProject step = new StepProject(stepName);
        mStudentList.addStep(step, mProject);
        //project.addStep(step);
        addFragment(step);
    }

    @Override
    public void getNewNameAndDescription(String name, String description) {
        mProject.setName(name);
        mProject.setDescription(description);
        mProject_name.setText(name);
        mProject_description.setText(description);
        mStudentList.updateProject(mProject, mStudentList.getStudent(mProject.getOwnerID()));
    }

    @Override
    public void setCotation(StepProject step) {
        mStudentList.updateCotation(step, mProject);
        mAverageCotation.setText(mStudentList.getAverageProject(mProject.getID()) + "/20");

    }
}