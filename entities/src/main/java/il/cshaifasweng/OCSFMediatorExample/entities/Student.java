package il.cshaifasweng.OCSFMediatorExample.entities;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name= "Student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;
    private String st_name;
    private int grade1;
    private int grade2;

    public Student(String st_name, int grade1,int grade2){
        this.st_name=st_name;
        this.grade1=grade1;
        this.grade2=grade2;
    }
    public Student(){}

    public int getId() {
        return Id;
    }

    public String getSt_name() {
        return st_name;
    }

    public int getGrade1() {
        return grade1;
    }

    public int getGrade2() {
        return grade2;
    }

    public void setId(int id) {
        Id = id;
    }

    public void setSt_name(String st_name) {
        this.st_name = st_name;
    }

    public void setGrade1(int grade1) {
        this.grade1 = grade1;
    }

    public void setGrade2(int grade2) {
        this.grade2 = grade2;
    }
}
