package be.helha.desmette.mobileprojetmanagement.model;

import java.io.Serializable;

public class StepProject implements Serializable {
    private String mStepName;
    private int mCotation = 0;

    public StepProject(String mStepName) {
        this.mStepName = mStepName;
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
