package be.helha.desmette.mobileprojetmanagement.controller;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.Serializable;
import java.util.UUID;

import be.helha.desmette.mobileprojetmanagement.R;
import be.helha.desmette.mobileprojetmanagement.model.Project;
import be.helha.desmette.mobileprojetmanagement.model.StepProject;
import be.helha.desmette.mobileprojetmanagement.model.Student;
import be.helha.desmette.mobileprojetmanagement.model.StudentList;

public class Project_config_activity extends AppCompatActivity implements Serializable,StepDialog.Interface,NameDialog.Interface,CotationFragment.Interface {

    public static final String ProjetID = "ProjetID";
    public static final String StudentID= "StudentID";
    public static final String ProjectDATA = "PROJECTDATA";

    Project project;
    Button addStep,mProjectModify;
    TextView project_name,averageCotation;
    TextView project_description;
    LinearLayout mContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.project_config_activity);
        getIntentData();
        addStep = findViewById(R.id.add_step_button);
        mProjectModify = findViewById(R.id.projet_name_modify);
        project_name = findViewById(R.id.projet_name_in_configuration);
        project_description = findViewById(R.id.description_project_textfield);
        averageCotation = findViewById(R.id.average_textView);
        mContainer = findViewById(R.id.AddStepLayout);
        updateUI();
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
        Student student = (Student) getIntent().getSerializableExtra(StudentID);
        project = student.getProjectByID(projectId);
    }

    private void updateUI() {
        mContainer.removeAllViews();
        for (StepProject x : project.getStepProjectList()) {
            addFragmentOnUpdate(x);
        }
        averageCotation.setText(project.getCotationAverage() + "/20");
        project_name.setText(project.getName());
        project_description.setText(project.getDescription());
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

    public void addFragment(String stepName){
        FragmentManager fragmentManager = getSupportFragmentManager();
        CotationFragment cotationFragment = (CotationFragment) fragmentManager.findFragmentById(R.id.AddStepLayout);
        cotationFragment = new CotationFragment();
        cotationFragment.setInterface(this);
        cotationFragment.setProject(new StepProject(stepName));
        project.addStep(new StepProject(stepName));
        fragmentManager.beginTransaction().add(R.id.AddStepLayout,cotationFragment).commit();
    }

    @Override
    public void getStepName(String stepName) {
        addFragment(stepName);
    }

    @Override
    public void getNewNameAndDescription(String name, String description) {
        project.setmName(name);
        project.setmDescription(description);
        project_name.setText(name);
        project_description.setText(description);
    }

    @Override
    public void setCotation(String stepName, int cotation) {
        project.modifyCotationOn(stepName,cotation);
        averageCotation.setText(project.getCotationAverage() + "/20");

    }
}