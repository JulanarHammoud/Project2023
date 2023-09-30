package il.cshaifasweng.OCSFMediatorExample.entities;

import java.io.Serializable;

public class GetSubject implements Serializable {
    SubjectTeacher subjectTeacher;
    Teacher teacher;
    public GetSubject(SubjectTeacher subjectTeacher, Teacher teacher) {
        this.subjectTeacher = subjectTeacher;
        this.teacher = teacher;
    }
    public SubjectTeacher getSubjectTeacher() {
        return subjectTeacher;
    }
    public void setSubjectTeacher(SubjectTeacher subjectTeacher) {
        this.subjectTeacher = subjectTeacher;
    }
    public Teacher getTeacher() {
        return teacher;
    }
    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
}