package il.cshaifasweng.OCSFMediatorExample.client.EventBus;


import il.cshaifasweng.OCSFMediatorExample.entities.StudentInfo;

public class StudentEvent {
    private StudentInfo student;

    public StudentEvent(StudentInfo student) {
        this.student = student;
    }

    public StudentInfo getStudent() {
        return student;
    }

    //public StudentEvent(Student msg) {
    //}
}
