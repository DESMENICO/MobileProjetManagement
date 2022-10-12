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

public class ProjectFragment extends Fragment implements Serializable {

    public static String ProjetID = "ProjetID";
    TableRow mTableRow;
    TextView mProjectName,mProjectCotation;
    Project project;
    Listener listener;

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    public void setProject(Project project){
        this.project = project;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.project_fragment, container, false);
        mTableRow = v.findViewById(R.id.project_tableRow_OnClick);
        mProjectCotation = v.findViewById(R.id.project_cotation_textview);
        mProjectName = v.findViewById(R.id.project_name_textView);
        if (project != null){
            mProjectName.setText(project.getName());
            mProjectCotation.setText(String.valueOf(project.getCotationAverage()));
        }
        mTableRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClickOnFragment(project);
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
