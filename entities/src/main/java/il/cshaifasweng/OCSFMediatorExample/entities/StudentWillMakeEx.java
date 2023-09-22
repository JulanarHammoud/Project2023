package il.cshaifasweng.OCSFMediatorExample.entities;
import java.io.Serializable;
import java.util.List;

public class StudentWillMakeEx implements Serializable{
    private List<Student> StudentsMakeThisEx;
    private Exam Ex;

    private String typee;
    private int IdCodeForExam;

    public int getIdCodeForExam() {
        return IdCodeForExam;
    }

    public String getTypee() {
        return typee;
    }

    public void setTypee(String typee) {
        this.typee = typee;
    }

    public void setIdCodeForExam(int idCodeForExam) {
        IdCodeForExam = idCodeForExam;
    }

    public StudentWillMakeEx() {
    }


    public List<Student> getStudentsMakeThisEx() {
        return StudentsMakeThisEx;
    }

    public void setStudentsMakeThisEx(List<Student> studentsMakeThisEx) {
        StudentsMakeThisEx = studentsMakeThisEx;
    }

    public Exam getEx() {
        return Ex;
    }

    public void setEx(Exam ex) {
        Ex = ex;
    }

    public StudentWillMakeEx(Exam ex) {
        Ex = ex;
    }
}

