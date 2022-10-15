package be.helha.desmette.mobileprojetmanagement.controller;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableRow;
import android.widget.TextView;

import java.io.Serializable;

import be.helha.desmette.mobileprojetmanagement.R;
import be.helha.desmette.mobileprojetmanagement.model.Project;
import be.helha.desmette.mobileprojetmanagement.model.StudentList;

public class Project_fragment extends Fragment implements Serializable {

    public static String ProjetID = "ProjetID";
    TableRow mTableRow;
    TextView mProjectName,mProjectCotation;
    StudentList mStudentList;
    Project mProject;
    Listener mListener;

    public void setListener(Listener mListener) {
        this.mListener = mListener;
    }

    public void setProject(Project mProject){
        this.mProject = mProject;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.project_fragment, container, false);
        mTableRow = v.findViewById(R.id.project_tableRow_OnClick);
        mProjectCotation = v.findViewById(R.id.project_cotation_textview);
        mProjectName = v.findViewById(R.id.project_name_textView);
        mStudentList = StudentList.get(getContext());
        if (mProject != null){
            mProjectName.setText(mProject.getName());
            mProjectCotation.setText(mStudentList.getAverageProject(mProject.getID()));
        }
        mTableRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onClickOnFragment(mProject);
            }
        });
        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public interface Listener{
        void onClickOnFragment(Project project);
    }
}
