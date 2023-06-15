
package il.cshaifasweng.OCSFMediatorExample.client.EventBus;

import il.cshaifasweng.OCSFMediatorExample.entities.CourseTeacher;
import il.cshaifasweng.OCSFMediatorExample.entities.SubjectTeacher;

public class SubjectTeacherEvent {
    SubjectTeacher subjectTeacher;

    public SubjectTeacherEvent(SubjectTeacher subjectTeacher) {
        this.subjectTeacher = subjectTeacher;
    }

    public SubjectTeacher getSubjectTeacher() {
        return subjectTeacher;
    }

    public void setSubjectTeacher(SubjectTeacher subjectTeacher) {
        this.subjectTeacher = subjectTeacher;
    }
}