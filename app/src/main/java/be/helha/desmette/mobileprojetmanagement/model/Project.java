package be.helha.desmette.mobileprojetmanagement.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;

public class Project implements Serializable {
    private UUID mId;
    private String mName = null;
    private String mDescription = null;
    private int mAverage;
    private UUID mOwnerID;
    private ArrayList<StepProject> stepProjectList = new ArrayList<StepProject>();



    public Project(UUID id){
        this.mId = id;
    }

    public Project(){
        mId =UUID.randomUUID();
    }


    public UUID getOwnerID() {
        return mOwnerID;
    }

    public void setAverage(int mAverage) {
        this.mAverage = mAverage;
    }


    public int getAverage(){
        return this.mAverage;
    }


    public void setOwnerID(UUID ownerID) {
        this.mOwnerID = ownerID;
    }


    public void setName(String mName) {
        this.mName = mName;
    }


    public void setDescription(String mDescription) {
        this.mDescription = mDescription;
    }


    public UUID getID() {
        return mId;
    }


    public String getName() {
        return mName;
    }


    public String getDescription() {
        return mDescription;
    }


}
