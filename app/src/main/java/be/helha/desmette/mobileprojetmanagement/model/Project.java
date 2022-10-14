package be.helha.desmette.mobileprojetmanagement.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;

import be.helha.desmette.mobileprojetmanagement.R;

public class Project implements Serializable {
    private UUID id;
    private String mName;
    private String mDescription;
    private UUID mOwnerID;
    private ArrayList<StepProject> stepProjectList = new ArrayList<StepProject>();


    public void setOwnerID(UUID ownerID) {
        this.mOwnerID = ownerID;
    }

    public Project(String Name, String Description) {
        id = UUID.randomUUID();
        this.mName = Name;
        this.mDescription = Description;
    }
    public Project(UUID id){
        this.id = id;
    }

    public Project(){
        id=UUID.randomUUID();
        this.mName = String.valueOf(R.string.app_name);
        this.mDescription = String.valueOf(R.string.description_text_default);
    }

    public ArrayList<StepProject> getStepProjectList() {
        return stepProjectList;
    }

    public void modifyCotationOn(String name, int cotation){
        for (StepProject x : stepProjectList) {
            if(x.getStepName() == name){
                x.setCotation(cotation);
            }
        }
    }


    public void setmName(String mName) {
        this.mName = mName;
    }

    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return mName;
    }

    public String getDescription() {
        return mDescription;
    }

    public String getCotationAverage() {
        float addition = 0;
        for (StepProject x : stepProjectList) {
            addition += (float) x.getmCotation();
        }

        if (((addition / stepProjectList.size()) * 2) != Float.NaN){
            return String.valueOf((addition / stepProjectList.size()) * 2);
        }
        else {
            return "0";
        }
    }

    public void addStep(StepProject stepProject){
        stepProjectList.add(stepProject);
    }

}
