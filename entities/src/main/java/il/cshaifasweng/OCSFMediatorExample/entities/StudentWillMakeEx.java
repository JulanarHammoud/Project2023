package il.cshaifasweng.OCSFMediatorExample.entities;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class StudentWillMakeEx implements Serializable{
    Student ss;
    ExamStudent Ex;

    public Student getSs() {
        return ss;
    }

    public void setSs(Student ss) {
        this.ss = ss;
    }

    public ExamStudent getEx() {
        return Ex;
    }

    public void setEx(ExamStudent ex) {
        Ex = ex;
    }

    public StudentWillMakeEx() {
    }
}

