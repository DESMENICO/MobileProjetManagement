package be.helha.desmette.mobileprojetmanagement.controller;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import be.helha.desmette.mobileprojetmanagement.R;

public class Add_student_dialog extends AppCompatDialogFragment {
    private EditText mStudentList;
    private Listener mListener;

    public void setmListener(Listener mListener) {
        this.mListener = mListener;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.add_student_dialog,null);
        builder.setView(view)
                .setTitle("Ajout d'étudiant")
                .setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //ne fais rien
                    }
                })
                .setPositiveButton("Confirmer", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mListener.getListStudentAdd(mStudentList.getText().toString());
                    }
                });
        mStudentList = view.findViewById(R.id.add_student_list);
        return builder.create();
    }


    interface Listener {
        void getListStudentAdd(String string);
    }
}
