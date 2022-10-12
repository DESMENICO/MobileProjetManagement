package be.helha.desmette.mobileprojetmanagement.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class StudentList {
    List<Student> studentList = new ArrayList<Student>();

    public List<Student> getStudentList(){
                return studentList;
    }

    public void addStudent(Student student){
        studentList.add(student);

    }


    public void removeStudentByID(UUID id){
        Student studentToRemove = null;
        for (Student student: studentList) {
            if(student.getId().equals(id)){
                studentToRemove = student;
            }
        }
        studentList.remove(studentToRemove);
    }
    public Student getStudentByID(UUID id){
        for (Student student: studentList) {
            if(student.getId().equals(id)){
                return student;
            }
        }
        return null;
    }



}
