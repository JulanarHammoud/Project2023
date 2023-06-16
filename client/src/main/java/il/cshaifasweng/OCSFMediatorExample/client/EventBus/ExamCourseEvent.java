package il.cshaifasweng.OCSFMediatorExample.client.EventBus;

import il.cshaifasweng.OCSFMediatorExample.entities.CourseTeacher;
import il.cshaifasweng.OCSFMediatorExample.entities.ExamCourse;

public class ExamCourseEvent {
    ExamCourse exx;

    public ExamCourseEvent(ExamCourse exx) {
        this.exx = exx;
    }

    public ExamCourse getExx() {
        return exx;
    }

    public void setExx(ExamCourse exx) {
        this.exx = exx;
    }
}
