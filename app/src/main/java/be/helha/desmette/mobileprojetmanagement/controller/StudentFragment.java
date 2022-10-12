package be.helha.desmette.mobileprojetmanagement.controller;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import be.helha.desmette.mobileprojetmanagement.R;
import be.helha.desmette.mobileprojetmanagement.model.Student;

public class StudentFragment extends Fragment {
    TextView mStudentName;
    LinearLayout mContainer;
    Student student;
    Listener listener;

    public void setListener(Listener listener) {
        this.listener = listener;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.student_name_fragment, container, false);
        mStudentName = v.findViewById(R.id.student_name);
        mContainer = v.findViewById(R.id.bg_student_name_fragment);
        mContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClick(student);
            }
        });
        mStudentName.setText(student.getFirstName());
        return v;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    interface Listener{
        void onClick(Student student);
    }
}
