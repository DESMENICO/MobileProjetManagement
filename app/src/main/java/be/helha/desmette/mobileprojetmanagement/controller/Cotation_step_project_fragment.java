package be.helha.desmette.mobileprojetmanagement.controller;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.Serializable;

import be.helha.desmette.mobileprojetmanagement.R;
import be.helha.desmette.mobileprojetmanagement.model.StepProject;

public class Cotation_step_project_fragment extends Fragment implements Serializable {

    StepProject mStepProject;
    Spinner mCotationSpinner;
    TextView mStepName;
    String mName = "Test";
    int mCurrentCotation;


    Interface mInterface;

    void setInterface(Interface mInterface){
        this.mInterface = mInterface;
    }

    public void setProject(StepProject StepProject) {
        this.mStepProject = StepProject;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.cotation_fragment,container,false);
        mCotationSpinner = v.findViewById(R.id.cotation_spinner);
        mStepName = v.findViewById(R.id.cotation_text);
        mStepName.setText(mStepProject.getStepName());
        mCotationSpinner.setSelection(mCurrentCotation);

        initializeSpinner();
        return v;
    }


    public void initializeSpinner(){
        String[] mPossibility = new String[] {"0","1","2","3","4","5","6","7","8","9","10"};
        ArrayAdapter<String> arrayAdapter;
        arrayAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item,mPossibility);
        mCotationSpinner.setAdapter(arrayAdapter);
        mCotationSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                mStepProject.setCotation(Integer.parseInt(mCotationSpinner.getSelectedItem().toString()));
                mInterface.setCotation(mStepProject);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //
            }
        });
        mCotationSpinner.setSelection(mStepProject.getCotation());

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    interface Interface{
        void setCotation(StepProject step);
    }
}
