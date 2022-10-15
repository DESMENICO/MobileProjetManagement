package be.helha.desmette.mobileprojetmanagement.db;

import android.database.Cursor;
import android.database.CursorWrapper;

import java.io.Serializable;
import java.util.UUID;

import be.helha.desmette.mobileprojetmanagement.model.Project;
import be.helha.desmette.mobileprojetmanagement.model.StepProject;

public class StepCursorWrapper extends CursorWrapper implements Serializable {

    public StepCursorWrapper(Cursor cursor) {
        super(cursor);
    }


    public StepProject getStep(){
        String name = getString(getColumnIndex(DbSchema.StepTable.cols.Name));
        int cotation = getInt(getColumnIndex(DbSchema.StepTable.cols.Cotation));
        String uuid = getString(getColumnIndex(DbSchema.StepTable.cols.UUID));
        String projectID = getString(getColumnIndex(DbSchema.StepTable.cols.ProjectID));
        StepProject step = new StepProject(UUID.fromString(uuid));
        step.setProjectId(UUID.fromString(projectID));
        step.setStepName(name);
        step.setCotation(cotation);
        return step;
    }


}
