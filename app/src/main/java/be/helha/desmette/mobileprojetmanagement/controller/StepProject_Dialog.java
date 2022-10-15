package be.helha.desmette.mobileprojetmanagement.controller;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import java.io.Serializable;

import be.helha.desmette.mobileprojetmanagement.R;

public class StepProject_Dialog extends AppCompatDialogFragment implements Serializable {

    private EditText mStep_name;
    Listener mListener;

    public void setListener(Listener mListener) {
        this.mListener = mListener;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.step_dialog,null);
        builder.setView(view)
                .setTitle("Ajout d'une cotation :")
                .setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //ne fais rien
                    }
                })
                .setPositiveButton("Confirmer", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mListener.getStepName(mStep_name.getText().toString());
                    }
                });
        mStep_name = view.findViewById(R.id.step_name_editText);
       return builder.create();
    }

    interface Listener {
        void getStepName(String stepName);
    }
}
