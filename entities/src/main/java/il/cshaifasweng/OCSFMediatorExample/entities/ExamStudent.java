package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name= "ExamStudent")
public class ExamStudent extends ExamTeacher implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id;
    int grade;

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }
}
