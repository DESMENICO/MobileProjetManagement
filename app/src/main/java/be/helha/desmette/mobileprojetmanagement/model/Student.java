package be.helha.desmette.mobileprojetmanagement.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Student implements Serializable { private static Student sStudent;
    String mFirstName;
    UUID mId;
    List<Project> mProjectList;

    public Student(String mFirstName) {
        this.mFirstName = mFirstName;
        mId = UUID.randomUUID();
        mProjectList = new ArrayList<Project>();
    }

    public Student(UUID mId) {
        this.mId = mId;
        mProjectList = new ArrayList<>();
    }

    public void setFirstName(String mFirstName) {
        this.mFirstName = mFirstName;
    }

    public UUID getId() {
        return mId;
    }




    public String getFirstName() {
        return mFirstName;
    }

}
