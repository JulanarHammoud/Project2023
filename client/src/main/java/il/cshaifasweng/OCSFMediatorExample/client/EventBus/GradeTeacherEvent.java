package il.cshaifasweng.OCSFMediatorExample.client.EventBus;

import il.cshaifasweng.OCSFMediatorExample.entities.GradeTeacher;

public class GradeTeacherEvent {
    GradeTeacher gradeTeacher;

    public GradeTeacherEvent(GradeTeacher gradeTeacher) {
        this.gradeTeacher = gradeTeacher;
    }

    public GradeTeacher getGradeTeacher() {
        return gradeTeacher;
    }

    public void setGradeTeacher(GradeTeacher gradeTeacher) {
        this.gradeTeacher = gradeTeacher;
    }
}
