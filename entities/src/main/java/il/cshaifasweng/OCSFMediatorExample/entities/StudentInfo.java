package il.cshaifasweng.OCSFMediatorExample.entities;
import javax.persistence.*;
import java.io.Serializable;

public class StudentInfo implements Serializable {
    private Student student;

    public StudentInfo(Student student) {
        this.student = student;
    }

    public Student getStudent() {
        return student;
    }
    public StudentInfo() {
    }
}
