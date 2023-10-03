package il.cshaifasweng.OCSFMediatorExample.entities;

import java.io.Serializable;

public class GradeSt implements Serializable {
    Student ss;

    public Student getSs() {
        return ss;
    }

    public void setSs(Student ss) {
        this.ss = ss;
    }

    public GradeSt(Student ss) {
        this.ss = ss;
    }
}
