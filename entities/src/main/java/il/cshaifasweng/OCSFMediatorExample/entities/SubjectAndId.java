package il.cshaifasweng.OCSFMediatorExample.entities;

import java.io.Serializable;

public class SubjectAndId implements Serializable {
    SubjectTeacher subject ;
    int id;

    public SubjectAndId(SubjectTeacher subject, int id) {
        this.subject = subject;
        this.id = id;
    }

    public SubjectTeacher getSubject() {
        return subject;
    }

    public int getId() {
        return id;
    }
}
