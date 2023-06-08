package il.cshaifasweng.OCSFMediatorExample.client.EventBus;

import il.cshaifasweng.OCSFMediatorExample.entities.Student;

public class StudentLogEvent {
    Student student;

    public StudentLogEvent(Student student) {
        this.student = student;
    }

    public Student getStudent() {
        return student;
    }
}
