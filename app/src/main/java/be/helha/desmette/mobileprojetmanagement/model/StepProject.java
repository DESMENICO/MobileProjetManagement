package be.helha.desmette.mobileprojetmanagement.model;

import java.io.Serializable;
import java.util.UUID;

public class StepProject implements Serializable {
    private String mStepName;
    private int mCotation = 0;
    private UUID mProjectId;
    private UUID mId;


    public void setProjectId(UUID mProjectId) {
        this.mProjectId = mProjectId;
    }

    public UUID getProjectId() {
        return mProjectId;
    }

    public StepProject(String mStepName) {
        this.mStepName = mStepName;
        mId = UUID.randomUUID();
    }

    public StepProject(UUID id){
        this.mId = id;
    }

    public UUID getId() {
        return mId;
    }

    public String getStepName() {
        return mStepName;
    }

    public void setStepName(String mStepName) {
        this.mStepName = mStepName;
    }

    public int getmCotation() {
        return mCotation;
    }

    public void setCotation(int mStepGrade) {
        this.mCotation = mStepGrade;
    }
}
