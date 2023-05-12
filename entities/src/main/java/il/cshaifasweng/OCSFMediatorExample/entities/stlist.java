package il.cshaifasweng.OCSFMediatorExample.entities;

import java.io.Serializable;
import java.util.List;


public class stlist implements Serializable {
    private List<Student> students ;

    public List<Student> getFlower() {
        return students;
    }

    public stlist(List<Student> students) {
        this.students = students;
    }

    public stlist() {
    }
}
