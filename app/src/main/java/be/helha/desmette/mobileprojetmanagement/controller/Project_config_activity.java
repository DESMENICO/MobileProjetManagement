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

public class Project_config_activity extends AppCompatActivity implements Serializable,StepDialog.Interface,NameDialog.Interface,CotationFragment.Interface {

    public static final String ProjetID = "ProjetID";
    public static final String StudentID= "StudentID";
    public static final String ProjectDATA = "PROJECTDATA";

    Project project;
    StudentList studentList;
    Button addStep,mProjectModify;
    TextView project_name,averageCotation;
    TextView project_description;
    LinearLayout mContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.project_config_activity);
        studentList = StudentList.get(getApplicationContext());
        getIntentData();
        addStep = findViewById(R.id.add_step_button);
        mProjectModify = findViewById(R.id.projet_name_modify);
        project_name = findViewById(R.id.projet_name_in_configuration);
        project_description = findViewById(R.id.description_project_textfield);
        averageCotation = findViewById(R.id.average_textView);
        mContainer = findViewById(R.id.AddStepLayout);
        mProjectModify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {openNameDialog();}
        });
        addStep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openStepDialog();
            }
        });

        updateUI();
    }

    @Override
    public void onBackPressed() {
        Intent data = new Intent();
        data.putExtra(ProjectDATA, project);
        this.setResult(RESULT_OK, data);
        finish();
    }

    private void getIntentData() {
        UUID projectId = (UUID) getIntent().getSerializableExtra(ProjetID);
        //Student student = (Student) getIntent().getSerializableExtra(StudentID);
        project = studentList.getProject(projectId);
    }

    private void updateUI() {
        mContainer.removeAllViews();
        List<StepProject> stepProjectList = studentList.getStepsOfProject(project.getId());

        for (StepProject x :stepProjectList) {
            addFragmentOnUpdate(x);
        }
        averageCotation.setText(studentList.getAverageProject(project.getId()) + " /20");
        if(project.getName() != null && project.getDescription() != null){
        project_name.setText(project.getName());
        project_description.setText(project.getDescription());}
        else{
            project_name.setText(R.string.project_name);
            project_description.setText(R.string.description_text_default);
        }
    }


    private void openNameDialog(){
        NameDialog nameDialog = new NameDialog();
        nameDialog.setInterface(this);
        nameDialog.show(getSupportFragmentManager(),"name");
    }
    public void openStepDialog(){
        StepDialog stepDialog = new StepDialog();
        stepDialog.setmStepDialogInterface(this);
        stepDialog.show(getSupportFragmentManager(),"step");
    }

    public void addFragmentOnUpdate(StepProject stepProject){

        FragmentManager fragmentManager = getSupportFragmentManager();
        CotationFragment cotationFragment = (CotationFragment) fragmentManager.findFragmentById(R.id.AddStepLayout);
        cotationFragment = new CotationFragment();
        cotationFragment.setInterface(this);
        cotationFragment.setProject(stepProject);
        fragmentManager.beginTransaction().add(R.id.AddStepLayout,cotationFragment).commit();

    }

    public void addFragment(StepProject step){
        FragmentManager fragmentManager = getSupportFragmentManager();
        CotationFragment cotationFragment = (CotationFragment) fragmentManager.findFragmentById(R.id.AddStepLayout);
        cotationFragment = new CotationFragment();
        cotationFragment.setInterface(this);
        cotationFragment.setProject(step);
        fragmentManager.beginTransaction().add(R.id.AddStepLayout,cotationFragment).commit();
    }

    @Override
    public void getStepName(String stepName) {
        StepProject step = new StepProject(stepName);
        studentList.addStep(step,project);
        project.addStep(step);
        addFragment(step);
    }

    @Override
    public void getNewNameAndDescription(String name, String description) {
        project.setmName(name);
        project.setmDescription(description);
        project_name.setText(name);
        project_description.setText(description);
        studentList.updateProject(project,studentList.getStudent(project.getOwnerID()));
    }

    @Override
    public void setCotation(StepProject step) {
        studentList.updateCotation(step,project);
        project.updateStep(step);
        averageCotation.setText(project.getCotationAverage() + "/20");

    }
}