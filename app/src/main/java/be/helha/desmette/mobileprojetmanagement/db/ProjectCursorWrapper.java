package be.helha.desmette.mobileprojetmanagement.db;

import android.database.Cursor;
import android.database.CursorWrapper;

import java.io.Serializable;
import java.util.UUID;

import be.helha.desmette.mobileprojetmanagement.model.Project;

public class ProjectCursorWrapper extends CursorWrapper implements Serializable {
    public ProjectCursorWrapper(Cursor cursor) {super(cursor);}

    public Project getProject(){
        String uuidString = getString(getColumnIndex(DbSchema.ProjectTable.cols.UUID));
        String name = getString(getColumnIndex(DbSchema.ProjectTable.cols.Name));
        String description = getString(getColumnIndex(DbSchema.ProjectTable.cols.Description));
        int average = Integer.parseInt(getString(getColumnIndex(DbSchema.ProjectTable.cols.Average)));
        String ownerId = getString(getColumnIndex(DbSchema.ProjectTable.cols.OwnerID));
        Project project = new Project(UUID.fromString(uuidString));
        project.setName(name);
        project.setAverage(average);
        project.setDescription(description);
        project.setOwnerID(UUID.fromString(ownerId));
        return project;
    }
}
