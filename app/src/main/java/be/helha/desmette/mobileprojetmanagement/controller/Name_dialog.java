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

public class Name_dialog extends AppCompatDialogFragment implements Serializable {

    EditText mProjectNameEditText,mProjectDescriptionEditText;
    Listener mListener;

    public void setListener(Listener listener){
        this.mListener = listener;
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.name_dialog,null);
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

                        mListener.getNewNameAndDescription(mProjectNameEditText.getText().toString(),mProjectDescriptionEditText.getText().toString());
                    }
                });
        mProjectNameEditText = view.findViewById(R.id.project_name_editText);
        mProjectDescriptionEditText = view.findViewById(R.id.project_description_editText);
        return builder.create();
    }


    interface Listener {
        void getNewNameAndDescription(String name, String description);
    }
}
