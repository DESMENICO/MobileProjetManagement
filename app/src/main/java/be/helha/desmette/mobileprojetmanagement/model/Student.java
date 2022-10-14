package be.helha.desmette.mobileprojetmanagement.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Student implements Serializable { private static Student sStudent;
    String mFirstName;
    UUID mId;
    List<Project> mProjectList;

    public Student(UUID mId) {
        this.mId = mId;
        mProjectList = new ArrayList<>();
    }

    public void setFirstName(String mFirstName) {
        this.mFirstName = mFirstName;
    }

    public void setProjectList(List<Project> mProjectList) {
        this.mProjectList = mProjectList;
    }

    public ArrayList<Project> getProjectList(){
        return (ArrayList<Project>) mProjectList;
    }

    public UUID getId() {
        return mId;
    }

    public Project getProjectByID(UUID id){
        for (Project project: mProjectList) {
            if(project.getId().equals(id)){
                return project;
            }
        }
        return null;
    }

    public void removeProjectByID(UUID id){
        Project projectToDelete = new Project();
        for (Project project: mProjectList) {

            if(project.getId().equals(id)){
                projectToDelete = project;
            }

        }
        mProjectList.remove(projectToDelete);
    }

    public Student(String mFirstName) {
        this.mFirstName = mFirstName;
        mId = UUID.randomUUID();
        mProjectList = new ArrayList<Project>();
    }

    public String getFirstName() {
        return mFirstName;
    }

    public void addProject(Project project){
        mProjectList.add(project);
    }
}
